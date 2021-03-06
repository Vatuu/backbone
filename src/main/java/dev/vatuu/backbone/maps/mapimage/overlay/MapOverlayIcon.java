package dev.vatuu.backbone.maps.mapimage.overlay;

import dev.vatuu.backbone.maps.mapimage.MapImageOverlay;
import net.minecraft.util.Identifier;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class MapOverlayIcon extends MapImageOverlay {

    private int x, y;
    private Icons icon;
    private int scale;

    public MapOverlayIcon(Icons icon, int x, int y, int scale, Identifier key) {
        super(key, false);
        this.x = x;
        this.y = y;
        this.icon = icon;
        this.scale = scale;
    }

    public MapOverlayIcon(Icons icon, int x, int y, Identifier key) {
        this(icon, x, y, 1, key);
    }

    public void render(BufferedImage base) {
        Graphics2D g = base.createGraphics();
        BufferedImage img = getScaledImage(this.icon.getImage(), scale);
        g.drawImage(img, this.x, this.y, img.getWidth(), img.getHeight(), null);
        g.dispose();
    }

    public void changeIcon(Icons icon) {
        this.icon = icon;
        //this.demandRerender();
    }

    public enum Icons {
        COAL_48("icons/coal48.png"),
        IRON_48("icons/iron48.png"),
        REDSTONE_48("icons/redstone48.png"),
        GOLD_48("icons/gold48.png"),
        DIAMOND_48("icons/redstone48.png");

        private final String path;

        Icons(String path) {
            this.path = path;
        }

        private static final Map<Icons, BufferedImage> cachedIcons = new HashMap<>();

        public BufferedImage getImage() {
            if(cachedIcons.containsKey(this))
                return cachedIcons.get(this);
            BufferedImage img = null;
            try (InputStream s = this.getClass().getClassLoader().getResourceAsStream(path)){
                img = ImageIO.read(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
            cachedIcons.put(this, img);
            return img;
        }
    }
}
