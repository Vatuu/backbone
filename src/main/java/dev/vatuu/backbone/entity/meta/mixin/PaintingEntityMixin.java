package dev.vatuu.backbone.entity.meta.mixin;

import dev.vatuu.backbone.entity.meta.PaintingEntityMeta;
import net.minecraft.entity.decoration.painting.PaintingEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PaintingEntity.class)
public interface PaintingEntityMixin extends PaintingEntityMeta { }
