package dev.vatuu.backbone.gambit.states;

import dev.vatuu.backbone.gambit.Gambit;
import dev.vatuu.backbone.gambit.hud.GambitHUD;
import dev.vatuu.backbone.gamestate.TimedGamestate;
import dev.vatuu.backbone.utils.TimeFormat;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class GameplayState extends TimedGamestate {

    private static final Identifier ID =  Gambit.id("gameplay");
    private static final long TIMER = 20;

    private final GambitHUD hudLeft, hudRight;

    private int scoreLeft, scoreRight;

    public GameplayState(World leftWorld, World rightWorld) {
        super(TimeFormat.MINUTES.getTicks(TIMER), false, false);
        hudLeft = new GambitHUD(leftWorld, true);
        hudRight = new GambitHUD(rightWorld, true);
    }

    @Override
    public Identifier getId() { return ID; }

    @Override
    public void onStart() {
        this.scoreLeft = this.scoreRight = 0;
        hudLeft.show();
        hudRight.show();
    }

    @Override
    protected void tick() {
        hudLeft.setLeftScore(scoreLeft).setRightScore(scoreRight);
        hudRight.setLeftScore(scoreRight).setRightScore(scoreLeft);
    }

    @Override
    protected void onTimerEnd() {

    }

    @Override
    public void onEnd() {

    }
}
