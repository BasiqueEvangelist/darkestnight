package me.basiqueevangelist.darkestnight.mixin;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkProvider;
import net.minecraft.world.chunk.light.ChunkLightProvider;
import net.minecraft.world.chunk.light.LightingProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LightingProvider.class)
public class LightingProviderMixin {
    @Unique private World world;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void storeWorld(ChunkProvider chunkProvider, boolean hasBlockLight, boolean hasSkyLight, CallbackInfo ci) {
        world = (World) chunkProvider.getWorld();
    }

    @Redirect(method = "getLight", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/chunk/light/ChunkLightProvider;getLightLevel(Lnet/minecraft/util/math/BlockPos;)I", ordinal = 0))
    private int getSkylightFor(ChunkLightProvider<?, ?> skylightProvider, BlockPos pos) {
        if (world.isClient)
            return skylightProvider.getLightLevel(pos);

        int correctedMoonPhase = Math.abs(world.getMoonPhase() % 8 - 4);
        return skylightProvider.getLightLevel(pos) - (4 - correctedMoonPhase);
    }
}
