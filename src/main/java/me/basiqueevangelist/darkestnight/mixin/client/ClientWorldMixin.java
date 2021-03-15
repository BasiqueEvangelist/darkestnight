package me.basiqueevangelist.darkestnight.mixin.client;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.MutableWorldProperties;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Supplier;

@Mixin(ClientWorld.class)
public abstract class ClientWorldMixin extends World {
    protected ClientWorldMixin(MutableWorldProperties properties, RegistryKey<World> registryRef, DimensionType dimensionType, Supplier<Profiler> profiler, boolean isClient, boolean debugWorld, long seed) {
        super(properties, registryRef, dimensionType, profiler, isClient, debugWorld, seed);
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void afterTick(CallbackInfo ci) {
        calculateAmbientDarkness();;
    }

    @Inject(method = "method_23783", at = @At(value = "TAIL"), cancellable = true)
    private void adjustWorldLight(CallbackInfoReturnable<Float> cir) {
        float value = (cir.getReturnValue() - 0.2F) / 0.8F * 0.9F + 0.1F;

        if (!isDay()) {
            int correctedMoonPhase = Math.abs(getMoonPhase() % 8 - 4);
            value *= (float)(correctedMoonPhase + 4) / 4;
        }

        value *= 1.0F - (rainGradient) / 6.5F;
        value *= 1.0F - (thunderGradient) / 6.5F;

        cir.setReturnValue(value);
    }
}
