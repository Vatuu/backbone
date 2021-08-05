package dev.vatuu.backbone.utils.math;

import net.minecraft.util.math.Vec3d;

public class Vector3 {

    private float x, y, z;

    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public void set(float x, float y, float z) {
        setX(x);
        setY(y);
        setZ(z);
    }

    public Vector3 add(float x, float y, float z) {
        return new Vector3(getX() + x, getY() + y, getZ() + z);
    }

    public Vector3 add(Vector3 vec) {
        return add(vec.getX(), vec.getY(), vec.getZ());
    }

    public Vector3 subtract(float x, float y, float z) {
        return new Vector3(getX() - x, getY() - y, getZ() - z);
    }

    public Vector3 subtract(Vector3 vec) {
        return subtract(vec.getX(), vec.getY(), vec.getZ());
    }

    public Vector3 multiply(float x, float y, float z) {
        return new Vector3(getX() * x, getY() * y, getZ() * z);
    }

    public Vector3 multiply(Vector3 vec) {
        return multiply(vec.getX(), vec.getY(), vec.getZ());
    }

    public Vector3 multiply(float value) {
        return multiply(value, value, value);
    }

    public Vector3 divide(float value) {
        return divide(value, value, value);
    }

    public Vector3 divide(float x, float y, float z) {
        return new Vector3(getX() / x, getY() / y, getZ() / z);
    }

    public Vector3 negate() {
        return multiply(-1F);
    }

    @Override
    public String toString() {
        return String.format("Vector3[%s %s %s]", this.x, this.y, this.z);
    }
}
