package xyz.qweru.atlas.api.script;

import org.mozilla.javascript.Script;

import javax.script.CompiledScript;

public class AtlasScript {
    public int getBind() {
        return bind;
    }

    public void setBind(int bind) {
        this.bind = bind;
    }

    private final String name;
    private final String description;

    public Script content() {
        return content;
    }

    public String description() {
        return description;
    }

    public String name() {
        return name;
    }

    private final Script content;
    private int bind;

    public AtlasScript(String name, String description, Script content, int bind) {
        this.name = name;
        this.description = description;
        this.content = content;
        this.bind = bind;
    }
}
