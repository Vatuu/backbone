package dev.vatuu.backbone.maps.mapimage;

import dev.vatuu.backbone.font.FontChar;
import dev.vatuu.backbone.font.MinecraftFont;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public abstract class MapImageOverlay {

    private final static Map<Pair<Character, Integer>, BufferedImage> characterCache = new HashMap<>();

    private final Identifier image;

    public MapImageOverlay(Identifier key, boolean shouldTick) {
        this.image = key;
    }

    public abstract void render(BufferedImage base);
    public void onTick() { }

    protected int getStringWidth(String text, int scale) {
        return MinecraftFont.FONT.getWidth(text) * scale;
    }

    protected int getStringWidth(String text) {
        return this.getStringWidth(text, 1);
    }

    protected void drawStringCentered(int x, int y, BufferedImage img, String text, int colour) {
        this.drawStringCentered(img, x, y, text, colour, 1);
    }

    protected void drawStringCentered(BufferedImage img, int x, int y, String text, int colour, int scale) {
        int xAdj = x - getStringWidth(text, scale) / 2;
        int yAdj = y - (MinecraftFont.FONT.getHeight() * scale) / 2;
        this.drawString(img, xAdj, yAdj, text, colour, scale);
    }

    protected void drawString(BufferedImage img, int x, int y, String text, int colour) {
        this.drawString(img, x, y, text, colour, 1);
    }

    protected void drawString(BufferedImage img, int x, int y, String text, int colour, int scale) {
        char[] chars = text.toCharArray();
        int xStart = x;
        int yStart = y;
        for(char c : chars) {
            if(c == '\n') {
                int height = MinecraftFont.FONT.getChar(c).getHeight() * scale;
                yStart += height + (2 * scale);
                xStart = x;
                continue;
            }
            int width = MinecraftFont.FONT.getChar(c).getWidth() * scale;
            drawCharacter(img, xStart, yStart, c, scale, colour);
            xStart += width + (2 * scale);
        }
    }

    protected void drawCharacter(BufferedImage img, int x, int y, char c, int scale, int colour) {
        BufferedImage letter = getScaledImage(getCharacterImg(c, colour), scale);
        Graphics2D g = img.createGraphics();
        g.drawImage(letter, x, y, letter.getWidth(), letter.getHeight(), null);
        g.dispose();
    }

    private static BufferedImage getCharacterImg(char c,int colour) {
        FontChar sprite = MinecraftFont.FONT.getChar(c);
        int width = sprite.getWidth();
        int height = sprite.getHeight();
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                boolean data = sprite.getPixel(y, x);
                img.setRGB(x, y, data ? 0xFF000000 | colour : 0);
            }
        }

        return img;
    }

    protected static BufferedImage getScaledImage(BufferedImage img, int scale) {
        if(scale > 1) {
            int width = img.getWidth(); int height = img.getHeight();
            BufferedImage scaled = new BufferedImage(width * scale, height * scale, img.getType());
            Graphics2D dim = scaled.createGraphics();
            dim.drawImage(img, 0, 0, width * scale, height * scale, null);
            dim.dispose();
            return scaled;
        }
        return img;
    }
}
