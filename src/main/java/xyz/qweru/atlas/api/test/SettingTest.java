package xyz.qweru.atlas.api.test;

import xyz.qweru.atlas.api.AtlasApi;
import xyz.qweru.atlas.api.settings.impl.CycleSetting;

public class SettingTest {
    public static void settingTest() {
        AtlasApi.LOGGER.debug("Started cycle setting test");
        CycleSetting<Modes> modeSetting = new CycleSetting<>("Test setting", "This is a test setting", Modes.SECOND);
        AtlasApi.LOGGER.debug("Given default: {}, current: {}", Modes.SECOND.name(), modeSetting.getCurrent().name());
        modeSetting.next();
        modeSetting.next();
        AtlasApi.LOGGER.debug("Cycled 2 times, expected: {}, actual: {}", Modes.FOURTH.name(), modeSetting.getCurrent().name());
        modeSetting.next();
        AtlasApi.LOGGER.debug("Cycled 1 time, expected: {}, actual: {}", Modes.FIRST.name(), modeSetting.getCurrent().name());
        AtlasApi.LOGGER.debug("Finished cycle setting test");
    }

    enum Modes {
        FIRST,
        SECOND,
        THIRD,
        FOURTH
    }
}
