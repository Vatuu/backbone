package dev.vatuu.backbone.entity.meta;

import dev.vatuu.backbone.entity.meta.base.DecorationEntityMeta;
import dev.vatuu.backbone.entity.meta.mixin.ItemFrameEntityMixin;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.item.ItemStack;

public interface ItemFrameEntityMeta extends DecorationEntityMeta<ItemFrameEntityMeta> {

    default ItemStack getItem() { return ((ItemFrameEntity)this).getHeldItemStack(); }
    default ItemFrameEntityMeta setItem(ItemStack stack) {
        ((ItemFrameEntity)this).setHeldItemStack(stack, true);
        return this;
    }

    default boolean isFixated() { return ((ItemFrameEntityMixin)this).getFixed(); }
    default ItemFrameEntityMeta fixate(boolean fixed) {
        ((ItemFrameEntityMixin)this).setFixed(fixed);
        return this;
    }

    default float getDropChance() { return ((ItemFrameEntityMixin)this).getItemDropChance(); }
    default ItemFrameEntityMeta setDropChance(float chance) {
        ((ItemFrameEntityMixin)this).setItemDropChance(chance);
        return this;
    }

    default Rotation getContentRotation() { return Rotation.fromByte((byte)((ItemFrameEntity)this).getRotation()); }
    default ItemFrameEntityMeta setContentRotation(Rotation r) {
        ((ItemFrameEntity)this).setRotation(r.getByte());
        return this;
    }

    enum Rotation {
        DEGREES_0((byte)0),
        DEGREES_45((byte)1),
        DEGREES_90((byte)2),
        DEGREES_135((byte)3),
        DEGREES_180((byte)4),
        DEGREES_225((byte)5),
        DEGREES_270((byte)6),
        DEGREES_315((byte)7),
        DEGREES_360((byte)8);

        private final byte value;

        Rotation(byte b) { this.value = b; }

        public byte getByte() { return this.value; }

        public static Rotation fromByte(byte b) { return Rotation.values()[b % 8]; }
    }
}
