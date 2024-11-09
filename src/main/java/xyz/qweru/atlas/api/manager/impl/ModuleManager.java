package xyz.qweru.atlas.api.manager.impl;

import oshi.util.tuples.Pair;
import xyz.qweru.atlas.api.AtlasAddon;
import xyz.qweru.atlas.api.AtlasApi;
import xyz.qweru.atlas.api.manager.Manager;
import xyz.qweru.atlas.api.manager.Managers;
import xyz.qweru.atlas.api.module.AtlasModule;
import xyz.qweru.atlas.util.annotations.ModuleInfo;

import javax.annotation.Nullable;
import java.util.List;

public class ModuleManager extends Manager<Pair<AtlasModule, ModuleInfo>> {

    public void init(List<AtlasAddon> addons) {
        addons.forEach(AtlasAddon::registerModules);
    }

    /**
     * this should be used instead of the regular add
     * @param module the module
     */
    public void addModule(AtlasModule module) {
        ModuleInfo info = module.getClass().getAnnotation(ModuleInfo.class);
        if(info != null) {
            add(new Pair<>(module, info));
        } else throw new RuntimeException("Module info is not present!");
    }

    public @Nullable AtlasModule getModuleByClass(Class<?> c) {
        for (Pair<AtlasModule, ModuleInfo> data : getAll()) {
            if(data.getA().getClass() == c) return data.getA();
        }

        return null;
    }

    public @Nullable AtlasModule getModuleByID(String id) {
        for (Pair<AtlasModule, ModuleInfo> data : getAll()) {
            // todo
        }
        return null;
    }

}
