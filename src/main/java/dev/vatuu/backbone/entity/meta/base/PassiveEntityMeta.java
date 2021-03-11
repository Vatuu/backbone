package dev.vatuu.backbone.entity.meta.base;

import dev.vatuu.backbone.entity.meta.mixin.PassiveEntityMixin;
import net.minecraft.entity.passive.PassiveEntity;

@SuppressWarnings("unchecked")
public interface PassiveEntityMeta<S extends PassiveEntityMeta<S>> extends LivingEntityMeta<S> {

    default int getAge() { return ((PassiveEntity)this).getBreedingAge(); }
    default S setAge(int age) {
        ((PassiveEntity)this).setBreedingAge(age);
        return (S)this;
    }

    default int getForcedAge() { return ((PassiveEntityMixin)this).getFAge(); }
    default S setForcedAge(int ticks) {
        ((PassiveEntityMixin)this).setFAge(ticks);
        return (S)this;
    }
}
