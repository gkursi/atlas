package xyz.qweru.atlas.util.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Any class annotated with this will be registered as an addon <br>
 * <bold>WARNING: this file must be in your root directory, otherwise events will not work!</bold>
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AddonInfo {
    String name();
    String description() default "???";
    String authors() default "???";
    String prefix() default "";
}
