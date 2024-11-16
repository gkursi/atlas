package xyz.qweru.atlas.api.manager.impl;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.EvaluatorException;
import org.mozilla.javascript.Script;
import oshi.util.tuples.Pair;
import xyz.qweru.atlas.api.AtlasApi;
import xyz.qweru.atlas.api.manager.Manager;
import xyz.qweru.atlas.api.module.AtlasModule;
import xyz.qweru.atlas.api.script.AtlasScript;
import xyz.qweru.atlas.api.script.AtlasScriptExecutor;
import xyz.qweru.atlas.api.settings.Setting;
import xyz.qweru.atlas.util.annotations.ModuleInfo;

import javax.script.Compilable;
import javax.script.ScriptException;

public class ScriptManager extends Manager<AtlasScript> {

    /**
     * Ignores upper / lower case
     */
    public AtlasScript getScriptByName(String name) {
        for (AtlasScript atlasScript : getAll()) {
            if(atlasScript.name().equalsIgnoreCase(name)) return atlasScript;
        }
        return null;
    }

    public AtlasScript add(String name, String description, String content, int bind) {
        try {
            Script s = AtlasScriptExecutor.ctx.compileString(content, name, 1, null);
            AtlasScript atlasScript = new AtlasScript(name, description, s, bind);
            add(atlasScript);
            return atlasScript;
        } catch (EvaluatorException e) {
            AtlasApi.LOGGER.error("Failed to compile '{}'!", name);
            AtlasApi.LOGGER.error(e.getMessage());
            throw new RuntimeException("Script compile error");
        }
    }

    public AtlasScript add(String name, String description, String content) {
        return add(name, description, content, -1);
    }

    public void handleKey(int key) {
        for (AtlasScript script : getAll()) {
            if(script.getBind() == key) AtlasScriptExecutor.execute(script);
        }
    }
}

/**
 *
 * TODO
 * add more events
 * add dynamic script loading
 *
 */
