package dev.vatuu.backbone.entity.meta.base;

import dev.vatuu.backbone.entity.meta.mixin.DecorationEntityMixin;
import net.minecraft.entity.decoration.AbstractDecorationEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

@SuppressWarnings("unchecked")
public interface DecorationEntityMeta<S extends DecorationEntityMeta<S>> extends EntityMeta<S> {

    default BlockPos getAttachmentPos() { return ((AbstractDecorationEntity)this).getDecorationBlockPos(); }
    default S setAttachmentPos(BlockPos p) {
        ((AbstractDecorationEntity)this).updatePosition(p.getX(), p.getY(), p.getZ());
        return (S)this;
    }
    default S setAttachmentPos(double x, double y, double z) {
        ((AbstractDecorationEntity)this).updatePosition(x, y, z);
        return (S)this;
    }

    default Direction getDirection() { return ((DecorationEntityMixin)this).getFacing(); }
    default S setDirection(Direction dir) {
        ((DecorationEntityMixin)this).setDir(dir);
        return (S)this;
    }
}
