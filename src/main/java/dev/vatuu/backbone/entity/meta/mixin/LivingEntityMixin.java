package dev.vatuu.backbone.entity.meta.mixin;

import dev.vatuu.backbone.entity.meta.base.LivingEntityMeta;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(LivingEntity.class)
public interface LivingEntityMixin extends LivingEntityMeta<LivingEntityMixin> {
    @Accessor void setLastAttackedTime(int timestamp);
}
