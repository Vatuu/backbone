package dev.vatuu.backbone.entity.meta.mixin;

import dev.vatuu.backbone.entity.meta.EndCrystalEntityMeta;
import net.minecraft.entity.decoration.EndCrystalEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EndCrystalEntity.class)
public interface EndCrystalEntityMixin extends EndCrystalEntityMeta { }
