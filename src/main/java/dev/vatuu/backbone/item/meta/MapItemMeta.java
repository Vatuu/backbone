package dev.vatuu.backbone.item.meta;

import dev.vatuu.backbone.exceptions.MapStateMissingException;
import dev.vatuu.backbone.item.ItemTags;
import dev.vatuu.backbone.maps.MapView;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;

public class MapItemMeta extends ItemMeta {

    private final MapView mapView;

    private MapItemMeta(MapView view) {
        this.mapView = view;
    }

    @Override
    protected CompoundTag createTag() {
        CompoundTag soFar =  super.createTag();

        soFar.putInt(ItemTags.MAP_ID, mapView.getId());

        return soFar;
    }

    public static class Builder extends ItemMeta.Builder {

        private Builder(MapView view) {
            super(Items.FILLED_MAP);
            this.meta = new MapItemMeta(view);
        }

        public static Builder create(MapView view) {
            return new Builder(view);
        }

        public static Builder create(int i) throws MapStateMissingException {
            return new Builder(new MapView(i));
        }
    }
}
