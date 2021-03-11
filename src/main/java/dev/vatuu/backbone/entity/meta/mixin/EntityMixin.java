package dev.vatuu.backbone.entity.meta.mixin;

import dev.vatuu.backbone.entity.meta.base.EntityMeta;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Entity.class)
public interface EntityMixin extends EntityMeta<EntityMixin> {
    @Accessor int getNetherPortalCooldown();
    @Accessor void setNetherPortalCooldown(int cooldown);
    @Invoker("setFlag") void setFlags(int index, boolean b);
}
