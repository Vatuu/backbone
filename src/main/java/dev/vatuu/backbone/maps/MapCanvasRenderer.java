package dev.vatuu.backbone.maps;

import net.minecraft.server.network.ServerPlayerEntity;

public interface MapCanvasRenderer {

    default void init(MapView view) { }

    boolean isPlayerSpecific(MapView map);
    void render(MapView map, MapCanvas canvas, ServerPlayerEntity currentPlayer);
}
