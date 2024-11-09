package xyz.qweru.atlas.api.module;

import net.minecraft.client.MinecraftClient;
import xyz.qweru.atlas.api.AtlasApi;

public class AtlasModule {
    protected static MinecraftClient mc = MinecraftClient.getInstance();
    protected int bind = -1;

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
}
