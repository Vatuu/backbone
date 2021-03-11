package dev.vatuu.backbone.events.mixins;

import dev.vatuu.backbone.events.ServerLifecycleEvents;
import dev.vatuu.backbone.events.ServerTickEvents;
import net.minecraft.resource.ServerResourceManager;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.function.BooleanSupplier;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {

    @Shadow private ServerResourceManager serverResourceManager;

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;setupServer()Z"), method = "runServer")
    private void beforeSetupServer(CallbackInfo info) {
        ServerLifecycleEvents.SERVER_STARTING.invoker().onServerStarting((MinecraftServer) (Object) this);
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;setFavicon(Lnet/minecraft/server/ServerMetadata;)V", ordinal = 0), method = "runServer")
    private void afterSetupServer(CallbackInfo info) {
        ServerLifecycleEvents.SERVER_STARTED.invoker().onServerStarted((MinecraftServer) (Object) this);
    }

    @Inject(at = @At("HEAD"), method = "shutdown")
    private void beforeShutdownServer(CallbackInfo info) {
        ServerLifecycleEvents.SERVER_STOPPING.invoker().onServerStopping((MinecraftServer) (Object) this);
    }

    @Inject(at = @At("TAIL"), method = "shutdown")
    private void afterShutdownServer(CallbackInfo info) {
        ServerLifecycleEvents.SERVER_STOPPED.invoker().onServerStopped((MinecraftServer) (Object) this);
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;tickWorlds(Ljava/util/function/BooleanSupplier;)V"), method = "tick")
    private void onStartTick(BooleanSupplier shouldKeepTicking, CallbackInfo ci) {
        ServerTickEvents.START_SERVER_TICK.invoker().onStartTick((MinecraftServer) (Object) this);
    }

    @Inject(at = @At("TAIL"), method = "tick")
    private void onEndTick(BooleanSupplier shouldKeepTicking, CallbackInfo info) {
        ServerTickEvents.END_SERVER_TICK.invoker().onEndTick((MinecraftServer) (Object) this);
    }

    @Inject(method = "reloadResources", at = @At("HEAD"))
    private void startResourceReload(Collection<String> collection, CallbackInfoReturnable<CompletableFuture<Void>> cir) {
        ServerLifecycleEvents.START_DATA_PACK_RELOAD.invoker().startDataPackReload((MinecraftServer) (Object) this, this.serverResourceManager);
    }

    @Inject(method = "reloadResources", at = @At("TAIL"))
    private void endResourceReload(Collection<String> collection, CallbackInfoReturnable<CompletableFuture<Void>> cir) {
        cir.getReturnValue().handleAsync((value, throwable) -> {
            // Hook into fail
            ServerLifecycleEvents.END_DATA_PACK_RELOAD.invoker().endDataPackReload((MinecraftServer) (Object) this, this.serverResourceManager, throwable == null);
            return value;
        }, (MinecraftServer) (Object) this);
    }
}
