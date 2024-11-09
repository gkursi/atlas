package xyz.qweru.atlas.api.module;

import net.minecraft.util.Identifier;

public enum AtlasCategory {
    COMBAT("Combat", null),
    MOVE("Movement", null),
    PLAYER("Player", null),
    VISUAL("Visual", null),
    WORLD("World", null),
    MISC("Misc", null),
    EXPLOIT("Exploit", null);
//    public static AtlasCategory COMBAT = new AtlasCategory("Combat", null);
//    public static AtlasCategory MOVE = new AtlasCategory("Movement", null);
//    public static AtlasCategory PLAYER = new AtlasCategory("Player", null);
//    public static AtlasCategory VISUAL = new AtlasCategory("Visual", null);
//    public static AtlasCategory WORLD = new AtlasCategory("World", null);
//    public static AtlasCategory MISC = new AtlasCategory("Misc", null);
//    public static AtlasCategory EXPLOIT = new AtlasCategory("Exploit", null);

    private final String displayName;
    private final Identifier icon;

    // todo icon
    AtlasCategory(String displayName, Identifier icon) {
        this.displayName = displayName;
        this.icon = icon;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Identifier getIcon() {
        return icon;
    }
}
