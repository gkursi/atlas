package xyz.qweru.atlas.api.settings.impl;

import xyz.qweru.atlas.api.settings.Setting;

public class ToggleSetting extends Setting {

    boolean state;

    public ToggleSetting(String name, String description, boolean defaultValue) {
        super(name, description);
        state = defaultValue;
    }

    @Override
    public void onBind() {
        state =! state;
        valueChanged();
    }

    public void setState(boolean state) {
        this.state = state;
        valueChanged();
    }

    public boolean getState() {
        return state;
    }
}
