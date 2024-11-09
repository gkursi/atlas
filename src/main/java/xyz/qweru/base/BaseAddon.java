package xyz.qweru.base;

import meteordevelopment.orbit.EventHandler;
import xyz.qweru.atlas.api.AtlasAddon;
import xyz.qweru.atlas.api.AtlasApi;
import xyz.qweru.atlas.api.event.impl.TickEvent;
import xyz.qweru.atlas.api.manager.Managers;
import xyz.qweru.atlas.util.annotations.AddonInfo;
import xyz.qweru.base.modules.TestModule;

@AddonInfo(name = "Base addon", description = "Adds some basic features to the client", authors = "qweru")
public class BaseAddon extends AtlasAddon {

    @Override
    public void registerModules() {
        AtlasApi.LOGGER.info("Registering base modules");
        Managers.MODULE.addModule(new TestModule());
    }

    @Override
    public String getPackage() {
        return "xyz.qweru.base";
    }

}
