package xyz.qweru.atlas.api;

import xyz.qweru.atlas.util.annotations.Todo;

@Todo.WriteDocs
public abstract class AtlasAddon {
    public void onInitialize() {}
    public void registerModules() {}
    public abstract String getPackage();
}
