package dev.vatuu.backbone.maps.mapimage;

import dev.vatuu.backbone.maps.MapCanvas;
import dev.vatuu.backbone.maps.MapCanvasRenderer;
import dev.vatuu.backbone.maps.MapView;
import net.minecraft.server.network.ServerPlayerEntity;

import java.awt.image.BufferedImage;

public class MapImageRenderer implements MapCanvasRenderer {

    private BufferedImage image;
    private final int x, y;
    private final MapImage map;

    public MapImageRenderer(int x, int y, MapImage map) {
        this.x = x;
        this.y = y;
        this.map = map;
    }

    @Override
    public boolean isPlayerSpecific(MapView map) {
        return false;
    }

    @Override
    public void render(MapView m, MapCanvas canvas, ServerPlayerEntity p) {
        BufferedImage fetched = map.getImagePart(this.x, this.y);
        if(image == null || !image.equals(fetched)) {
            this.image = fetched;
            canvas.drawImage(fetched, 0, 0);
        }
    }
}