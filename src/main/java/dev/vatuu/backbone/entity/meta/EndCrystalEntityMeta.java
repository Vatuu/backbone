package dev.vatuu.backbone.entity.meta;

import dev.vatuu.backbone.entity.meta.base.EntityMeta;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.util.math.BlockPos;

import java.util.Optional;

public interface EndCrystalEntityMeta extends EntityMeta<EndCrystalEntityMeta> {

    default boolean hasBase() { return ((EndCrystalEntity)this).getShowBottom(); }
    default EndCrystalEntityMeta setBase(boolean base) {
        ((EndCrystalEntity)this).setShowBottom(base);
        return this;
    }

    default Optional<BlockPos> getBeamTarget() {
        BlockPos target = ((EndCrystalEntity) this).getBeamTarget();
        return target != null ? Optional.of(target) : Optional.empty();
    }
    default EndCrystalEntityMeta setBeamTarget(BlockPos pos) {
        ((EndCrystalEntity)this).setBeamTarget(pos);
        return this;
    }
    default EndCrystalEntityMeta disableBeam() {
        ((EndCrystalEntity)this).setBeamTarget(null);
        return this;
    }
}
