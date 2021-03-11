package dev.vatuu.backbone.maps;

import com.mojang.datafixers.util.Pair;
import dev.vatuu.backbone.Backbone;
import dev.vatuu.backbone.exceptions.MapStateMissingException;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.map.MapState;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class MapView  {

    private final int id;
    private final MapState state;

    private final List<MapCanvasRenderer> renderers = new ArrayList<>();
    private final MapCanvas baseCanvas;
    private final Map<UUID, MapCanvas> mapCanvases = new HashMap<>();

    public MapView() {
        Pair<MapState, Integer> newMap = createMapState();

        this.id = newMap.getSecond();
        this.state = newMap.getFirst();

        this.baseCanvas = new MapCanvas(state.colors, this);
    }

    public MapView(int id) throws MapStateMissingException {
        MapState state = getMapState(id);

        if(state == null)
            throw new MapStateMissingException(id);

        this.id = id;
        this.state = state;

        this.baseCanvas = new MapCanvas(state.colors, this);
    }

    @Nullable
    private static MapState getMapState(int id) {
        return Backbone.SERVER.getOverworld().getMapState(FilledMapItem.getMapName(id));
    }

    public int getId() {
        return id;
    }

    public List<MapCanvasRenderer> getRenderers() {
        return renderers;
    }

    public void addRenderer(MapCanvasRenderer r) {
        if (!renderers.contains(r)) {
            this.renderers.add(r);
            r.init(this);
        }
    }

    public void makeDirty(int x, int y) {
        this.state.markDirty(x, y);
    }

    public void render(ServerPlayerEntity player) {
        if(renderers.isEmpty())
            return;

        renderers.stream().filter(r -> !r.isPlayerSpecific(this)).forEach(r -> r.render(this, baseCanvas, player));

        MapCanvas canvas = mapCanvases.compute(player.getUuid(), (k, v) -> {
            MapCanvas c = v;
            if(c == null)
                c = new MapCanvas(baseCanvas.getData(), this);
            else
                c.setData(baseCanvas.getData());
            return c;
        });

        renderers.stream().filter(r -> r.isPlayerSpecific(this)).forEach(r -> r.render(this, canvas, player));
    }

    private Pair<MapState, Integer> createMapState() {
        ServerWorld w = Backbone.SERVER.getOverworld();
        int id = w.getNextMapId();

        MapState state = new MapState(FilledMapItem.getMapName(id));
        state.init(0, 0, 0, false, false, World.OVERWORLD);
        w.putMapState(state);

        return new Pair<>(state, id);
    }
}
