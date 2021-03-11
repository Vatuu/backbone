package dev.vatuu.backbone.entity.meta.base;

import com.mojang.datafixers.util.Either;
import dev.vatuu.backbone.entity.meta.mixin.MobEntityMixin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.decoration.AbstractDecorationEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.Optional;
import java.util.UUID;

@SuppressWarnings("unchecked")
public interface MobEntityMeta<S extends MobEntityMeta<S>> extends LivingEntityMeta<S> {

    default boolean requiresPersistence() { return ((MobEntity)this).isPersistent(); }
    default S setPersistence(boolean set) {
        ((MobEntityMixin)this).setPersistent(set);
        return (S)this;
    }

    default boolean canPickUpLoot() { return ((MobEntity)this).canPickUpLoot(); }
    default S setPickUpLoot(boolean can) {
        ((MobEntity)this).setCanPickUpLoot(can);
        return (S)this;
    }

    default float getEquippedDropChance(EquipmentSlot slot) { return ((MobEntityMixin)this).getChance(slot); }
    default S setDropChance(EquipmentSlot slot, float chance) {
        if(chance < 0F)
            ((MobEntity)this).setEquipmentDropChance(slot, 0);
        else if(chance > 1.0F)
            ((MobEntity)this).setEquipmentDropChance(slot, 1.0F);
        else
            ((MobEntity)this).setEquipmentDropChance(slot, chance);
        return (S)this;
    }

    default Optional<Either<UUID, BlockPos>> getLeash() {
        Entity e = ((MobEntityMixin)this).getHoldingEntity();
        if(e == null)
            return Optional.empty();
        else {
            if(e instanceof AbstractDecorationEntity)
                return Optional.of(Either.right(((DecorationEntityMeta)e).getAttachmentPos()));
            else
                return Optional.of(Either.left(((EntityMeta)e).getUuid()));
        }
    }
    //TODO Set leashed

    default boolean isLeftHanded() { return ((MobEntity)this).isLeftHanded(); }
    default S setLeftHanded(boolean left) {
        ((MobEntity)this).setLeftHanded(left);
        return (S)this;
    }

    default Identifier getLootTable() { return ((MobEntity)this).getLootTable(); }
    default S setLootTable(Identifier id) {
        ((MobEntity)this).getLootTable();
        return (S)this;
    }

    default long getLootTableSeed() { return ((MobEntityMixin)this).getLTableSeed(); }
    default S setLootTableSeed(long seed) {
        ((MobEntityMixin)this).setLTableSeed(seed);
        return (S)this;
    }

    default boolean hasNoAI() { return ((MobEntity)this).isAiDisabled(); }
    default S setNoAI(boolean noAi) {
        ((MobEntity)this).setAiDisabled(noAi);
        return (S)this;
    }
}
