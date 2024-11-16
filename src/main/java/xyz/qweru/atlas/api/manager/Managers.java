package xyz.qweru.atlas.api.manager;

import xyz.qweru.atlas.api.manager.impl.ModuleManager;
import xyz.qweru.atlas.api.manager.impl.ScriptManager;
import xyz.qweru.atlas.api.script.AtlasScript;

public class Managers {
    public static ModuleManager MODULE = new ModuleManager();
    public static ScriptManager SCRIPT = new ScriptManager();
}
