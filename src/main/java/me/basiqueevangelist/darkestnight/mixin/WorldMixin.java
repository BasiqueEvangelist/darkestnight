package me.basiqueevangelist.darkestnight.mixin;

import net.minecraft.util.math.MathHelper;
import net.minecraft.world.LunarWorldView;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(World.class)
public abstract class WorldMixin implements LunarWorldView {
    @Shadow private int ambientDarkness;

    @Shadow public abstract float getThunderGradient(float delta);

    @Shadow public abstract float getRainGradient(float delta);

    /**
     * @reason Completely replaces logic.
     * @author BasiqueEvangelist
     */
    @Overwrite
    public void calculateAmbientDarkness() {
        double rainLight = 1.0D - (double)(this.getRainGradient(1.0F) * 5.0F) / 16.0D;
        double thunderLight = 1.0D - (double)(this.getThunderGradient(1.0F) * 5.0F) / 16.0D;
        double skyLight = 0.5D + 2.0D * MathHelper.clamp(MathHelper.cos(this.getSkyAngle(1.0F) * 6.2831855F), -0.25D, 0.25D);
        this.ambientDarkness = (int)((1.0D - skyLight * rainLight * thunderLight) * 15.0D);
    }
}
