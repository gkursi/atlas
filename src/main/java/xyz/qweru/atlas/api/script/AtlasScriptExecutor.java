package xyz.qweru.atlas.api.script;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import xyz.qweru.atlas.Atlas;
import xyz.qweru.atlas.api.AtlasApi;
import xyz.qweru.atlas.api.manager.Manager;
import xyz.qweru.atlas.api.manager.Managers;

import java.util.ArrayList;
import java.util.List;


public class AtlasScriptExecutor {
    static MinecraftClient mc;
    public static Context ctx;
    public static Scriptable scope;

    public static void init() {
        mc = MinecraftClient.getInstance();
        ctx = Context.enter();
        ctx.setOptimizationLevel(9);
        scope = ctx.initStandardObjects();
    }

    public static void stop() {
        ctx.close();
    }

    /**
     * Set value of key,
     * <b>does not persist across updates!!!</b>
     * @param key key to access value
     * @param value value
     */
    public static void set(String key, Object value) {
        ScriptableObject.putProperty(scope, key, Context.javaToJS(value, scope));
    }

    public static void update() {
        scope = ctx.initStandardObjects();
        set("mc", mc);
        set("modules", Managers.MODULE);
        set("scripts", Managers.SCRIPT);
        set("logger", AtlasApi.LOGGER);

        set("fps", mc.getCurrentFps());
        set("tickDelta", mc.getRenderTickCounter().getTickDelta(true));

        if(mc.world == null || mc.player == null) return;

        set("world", mc.world);
        set("player", mc.player);
        set("interactions", mc.interactionManager);

        List<Entity> entities = new ArrayList<>();
        mc.world.getEntities().forEach(entities::add);
        set("entities", entities);

//        for(Entity entity : entities) {
//            if(entity.distanceTo(mc.player) < 5) mc.interactionManager.attackEntity(mc.player, entity);
//        }
    }

    public static void execute(AtlasScript script) {
        try {
            set("name", script.name());
            set("description", script.description());
            script.content().exec(ctx, scope);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
