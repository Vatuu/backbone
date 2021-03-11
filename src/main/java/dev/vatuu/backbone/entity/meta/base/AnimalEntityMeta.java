package dev.vatuu.backbone.entity.meta.base;

import dev.vatuu.backbone.entity.meta.mixin.AnimalEntityMixin;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.UUID;

@SuppressWarnings("unchecked")
public interface AnimalEntityMeta<S extends AnimalEntityMeta<S>> extends PassiveEntityMeta<S> {

    default int getLoveTicks() { return ((AnimalEntity)this).getLoveTicks(); }
    default S setLoveTicks(int ticks) {
        ((AnimalEntity)this).setLoveTicks(ticks);
        return (S)this;
    }

    default ServerPlayerEntity getLoveCausePlayer() { return ((AnimalEntity)this).getLovingPlayer(); }
    default UUID getLoveCause() { return ((AnimalEntityMixin)this).getLovingPlayer(); }
    default S setLoveCause(UUID uuid) {
        ((AnimalEntityMixin)this).setLovingPlayer(uuid);
        return (S)this;
    }
}
