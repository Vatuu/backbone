package dev.vatuu.backbone.entity.meta.mixin;

import dev.vatuu.backbone.entity.meta.ArmorStandEntityMeta;
import net.minecraft.entity.decoration.ArmorStandEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ArmorStandEntity.class)
public interface ArmorStandEntityMixin extends ArmorStandEntityMeta {
    @Invoker("setMarker") void toMarker(boolean v);
    @Invoker("setHideBasePlate") void setHideBase(boolean v);
    @Invoker("setShowArms") void setArms(boolean v);
    @Invoker("setSmall") void setSmol(boolean v);
    @Accessor("disabledSlots") int getSlots();
    @Accessor("disabledSlots") void setSlots(int slots);

}
