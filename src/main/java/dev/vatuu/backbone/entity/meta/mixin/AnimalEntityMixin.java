package dev.vatuu.backbone.entity.meta.mixin;

import dev.vatuu.backbone.entity.meta.base.AnimalEntityMeta;
import net.minecraft.entity.passive.AnimalEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.UUID;

@Mixin(AnimalEntity.class)
public interface AnimalEntityMixin extends AnimalEntityMeta<AnimalEntityMixin> {
    @Accessor UUID getLovingPlayer();
    @Accessor void setLovingPlayer(UUID uuid);
}
