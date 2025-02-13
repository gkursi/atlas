package xyz.qweru.atlas.mixin.mixins;

import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.qweru.atlas.api.AtlasApi;
import xyz.qweru.atlas.api.event.impl.KeyEvent;

@Mixin(Keyboard.class)
public class KeyboardMixin {

    @Inject(at = @At("HEAD"), method = "onKey")
    void key(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
        AtlasApi.norbit.post(new KeyEvent(key, scancode, action, modifiers));
    }

}
