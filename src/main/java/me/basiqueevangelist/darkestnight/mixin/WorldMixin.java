package me.basiqueevangelist.darkestnight.mixin;

import me.basiqueevangelist.darkestnight.NightUtil;
import net.minecraft.world.LunarWorldView;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(World.class)
public abstract class WorldMixin implements LunarWorldView {
    @Shadow private int ambientDarkness;

    @Shadow public abstract boolean isClient();

    @Inject(method = "calculateAmbientDarkness", at = @At("TAIL"))
    private void changeAmbientDarkness(CallbackInfo ci) {
        if (isClient())
            return;

        int correctedMoonPhase = Math.abs(getDimension().getMoonPhase(this.getLunarTime()) % 8 - 4);

        ambientDarkness += Math.max(0, NightUtil.lerpEffect(getSkyAngle(0), 0, (4 - correctedMoonPhase)));
    }
}
