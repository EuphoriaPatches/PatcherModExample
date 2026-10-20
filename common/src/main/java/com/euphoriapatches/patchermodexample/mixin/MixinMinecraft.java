package com.euphoriapatches.patchermodexample.mixin;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;

// Intentionally empty, kept so there is a registered common mixin to build on.
// Add injectors here, for example:
//
//     @Inject(at = @At("TAIL"), method = "<init>")
//     private void init(CallbackInfo info) { ... }
@Mixin(Minecraft.class)
public class MixinMinecraft {
}
