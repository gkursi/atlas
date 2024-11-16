package xyz.qweru.atlas.api;

import org.reflections.Reflections;
import xyz.qweru.atlas.util.annotations.Todo;

@Todo.WriteDocs
public abstract class AtlasAddon {
    public void onInitialize() {}
    public void registerModules() {
        Reflections reflections = new Reflections(getPackage());
        // todo: automatically scan for modules
    }
    public abstract String getPackage();
}
