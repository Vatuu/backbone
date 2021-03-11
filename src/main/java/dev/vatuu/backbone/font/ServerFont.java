package dev.vatuu.backbone.font;

import java.util.HashMap;

public class ServerFont {

    private final HashMap<Character, FontChar> chars = new HashMap<>();
    private int height;

    public FontChar getChar(char c) {
        return chars.get(c);
    }

    public void setChar(char c, FontChar sprite) {
        chars.put(c, sprite);
        if(sprite.getHeight() > height)
            height = sprite.getHeight();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth(String text) {
        int length = 0;
        for(String s : text.split("\n")) {
            int t = 0;
            for (int i = 0; i < s.length(); i++)
                t += getChar(s.charAt(i)).getWidth();
            if(t > length)
                length = t;
        }
        return length;
    }

    public boolean isValid(String text) {
        for(int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if(c != 167 && c != '\n' && chars.get(c) == null)
                return false;
        }

        return true;
    }
}
