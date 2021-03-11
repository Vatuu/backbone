package dev.vatuu.backbone.gamestate;

import dev.vatuu.backbone.events.ServerTickEvents;

import java.util.LinkedList;

public class GamestateManager {

    public static GamestateManager INSTANCE;

    private final LinkedList<GameState> stateQueue;
    private GameState currentState;
    private int index;

    private ServerTickEvents.StartTick tickListener;

    private GamestateManager() {
        this.stateQueue = new LinkedList<>();
        this.currentState = null;
        this.index = 0;
    }

    public static void init() {
        INSTANCE = new GamestateManager();
    }

    public void addState(GameState state) {
        stateQueue.add(state);
    }

    public void begin() {
        if(stateQueue.isEmpty() || this.index >= stateQueue.size())
            return;

        this.currentState = stateQueue.get(this.index);

        this.currentState.onStart();
        this.tickListener = ServerTickEvents.START_SERVER_TICK.register(s -> this.currentState.onTick());
    }

    public void end() {
        if(currentState == null)
            return;

        this.currentState.onEnd();
        this.currentState = null;
        ServerTickEvents.START_SERVER_TICK.unregister(this.tickListener);
        this.tickListener = null;
    }

    public void proceed() {
        if(index + 1 >= stateQueue.size() || currentState == null)
            return;

        end();
        this.index++;
        begin();
    }

    public void backtrack() {
        if(index - 1 < 0 || currentState == null)
            return;

        end();
        this.index--;
        begin();
    }

    public void reset(boolean restart) {
        if(currentState != null)
            end();

        this.index = 0;
        if(restart)
            begin();
    }

    public void purge() {
        reset(false);
        this.stateQueue.clear();
    }
}
