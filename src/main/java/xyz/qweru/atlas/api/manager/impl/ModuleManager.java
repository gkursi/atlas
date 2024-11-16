package xyz.qweru.atlas.api.manager.impl;

import oshi.util.tuples.Pair;
import xyz.qweru.atlas.api.AtlasAddon;
import xyz.qweru.atlas.api.AtlasApi;
import xyz.qweru.atlas.api.manager.Manager;
import xyz.qweru.atlas.api.module.AtlasModule;
import xyz.qweru.atlas.api.settings.Setting;
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

    public @Nullable Pair<AtlasModule, ModuleInfo> getModuleByClass(Class<?> c) {
        for (Pair<AtlasModule, ModuleInfo> data : getAll()) {
            if(data.getA().getClass() == c) return data;
        }

        return null;
    }

    public @Nullable Pair<AtlasModule, ModuleInfo> getModuleByName(String name) {
        for (Pair<AtlasModule, ModuleInfo> data : getAll()) {
            if(data.getB().name().equalsIgnoreCase(name)) return data;
        }
        return null;
    }

    public void handleKey(int key) {
        for (Pair<AtlasModule, ModuleInfo> i : getAll()) {
            if(i.getA().getBind() == key) {
                AtlasApi.LOGGER.debug("Toggled module: {}", i.getB().name());
                i.getA().toggle();
            }
            if(i.getA().isEnabled()) {
                for (Setting setting : i.getA().getSettings()) {
                    if(setting.getBind() == key) {
                        AtlasApi.LOGGER.debug("Toggled setting: {}", setting.getName());
                        setting.onBind();
                    }
                }
            }
        }
    }

}
