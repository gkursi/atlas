package xyz.qweru.atlas.api.test;

import org.lwjgl.glfw.GLFW;
import xyz.qweru.atlas.api.manager.Managers;

import static xyz.qweru.atlas.api.AtlasApi.mc;

public class ScriptTest {
    public static void scriptTest() {
        String content1 = """
                logger.info("Hello from script 1!");
                logger.info("Module count: {}", modules.getAll().size());
                logger.info("Script count: {}", scripts.getAll().size());
                """;

        String content2 = """
                if(player) {
                    logger.info("Player health: {}", mc.player.getHealth());
                } else {
                    logger.info("Player not loaded yet");
                }
                """;

        Managers.SCRIPT.add("Test script 1", "Test exposed module and script manager objects", content1, GLFW.GLFW_KEY_U);
        Managers.SCRIPT.add("Test script 2", "Test exposed player object", content2, GLFW.GLFW_KEY_J);
    }
}
