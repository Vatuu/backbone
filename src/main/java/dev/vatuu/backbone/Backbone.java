package dev.vatuu.backbone;

import dev.vatuu.backbone.cmd.DebugCommands;
import dev.vatuu.backbone.events.CommandRegistrationCallback;
import dev.vatuu.backbone.events.ServerLifecycleEvents;
import dev.vatuu.backbone.gamestate.GamestateManager;
import dev.vatuu.backbone.managers.BossbarManager;
import dev.vatuu.backbone.sam.SamManager;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;

public class Backbone implements DedicatedServerModInitializer {

    public static final String MOD_ID = "backbone";
    public static MinecraftServer SERVER;

    public static Identifier id(String path) { return new Identifier(MOD_ID, path); }

    @Override
    public void onInitializeServer() {
        ServerLifecycleEvents.SERVER_STARTING.register(server -> {
            SERVER = server;
            BossbarManager.init(SERVER);
            GamestateManager.init();
        });

        if(FabricLoader.getInstance().isDevelopmentEnvironment()) {
            CommandRegistrationCallback.EVENT.register(DebugCommands::registerTestCommands);
            SamManager.init();
        }
    }
}
