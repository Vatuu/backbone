package dev.vatuu.backbone.gamestate;

import net.minecraft.util.Identifier;

public interface GameState {

    Identifier getId();

    void onStart();
    void onTick();
    void onEnd();
}
