package dev.vatuu.backbone.entity.meta.base;

import dev.vatuu.backbone.entity.meta.mixin.EntityMixin;
import net.minecraft.entity.Entity;
import net.minecraft.text.Text;
import net.minecraft.util.Pair;
import net.minecraft.util.math.Vec3d;

import java.util.Set;
import java.util.UUID;

@SuppressWarnings("unchecked")
public interface EntityMeta<S extends EntityMeta<S>> {

    default int getEntityId() { return ((Entity)this).getEntityId(); }

    default int getAir() { return ((Entity)this).getAir(); }
    default S setAir(int air) {
        ((Entity)this).setAir(air);
        return (S)this;
    }

    default Text getCustomName() { return ((Entity)this).getCustomName(); }
    default S setCustomName(Text name) {
        ((Entity)this).setCustomName(name);
        return (S)this;
    }

    default boolean isCustomNameVisible() { return ((Entity)this).isCustomNameVisible(); }
    default S setCustomNameVisible(boolean visible) {
        ((Entity)this).setCustomNameVisible(visible);
        return (S)this;
    }

    default float getFallDistance() { return ((Entity)this).fallDistance; }
    default S setFallDistance(float distance) {
        ((Entity)this).fallDistance = distance;
        return (S)this;
    }

    default int getFireTicks() { return ((Entity)this).getFireTicks(); }
    default S setFireTicks(int ticks) {
        ((Entity)this).setFireTicks(ticks);
        return (S)this;
    }

    default boolean isGlowing() { return ((Entity)this).isGlowing(); }
    default S setGlowing(boolean glowing) {
        ((Entity)this).setGlowing(glowing);
        return (S)this;
    }

    default boolean isInvulnerable() { return ((Entity)this).isInvulnerable(); }
    default S setInvulnerable(boolean is) {
        ((Entity)this).setInvulnerable(is);
        return (S)this;
    }

    default Vec3d getVelocity() { return ((Entity)this).getVelocity(); }
    default S setVelocity(Vec3d velocity) {
        ((Entity)this).setVelocity(velocity);
        return (S)this;
    }

    default boolean hasNoGravity() { return ((Entity)this).hasNoGravity(); }
    default S setNoGravity(boolean grav) {
        ((Entity)this).setNoGravity(grav);
        return (S)this;
    }

    default boolean isOnGround() { return ((Entity)this).isOnGround(); }
    default S setOnGround(boolean ground) {
        ((Entity)this).setOnGround(ground);
        return (S)this;
    }

    default int getPortalCooldown() { return ((EntityMixin)this).getNetherPortalCooldown(); }
    default S setPortalCooldown(int cooldown) {
        ((EntityMixin)this).setNetherPortalCooldown(cooldown);
        return (S)this;
    }

    default Vec3d getPos() { return ((Entity)this).getPos(); }
    default S setPos(Vec3d pos) {
        ((Entity)this).setPos(pos.getX(), pos.getY(), pos.getZ());
        return (S)this;
    }
    default S setPos(double x, double y, double z) {
        ((Entity)this).setPos(x, y, z);
        return (S)this;
    }

    default Pair<Float, Float> getRotation() { return new Pair<>(((Entity)this).yaw, ((Entity)this).pitch); }
    default S setRotation(float yaw, float pitch) {
        ((Entity)this).yaw = yaw % 360F;
        ((Entity)this).pitch = pitch % 360F;
        return (S)this;
    }

    default boolean isSilent() { return ((Entity)this).isSilent(); }
    default S setSilent(boolean silent) {
        ((Entity)this).setSilent(silent);
        return (S)this;
    }

    default Set<String> getScoreboardTags() { return ((Entity)this).getScoreboardTags(); }
    default S setScoreboardTags(Set<String> tags) {
        ((Entity)this).getScoreboardTags().clear();
        ((Entity)this).getScoreboardTags().addAll(tags);
        return (S)this;
    }
    default S addScoreboardTag(String tag) {
        ((Entity)this).getScoreboardTags().add(tag);
        return (S)this;
    }
    default S removeScoreboardTag(String tag) {
        ((Entity)this).getScoreboardTags().remove(tag);
        return (S)this;
    }

    default boolean isInvisible() { return ((Entity)this).isInvisible(); }
    default S setInvisible(boolean b) {
        ((Entity)this).setInvisible(b);
        return (S)this;
    }

    default UUID getUuid() { return ((Entity)this).getUuid(); }

    static <S extends EntityMeta<S>> S getMeta(Entity entity) {
        return (S)entity;
    }
}
