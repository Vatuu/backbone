package dev.vatuu.backbone.utils;

import net.minecraft.util.math.Vec3d;

public final class MathUtils {

    public static double getDistance3D(Vec3d p1, Vec3d p2) {
        return Math.sqrt(
            Math.pow(p2.getX() - p1.getX(), 2) +
            Math.pow(p2.getY() - p1.getY(), 2) +
            Math.pow(p2.getZ() - p1.getZ(), 2)
        );
    }
}
