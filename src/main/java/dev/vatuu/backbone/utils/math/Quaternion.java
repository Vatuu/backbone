package dev.vatuu.backbone.utils.math;

import net.minecraft.client.util.math.Vector3f;

public class Quaternion {

    private float x, y, z, w;

    public Quaternion(Vector3f axis, float angle) {
        angle = (float)Math.toRadians(angle);

        float theta = (float)Math.sin(angle / 2);
        this.x = axis.getX() * theta;
        this.y = axis.getY() * theta;
        this.z = axis.getZ() * theta;
        this.w = (float)Math.cos(angle / 2);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public float getW() {
        return w;
    }
}
