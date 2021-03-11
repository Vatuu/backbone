package dev.vatuu.backbone.entity.meta.mixin;

import dev.vatuu.backbone.entity.meta.LeashKnotEntityMeta;
import net.minecraft.entity.decoration.LeashKnotEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LeashKnotEntity.class)
public interface LeashKnotEntityMixin extends LeashKnotEntityMeta { }
