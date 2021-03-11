package dev.vatuu.backbone.entity.meta;

import dev.vatuu.backbone.entity.meta.base.LivingEntityMeta;
import dev.vatuu.backbone.entity.meta.mixin.ArmorStandEntityMixin;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.util.math.EulerAngle;

public interface ArmorStandEntityMeta extends LivingEntityMeta<ArmorStandEntityMeta> {

    default int getLockedSlots() { return ((ArmorStandEntityMixin)this).getSlots(); }
    default ArmorStandEntityMeta setLockedSlots(int slots) {
        ((ArmorStandEntityMixin)this).setSlots(slots);
        return this;
    }
    //TODO Make this better

    default boolean isMarker() { return ((ArmorStandEntity)this).isMarker(); }
    default ArmorStandEntityMeta turnMarker(boolean marker) {
        ((ArmorStandEntityMixin)this).toMarker(marker);
        return this;
    }

    default boolean isBaseHidden() { return ((ArmorStandEntity)this).shouldHideBasePlate(); }
    default ArmorStandEntityMeta setBaseHidden(boolean v) {
        ((ArmorStandEntityMixin)this).setHideBase(v);
        return this;
    }

    default boolean areArmsShown() { return ((ArmorStandEntity)this).shouldShowArms(); }
    default ArmorStandEntityMeta setArmsShown(boolean v) {
        ((ArmorStandEntityMixin)this).setArms(v);
        return this;
    }

    default boolean isSmall() { return ((ArmorStandEntity)this).isSmall(); }
    default ArmorStandEntityMeta setSmall(boolean v) {
        ((ArmorStandEntityMixin)this).setSmol(v);
        return this;
    }

    default EulerAngle getBodyRotation() { return ((ArmorStandEntity)this).getBodyRotation(); }
    default ArmorStandEntityMeta setBodyRotation(EulerAngle angle) {
        ((ArmorStandEntity)this).setBodyRotation(angle);
        return this;
    }
    default ArmorStandEntityMeta setBodyRotation(float pitch, float yaw, float roll) {
        ((ArmorStandEntity)this).setBodyRotation(new EulerAngle(pitch, yaw, roll));
        return this;
    }

    default EulerAngle getHeadRotation() { return ((ArmorStandEntity)this).getHeadRotation(); }
    default ArmorStandEntityMeta setHeadRotation(EulerAngle angle) {
        ((ArmorStandEntity)this).setHeadRotation(angle);
        return this;
    }
    default ArmorStandEntityMeta setHeadRotation(float pitch, float yaw, float roll) {
        ((ArmorStandEntity)this).setHeadRotation(new EulerAngle(pitch, yaw, roll));
        return this;
    }

    default EulerAngle getLeftArmRotation() { return ((ArmorStandEntity)this).getLeftArmRotation(); }
    default ArmorStandEntityMeta setLeftArmRotation(EulerAngle angle) {
        ((ArmorStandEntity)this).setLeftArmRotation(angle);
        return this;
    }
    default ArmorStandEntityMeta setLeftArmRotation(float pitch, float yaw, float roll) {
        ((ArmorStandEntity)this).setLeftArmRotation(new EulerAngle(pitch, yaw, roll));
        return this;
    }

    default EulerAngle getRightArmRotation() { return ((ArmorStandEntity)this).getRightArmRotation(); }
    default ArmorStandEntityMeta setRightArmRotation(EulerAngle angle) {
        ((ArmorStandEntity)this).setRightArmRotation(angle);
        return this;
    }
    default ArmorStandEntityMeta setRightArmRotation(float pitch, float yaw, float roll) {
        ((ArmorStandEntity)this).setRightArmRotation(new EulerAngle(pitch, yaw, roll));
        return this;
    }

    default EulerAngle getLeftLegRotation() { return ((ArmorStandEntity)this).getLeftLegRotation(); }
    default ArmorStandEntityMeta setLeftLegRotation(EulerAngle angle) {
        ((ArmorStandEntity)this).setLeftLegRotation(angle);
        return this;
    }
    default ArmorStandEntityMeta setLeftLegRotation(float pitch, float yaw, float roll) {
        ((ArmorStandEntity)this).setLeftLegRotation(new EulerAngle(pitch, yaw, roll));
        return this;
    }

    default EulerAngle getRightLegRotation() { return ((ArmorStandEntity)this).getRightLegRotation(); }
    default ArmorStandEntityMeta setRightLegRotation(EulerAngle angle) {
        ((ArmorStandEntity)this).setRightLegRotation(angle);
        return this;
    }
    default ArmorStandEntityMeta setRightLegRotation(float pitch, float yaw, float roll) {
        ((ArmorStandEntity)this).setRightLegRotation(new EulerAngle(pitch, yaw, roll));
        return this;
    }

    enum DisabledSlots {
        NO_ADD_CHANGE_MAINHAND(1),
        NO_ADD_CHANGE_BOOTS(2),
        NO_ADD_CHANGE_LEGS(4),
        NO_ADD_CHANGE_CHEST(8),
        NO_ADD_CHANGE_HELMET(16),
        NO_ADD_CHANGE_OFFHAND(32),
        NO_REMOVE_CHANGE_MAINHAND(256),
        NO_REMOVE_CHANGE_BOOTS(512),
        NO_REMOVE_CHANGE_LEGS(1024),
        NO_REMOVE_CHANGE_CHEST(2048),
        NO_REMOVE_CHANGE_HELMET(4096),
        NO_REMOVE_CHANGE_OFFHAND(8192),
        NO_ADD_MAINHAND(65536),
        NO_ADD_BOOTS(131072),
        NO_ADD_LEGS(262144),
        NO_ADD_CHEST(524288),
        NO_ADD_HELMET(1048576),
        NO_ADD_OFFHAND(2097152);

        public static final int DISABLE_ALL = 4144896;

        private final int value;

        DisabledSlots(int number) {
            this.value = number;
        }

        public int getValue() { return value; }

        public static int getSlots(DisabledSlots... slots) {
            int r = 0;
            for(DisabledSlots s : slots)
                r |= s.getValue();
            return r;
        }
    }
}
