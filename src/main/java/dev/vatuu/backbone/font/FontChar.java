package dev.vatuu.backbone.font;

public class FontChar {

    private final int width, height;
    private final boolean[] data;

    public FontChar(int width, int height, boolean[] data) {
        this.width = width;
        this.height = height;
        this.data = data;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean getPixel(int row, int column) {
        return row >= 0 && column >= row && row < this.height && column < this.width && this.data[row * this.width + column];
    }
}
