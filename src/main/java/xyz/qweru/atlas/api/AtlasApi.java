package xyz.qweru.atlas.api;

import io.github.racoondog.norbit.EventBus;
import meteordevelopment.orbit.EventHandler;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.EntrypointContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.minecraft.client.MinecraftClient;
import org.slf4j.LoggerFactory;
import xyz.qweru.atlas.api.event.impl.TickEvent;
import xyz.qweru.atlas.api.manager.Managers;
import xyz.qweru.atlas.api.script.AtlasScript;
import xyz.qweru.atlas.api.script.AtlasScriptExecutor;
import xyz.qweru.atlas.util.annotations.AddonInfo;
import xyz.qweru.atlas.util.misc.AtlasLogger;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public final class AtlasApi {
    public static final EventBus norbit = EventBus.threadSafe();
    public static final String MOD_ID = "Atlas";
    public static final AtlasLogger LOGGER = new AtlasLogger(LoggerFactory.getLogger(MOD_ID));
    public static final boolean debug = true;

    static boolean init = false;

    /**
     * Init method
     */
    public static void init() {
        if(init) return;
        LOGGER.info("Starting atlas");
        AtlasLogger.TimedLogger logger = LOGGER.getTimed();
        norbit.registerLambdaFactory("xyz.qweru.atlas", (lookupInMethod, klass) -> (MethodHandles.Lookup) lookupInMethod.invoke(null, klass, MethodHandles.lookup()));
        norbit.subscribe(AtlasApi.class);
        logger.debug("Loaded events");
        getAddons();
        logger.debug("Loaded addons");
        addons.forEach(AtlasAddon::onInitialize);
        logger.debug("Initialized addons");
        Managers.MODULE.init(addons);
        logger.debug("Initialized modules");
        AtlasScriptExecutor.init();
        logger.debug("Initialized scripts");
        AtlasScriptExecutor.execute(
                new AtlasScript("TestScript", "test script", """
            print('Atlas test script');
            console.log('Env: ' + minecraftClient.getName();
            
            
            """)
        );
        logger.debug("Ran script");
        LOGGER.info("Finished!");
        init = true;
    }

    public static boolean isInitialized() {
        return init;
    }

    static List<AtlasAddon> addons = new CopyOnWriteArrayList<>();

    /**
     * Inspiration from meteor client
     */
    static void getAddons() {
        for (EntrypointContainer<AtlasAddon> entrypoint : FabricLoader.getInstance().getEntrypointContainers("atlas", AtlasAddon.class)) {
            ModMetadata metadata = entrypoint.getProvider().getMetadata();
            AtlasAddon addon;
            try {
                addon = entrypoint.getEntrypoint();
            } catch (Throwable throwable) {
                throw new RuntimeException("Exception during addon init \"%s\".".formatted(metadata.getName()), throwable);
            }

            AddonInfo addonInfo = addon.getClass().getAnnotation(AddonInfo.class);
            if(addonInfo != null) {
                LOGGER.info("Found addon: {} ({}) by {}", addonInfo.name(), addonInfo.description(), addonInfo.authors());
            } else {
                throw new RuntimeException("Addon entrypoint must be annotated with @AddonInfo!");
            }

            norbit.registerLambdaFactory(addon.getPackage(), (lookupInMethod, klass) -> (MethodHandles.Lookup) lookupInMethod.invoke(null, klass, MethodHandles.lookup()));
            norbit.subscribe(addon);

            addons.add(addon);
        }
    }

    @EventHandler
    static void tick(TickEvent e) {
        AtlasScriptExecutor.update();
    }
}
