package xyz.qweru.atlas.util.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * idk why i decided to use annotations
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AddonInfo {
    String name();
    String description();
    String authors() default "???";
//    String prefix() default "";
}
