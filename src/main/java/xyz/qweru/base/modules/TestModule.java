package xyz.qweru.base.modules;

import xyz.qweru.atlas.api.AtlasApi;
import xyz.qweru.atlas.api.module.AtlasCategory;
import xyz.qweru.atlas.api.module.AtlasModule;
import xyz.qweru.atlas.util.annotations.ModuleInfo;

@ModuleInfo(name = "Test module", description = "A test module", category = AtlasCategory.MISC)
public class TestModule extends AtlasModule {

    @Override
    public void enable() {
        super.enable();
        AtlasApi.LOGGER.info("Enabled test module.");
    }

    @Override
    public void disable() {
        super.disable();
        AtlasApi.LOGGER.info("Disabled test module.");
    }
}
