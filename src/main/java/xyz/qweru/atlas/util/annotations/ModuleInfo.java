package xyz.qweru.atlas.util.annotations;

import xyz.qweru.atlas.api.module.AtlasCategory;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ModuleInfo {
    String name();
    String description();
    AtlasCategory category();
}
