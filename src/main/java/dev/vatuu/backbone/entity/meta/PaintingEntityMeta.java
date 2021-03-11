package dev.vatuu.backbone.entity.meta;

import dev.vatuu.backbone.entity.meta.base.DecorationEntityMeta;
import net.minecraft.entity.decoration.painting.PaintingEntity;
import net.minecraft.entity.decoration.painting.PaintingMotive;

public interface PaintingEntityMeta extends DecorationEntityMeta<PaintingEntityMeta> {

    default PaintingMotive getMotive() { return ((PaintingEntity)this).motive; }
    default PaintingEntityMeta setMotive(PaintingMotive motive) {
        ((PaintingEntity)this).motive = motive;
        return this;
    }

    default PaintingEntityMeta getMeta(PaintingEntity e) { return (PaintingEntityMeta)e; }
}
