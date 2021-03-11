package dev.vatuu.backbone.events;

import dev.vatuu.backbone.events.base.Event;
import dev.vatuu.backbone.events.base.EventFactory;
import net.minecraft.resource.ServerResourceManager;
import net.minecraft.server.MinecraftServer;

public final class ServerLifecycleEvents {

    private ServerLifecycleEvents() { }

    public static final Event<ServerStarting> SERVER_STARTING = EventFactory.createArrayBacked(ServerStarting.class, callbacks -> server -> {
        for (ServerStarting callback : callbacks)
            callback.onServerStarting(server);
    });

    public static final Event<ServerStarted> SERVER_STARTED = EventFactory.createArrayBacked(ServerStarted.class, (callbacks) -> (server) -> {
        for (ServerStarted callback : callbacks)
            callback.onServerStarted(server);
    });

    public static final Event<ServerStopping> SERVER_STOPPING = EventFactory.createArrayBacked(ServerStopping.class, (callbacks) -> (server) -> {
        for (ServerStopping callback : callbacks)
            callback.onServerStopping(server);
    });

    public static final Event<ServerStopped> SERVER_STOPPED = EventFactory.createArrayBacked(ServerStopped.class, callbacks -> server -> {
        for (ServerStopped callback : callbacks)
            callback.onServerStopped(server);
    });

    public static final Event<StartDataPackReload> START_DATA_PACK_RELOAD = EventFactory.createArrayBacked(StartDataPackReload.class, callbacks -> (server, serverResourceManager) -> {
        for (StartDataPackReload callback : callbacks)
            callback.startDataPackReload(server, serverResourceManager);
    });

    public static final Event<EndDataPackReload> END_DATA_PACK_RELOAD = EventFactory.createArrayBacked(EndDataPackReload.class, callbacks -> (server, serverResourceManager, success) -> {
        for (EndDataPackReload callback : callbacks)
            callback.endDataPackReload(server, serverResourceManager, success);
    });

    @FunctionalInterface
    public interface ServerStarting {
        void onServerStarting(MinecraftServer server);
    }

    @FunctionalInterface
    public interface ServerStarted {
        void onServerStarted(MinecraftServer server);
    }

    @FunctionalInterface
    public interface ServerStopping {
        void onServerStopping(MinecraftServer server);
    }

    @FunctionalInterface
    public interface ServerStopped {
        void onServerStopped(MinecraftServer server);
    }

    @FunctionalInterface
    public interface StartDataPackReload {
        void startDataPackReload(MinecraftServer server, ServerResourceManager serverResourceManager);
    }

    @FunctionalInterface
    public interface EndDataPackReload {
        void endDataPackReload(MinecraftServer server, ServerResourceManager serverResourceManager, boolean success);
    }
}
