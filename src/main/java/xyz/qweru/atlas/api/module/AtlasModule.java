package xyz.qweru.atlas.api.module;

import net.minecraft.client.MinecraftClient;
import xyz.qweru.atlas.api.AtlasApi;
import xyz.qweru.atlas.api.settings.Setting;

import java.util.ArrayList;
import java.util.List;

public class AtlasModule {
    protected static MinecraftClient mc = MinecraftClient.getInstance();
    protected int bind = -1;
    boolean enabled = false;
    List<Setting> settings = new ArrayList<>();

    public void enable() {
        AtlasApi.norbit.subscribe(this);
    }

    public int getBind() {
        return bind;
    }

    public void setBind(int bind) {
        this.bind = bind;
    }

    public void disable() {
        AtlasApi.norbit.unsubscribe(this);
    }

    public void setEnabled(boolean e) {
        this.enabled = e;
    }
    public void toggle() {
        enabled =! enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void addSettings(Setting... settings) {
        this.settings.addAll(List.of(settings));
    }

    public List<Setting> getSettings() {
        return settings;
    }
}
