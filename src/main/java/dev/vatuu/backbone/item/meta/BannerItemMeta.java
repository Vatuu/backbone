package dev.vatuu.backbone.item.meta;

import dev.vatuu.backbone.item.ItemTags;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.util.DyeColor;

import java.util.HashMap;
import java.util.Map;

public class BannerItemMeta extends ItemMeta {

    private final Map<BannerPattern, DyeColor> patterns = new HashMap<>();

    @Override
    protected CompoundTag createTag() {
        CompoundTag soFar = super.createTag();

        ListTag patterns = createPatternTag();
        if(patterns != null)
            soFar.put(ItemTags.PATTERN_ROOT, patterns);

        return soFar;
    }

    private ListTag createPatternTag() {
        ListTag pats = new ListTag();
        patterns.forEach((p, c) -> {
            CompoundTag entry = new CompoundTag();
            entry.putString(ItemTags.PATTERN_ENTRY, p.getId());
            entry.putInt(ItemTags.PATTERN_COLOR, c.getId());
            pats.add(entry);
        });

        return pats.isEmpty() ? null : pats;
    }

    public static class Builder extends ItemMeta.Builder {

        protected Builder() {
            super(Items.WHITE_BANNER);
            this.meta = new BannerItemMeta();
        }

        public static Builder create() {
            return new Builder();
        }

        public Builder addPattern(BannerPattern pattern, DyeColor color) {
            ((BannerItemMeta)meta).patterns.put(pattern, color);
            return this;
        }
    }
}
