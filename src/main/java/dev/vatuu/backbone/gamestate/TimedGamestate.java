package dev.vatuu.backbone.gamestate;

public abstract class TimedGamestate implements GameState {

    protected long ticks;
    protected long passedTicks;
    protected boolean shouldLoop;
    private boolean paused;

    public TimedGamestate(long amount, boolean shouldLoop, boolean startPaused) {
        this.ticks = amount;
        this.passedTicks = 0;
        this.shouldLoop = shouldLoop;
        this.paused = startPaused;
    }

    protected abstract void tick();
    protected abstract void onTimerEnd();

    @Override
    public void onTick() {
        if(passedTicks < ticks && !paused) {
            passedTicks++;
            if(passedTicks == ticks) {
                onTimerEnd();
                if(shouldLoop)
                    passedTicks = 0;
            }
        }

        tick();
    }

    public long getTimerLength() {
        return ticks;
    }

    public void setTimerLength(long ticks) {
        this.ticks = ticks;
    }

    public long getPassedTicks() {
        return passedTicks;
    }

    public void setPassedTicks(int ticks) {
        if(ticks >= this.ticks) {
            this.onTimerEnd();
            if(shouldLoop)
                passedTicks = 0;
            else
                passedTicks = this.ticks;
        } else
            passedTicks = ticks;
    }

    public boolean isLooping() {
        return shouldLoop;
    }

    public void setLooping(boolean shouldLoop) {
        this.shouldLoop = shouldLoop;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }
}
