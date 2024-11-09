package xyz.qweru.atlas.api.event;

import meteordevelopment.orbit.ICancellable;

public abstract class AtlasEvent implements ICancellable {
    protected boolean cancelled = false;
    public boolean isCancelled() { return cancelled; }
    public void setCancelled(boolean cancelled) { this.cancelled = cancelled; }
}
