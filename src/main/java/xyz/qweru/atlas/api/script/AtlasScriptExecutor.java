package xyz.qweru.atlas.api.script;

import net.minecraft.client.MinecraftClient;
import xyz.qweru.atlas.api.manager.Manager;
import xyz.qweru.atlas.api.manager.Managers;

import javax.script.*;

public class AtlasScriptExecutor {
    static MinecraftClient mc;
    static ScriptEngine engine;

    public static void init() {
        mc = MinecraftClient.getInstance();
        engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.put("minecraftClient", mc);
        engine.put("moduleManager", Managers.MODULE);
    }

    public static void set(String key, Object value) {
        engine.put(key, value);
    }

    public static void update() {
        engine.put("fps", mc.getCurrentFps());
        engine.put("tickDelta", mc.getRenderTickCounter().getTickDelta(true));

        if(mc.world == null || mc.player == null) return;

        engine.put("world", mc.world);
        engine.put("player", mc.player);
        engine.put("interactions", mc.interactionManager);
        engine.put("cameraEntity", mc.getCameraEntity());
    }

    public static void execute(AtlasScript script) {
        try {
            engine.put("name", script.name());
            engine.put("description", script.description());
            engine.eval(script.content());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
