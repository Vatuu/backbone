package dev.vatuu.backbone.maps;

import java.awt.*;

public class MapCanvas {

    private byte[] data;
    private boolean hasChanged = false;

    private final MapView view;

    public MapCanvas(byte[] data, MapView view) {
        this.data = data;
        this.view = view;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        if(data.length == 128 * 128)
            this.data = data;
    }

    public byte getPixel(int x, int y) {
        if(x >= 0 && x < 128 && y >= 0 && y < 128)
            return data[y * 128 + x];
        else
            return 0;
    }

    public void setPixel(int x, int y, byte color) {
        if(x >= 0 && x < 128 && y >= 0 && y < 128) {
            int index = y * 128 + x;
            if(data[index] != color) {
                data[index] = color;
                view.makeDirty(x, y);
            }
        }
    }

    public void drawImage(Image i, int x, int y) {
        byte[] bytes = MapPallete.imageToBytes(i);

        for(int xOff = 0; xOff < i.getWidth(null); xOff++)
            for(int yOff = 0; yOff < i.getHeight(null); yOff++)
                this.setPixel(x + xOff, y + yOff, bytes[yOff * i.getWidth(null) + xOff]);
    }
}
