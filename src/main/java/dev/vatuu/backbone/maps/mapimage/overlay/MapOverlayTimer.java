package dev.vatuu.backbone.maps.mapimage.overlay;

import dev.vatuu.backbone.maps.mapimage.MapImageOverlay;
import dev.vatuu.backbone.utils.TimeFormat;
import net.minecraft.util.Identifier;

import java.awt.image.BufferedImage;

public class MapOverlayTimer extends MapImageOverlay {

    private long ticks = 0;
    private String timeString = "0:00";

    private final int scale;

    public MapOverlayTimer(Identifier key, int scale) {
        super(key, true);
        this.scale = scale;
    }

    @Override
    public void render(BufferedImage base) {
        this.drawStringCentered(base, base.getWidth() / 2, base.getHeight() / 2, timeString, 0x00000000, scale);
    }

    @Override
    public void onTick() {
        if(ticks % 20 == 0)
            updateText();
        ticks++;
    }

    private void updateText() {
        timeString = TimeFormat.MINUTES.getFormattedString(ticks);
        //demandRerender();
    }
}
