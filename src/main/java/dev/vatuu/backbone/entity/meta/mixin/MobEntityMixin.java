package dev.vatuu.backbone.entity.meta.mixin;

import dev.vatuu.backbone.entity.meta.base.MobEntityMeta;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(MobEntity.class)
public interface MobEntityMixin extends MobEntityMeta<MobEntityMixin> {
    @Invoker("getDropChance") float getChance(EquipmentSlot slot);
    @Accessor void setPersistent(boolean b);
    @Accessor Entity getHoldingEntity();
    @Accessor("lootTableSeed") long getLTableSeed();
    @Accessor("lootTableSeed") void setLTableSeed(long l);
}
