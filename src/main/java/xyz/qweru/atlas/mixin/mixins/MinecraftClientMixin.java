package xyz.qweru.atlas.mixin.mixins;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.qweru.atlas.api.AtlasApi;
import xyz.qweru.atlas.api.event.impl.PostInitEvent;
import xyz.qweru.atlas.api.event.impl.TickEvent;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Unique
    boolean once = false;

    @Inject(at = @At("HEAD"), method = "tick")
    void tick(CallbackInfo ci) {
        AtlasApi.norbit.post(new TickEvent());
    }

    @Inject(at = @At("TAIL"), method = "<init>")
    void init(CallbackInfo ci) {
        AtlasApi.init();
    }

}