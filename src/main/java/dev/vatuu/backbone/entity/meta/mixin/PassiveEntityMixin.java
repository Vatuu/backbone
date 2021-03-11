package dev.vatuu.backbone.entity.meta.mixin;

import dev.vatuu.backbone.entity.meta.base.PassiveEntityMeta;
import net.minecraft.entity.passive.PassiveEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(PassiveEntity.class)
public interface PassiveEntityMixin extends PassiveEntityMeta<PassiveEntityMixin> {
    @Accessor("forcedAge") int getFAge();
    @Accessor("forcedAge") void setFAge(int forcedAge);
}
