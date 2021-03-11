package dev.vatuu.backbone.sam;

import dev.vatuu.backbone.Backbone;
import dev.vatuu.backbone.events.ServerTickEvents;
import dev.vatuu.backbone.sam.model.ServerAnimatableModel;
import dev.vatuu.backbone.utils.MathUtils;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

import java.util.*;
import java.util.stream.Collectors;

public final class SamManager {

    public static SamManager INSTANCE;

    private final HashMap<Identifier, ServerAnimatableModel> models;
    private final Map<Identifier, List<ServerPlayerEntity>> animateTask;

    public static void init() {
        INSTANCE = new SamManager();
    }

    private SamManager() {
        models = new HashMap<>();
        animateTask = new HashMap<>();
        registerListener();
        INSTANCE = this;
    }

    public boolean modelExists(Identifier id) {
        return models.containsKey(id);
    }

    public void register(Identifier id, ServerAnimatableModel model) {
        models.put(id, model);
    }

    public void unregister(Identifier id) {
        if(!modelExists(id))
            return;

        ServerAnimatableModel model = models.remove(id);
        List<ServerPlayerEntity> p = animateTask.remove(id);
        p.forEach(model::destroy);
    }

    public ServerAnimatableModel getModel(Identifier id) {
        return models.get(id);
    }

    public void addPlayer(Identifier id, ServerPlayerEntity p) {
        if(!modelExists(id))
            return;

        animateTask.computeIfPresent(id, (i, players) -> {
            players.add(p);
            return players;
        });
        animateTask.computeIfAbsent(id, i -> Collections.singletonList(p));
    }

    private void registerListener() {
        ServerTickEvents.START_SERVER_TICK.register(this::updateTask);
        ServerTickEvents.END_SERVER_TICK.register(this::updateTask);
    }

    private void updateTask(MinecraftServer server) {
        models.keySet().forEach(k -> animateTask.computeIfPresent(k, (id, pair) -> {
            ServerAnimatableModel model = getModel(id);
            List<ServerPlayerEntity> updated = cleanupList(pair);
            getTargets(model, updated).forEach(model::render);
            return updated;
        }));
    }

    private List<ServerPlayerEntity> getTargets(ServerAnimatableModel model, List<ServerPlayerEntity> players) {
        Vec3d modelPos = model.getPos();
        List<ServerPlayerEntity> toRender = new ArrayList<>();

        for(ServerPlayerEntity p : players)
            if(MathUtils.getDistance3D(modelPos, p.getPos()) < model.getRenderDistance())
                toRender.add(p);

        return toRender;
    }

    private List<ServerPlayerEntity> cleanupList(List<ServerPlayerEntity> list) {
        return list.stream().filter(p -> getPlayer(p.getUuid(), Backbone.SERVER) != null).collect(Collectors.toList());
    }

    private ServerPlayerEntity getPlayer(UUID uuid, MinecraftServer server) {
        return server.getPlayerManager().getPlayer(uuid);
    }
}
