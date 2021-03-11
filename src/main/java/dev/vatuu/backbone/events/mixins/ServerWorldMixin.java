package dev.vatuu.backbone.events.mixins;

import dev.vatuu.backbone.events.ServerTickEvents;
import net.minecraft.server.world.ServerWorld;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BooleanSupplier;

@Mixin(ServerWorld.class)
public class ServerWorldMixin {

    @Inject(method = "tick", at = @At(value = "FIELD", target = "Lnet/minecraft/server/world/ServerWorld;inBlockTick:Z", opcode = Opcodes.PUTFIELD, ordinal = 0, shift = At.Shift.AFTER))
    private void startWorldTick(BooleanSupplier shouldKeepTicking, CallbackInfo ci) {
        ServerTickEvents.START_WORLD_TICK.invoker().onStartTick((ServerWorld) (Object) this);
    }
}
