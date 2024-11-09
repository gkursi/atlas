package xyz.qweru.atlas.util.misc;

import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

// todo
public class Prefix {
    public final Text full;

    public Prefix(Text text) {
        this.full = text;
    }

    public static final Prefix DEFAULT = new Prefix(Text.empty().append(
            Text.empty().formatted(Formatting.BLUE).append("[")
    ).append(
            Text.empty().formatted(Formatting.WHITE).append("Atlas")
    ).append(
            Text.empty().formatted(Formatting.BLUE).append("] ")
    ));
}
