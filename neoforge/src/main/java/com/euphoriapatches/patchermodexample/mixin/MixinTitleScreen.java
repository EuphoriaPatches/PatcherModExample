package com.euphoriapatches.patchermodexample.mixin;

import net.minecraft.client.gui.screens.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;

// Intentionally empty, kept so there is a registered loader specific mixin to build on.
// Add injectors here, for example:
//
//     @Inject(at = @At("HEAD"), method = "init()V")
//     private void init(CallbackInfo info) { ... }
@Mixin(TitleScreen.class)
public class MixinTitleScreen {
}
