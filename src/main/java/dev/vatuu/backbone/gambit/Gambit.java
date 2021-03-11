package dev.vatuu.backbone.gambit;

import net.minecraft.util.Identifier;

public class Gambit {

    public static final String MOD_ID = "gambit";

    public static Identifier id(String path) { return new Identifier(MOD_ID, path); }
}
