package dev.vatuu.backbone.gambit.states;

import dev.vatuu.backbone.gambit.Gambit;
import dev.vatuu.backbone.gamestate.GameState;
import net.minecraft.util.Identifier;

public class MatchmakingState implements GameState {

    private static final Identifier ID = Gambit.id("matchmaking");

    public Identifier getId() { return ID; }

    @Override
    public void onStart() {

    }

    @Override
    public void onTick() {

    }

    @Override
    public void onEnd() {

    }
}
