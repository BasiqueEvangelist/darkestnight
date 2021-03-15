package me.basiqueevangelist.darkestnight;

import net.minecraft.util.math.MathHelper;

public final class NightUtil {
    private NightUtil() {

    }

    public static float getNightEffectProgress(float skyAngle) {
        return Math.min(1.0F - Math.abs(skyAngle - 0.5F) * 16, 1.0F);
    }

    public static float lerpEffect(float skyAngle, float start, float end) {
        return MathHelper.lerp(getNightEffectProgress(skyAngle), start, end);
    }

    public static boolean isNight(float skyAngle) {
        return skyAngle < 0.75F && skyAngle > 0.25F;
    }
}
