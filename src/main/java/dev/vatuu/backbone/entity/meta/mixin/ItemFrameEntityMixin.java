package dev.vatuu.backbone.entity.meta.mixin;

import dev.vatuu.backbone.entity.meta.ItemFrameEntityMeta;
import net.minecraft.entity.decoration.ItemFrameEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ItemFrameEntity.class)
public interface ItemFrameEntityMixin extends ItemFrameEntityMeta {
    @Accessor boolean getFixed();
    @Accessor void setFixed(boolean fixed);
    @Accessor float getItemDropChance();
    @Accessor void setItemDropChance(float chance);
}
