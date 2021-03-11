package dev.vatuu.backbone.utils;

import com.mojang.datafixers.util.Pair;

public class StringUtils {

    @SafeVarargs
    public static String formatString(String mask, Pair<String, String>... values) {
        String s = mask;
        for (Pair<String, String> e : values)
            s = s.replace(e.getFirst(), e.getSecond());
        return s;
    }
}
