package dev.vatuu.backbone.events;

import com.mojang.brigadier.CommandDispatcher;
import dev.vatuu.backbone.events.base.Event;
import dev.vatuu.backbone.events.base.EventFactory;
import net.minecraft.server.command.ServerCommandSource;

public interface CommandRegistrationCallback {

    Event<CommandRegistrationCallback> EVENT = EventFactory.createArrayBacked(CommandRegistrationCallback.class, callbacks -> dispatcher -> {
        for (CommandRegistrationCallback callback : callbacks)
            callback.register(dispatcher);
    });

    void register(CommandDispatcher<ServerCommandSource> dispatcher);
}
