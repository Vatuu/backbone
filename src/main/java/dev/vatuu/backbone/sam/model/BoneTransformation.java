package dev.vatuu.backbone.sam.model;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class BoneTransformation {

    private double x, y, z;
    private double prevX, prevY, prevZ;
    private float pitch, yaw, roll;
    private float prevPitch, prevYaw, prevRoll;

    public BoneTransformation(Vec3d position) {
        this(position.x, position.y, position.z, 0, 0, 0);
    }

    public BoneTransformation(double x, double y, double z) {
        this(x, y, z, 0, 0, 0);
    }

    public BoneTransformation(Vec3d position, float pitch, float yaw, float roll) {
        this(position.getX(), position.getY(), position.getZ(), pitch, yaw, roll);
    }

    public BoneTransformation(double x, double y, double z, float pitch, float yaw, float roll) {
        this.x = this.prevX = x;
        this.y = this.prevY = y;
        this.z = this.prevZ = z;
        this.pitch = this.prevPitch = pitch;
        this.yaw = this.prevYaw = yaw;
        this.roll = this.prevRoll = roll;
    }

    public Vec3d getPosition() {
        return new Vec3d(this.x, this.y, this.z);
    }

    public Vec3d getPosition(double delta) {
        this.prevX = MathHelper.lerp(delta, this.prevX, this.x);
        this.prevY = MathHelper.lerp(delta, this.prevY, this.y);
        this.prevZ = MathHelper.lerp(delta, this.prevZ, this.z);
        return this.getPrevPosition();
    }

    public Vec3d getPrevPosition() {
        return new Vec3d(this.prevX, this.prevY, this.prevZ);
    }

    public void setPosition(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setPosition(Vec3d pos) {
        this.x = pos.getX();
        this.y = pos.getY();
        this.z = pos.getZ();
    }

    public float getPitch() {
        return pitch;
    }

    public float getPitch(double delta) {
        return (this.prevPitch = (float)MathHelper.lerp(delta, prevPitch, pitch));
    }

    public float getPrevPitch() {
        return prevPitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getYaw(double delta) {
        return (this.prevYaw = (float)MathHelper.lerp(delta, prevYaw, yaw));
    }

    public float getPrevYaw() {
        return prevYaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getRoll() {
        return roll;
    }

    public float getRoll(double delta) {
        return (this.prevRoll = (float)MathHelper.lerp(delta, prevRoll, roll));
    }

    public float getPrevRoll() {
        return prevRoll;
    }

    public void setRoll(float roll) {
        this.roll = roll;
    }

    public static final Codec<BoneTransformation> CODEC = RecordCodecBuilder.create(i -> i.group(
       Codec.DOUBLE.listOf().fieldOf("pos").forGetter((BoneTransformation o) -> Lists.newArrayList(o.x, o.y, o.z)),
       Codec.FLOAT.listOf().fieldOf("rot").forGetter((BoneTransformation o) -> Lists.newArrayList(o.pitch, o.yaw, o.roll))
    ).apply(i, (pos, rot) -> new BoneTransformation(pos.get(0), pos.get(1), pos.get(2), rot.get(0), rot.get(1), rot.get(2))));
}
