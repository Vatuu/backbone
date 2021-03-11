package dev.vatuu.backbone.managers;

import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.CommandBossBar;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.List;


public final class BossbarManager {

    public static BossbarManager INSTANCE;

    private final MinecraftServer server;

    private BossbarManager(MinecraftServer server) {
        this.server = server;
    }

    public static void init(MinecraftServer server) {
        INSTANCE = new BossbarManager(server);
    }

    public BossBar create(Identifier id, Text title) {
        return server.getBossBarManager().add(id, title);
    }

    public BossBar createForPlayers(Identifier id, Text title, List<ServerPlayerEntity> players) {
        CommandBossBar bar = server.getBossBarManager().add(id, title);
        if(players != null)
            bar.addPlayers(players);
        return bar;
    }

    @SuppressWarnings("unchecked")
    public BossBar createForWorld(Identifier id, Text title, World w) {
        return createForPlayers(id, title, (List<ServerPlayerEntity>)w.getPlayers());
    }

    public BossBar get(Identifier id) {
        return server.getBossBarManager().get(id);
    }

    public boolean destroy(Identifier id) {
        CommandBossBar bar = server.getBossBarManager().get(id);
        if(bar == null)
            return false;

        server.getBossBarManager().remove(bar);
        return true;
    }
}
