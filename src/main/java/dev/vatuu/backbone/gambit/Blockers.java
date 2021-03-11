package dev.vatuu.backbone.gambit;

import dev.vatuu.backbone.gambit.hud.GambitUnicode;

public enum Blockers {
    SMALL(GambitUnicode.BAR_BLOCKER_SMALL),
    MEDIUM(GambitUnicode.BAR_BLOCKER_MEDIUM),
    LARGE(GambitUnicode.BAR_BLOCKER_LARGE);

    private final char unicode;

    Blockers(char unicode) {
        this.unicode = unicode;
    }

    public char getUnicode() {
        return unicode;
    }
}
