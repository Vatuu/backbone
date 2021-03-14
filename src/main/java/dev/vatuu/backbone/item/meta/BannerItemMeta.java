package dev.vatuu.backbone.item.meta;

import com.mojang.datafixers.util.Pair;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.util.DyeColor;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public interface BannerItemMeta extends ItemMeta<BannerItemMeta> {

    List<Pair<BannerPattern, DyeColor>> getPatterns();

    BannerItemMeta setPattern(List<Pair<BannerPattern, DyeColor>> patterns);

    BannerItemMeta addPattern(BannerPattern pattern, DyeColor color);

    BannerItemMeta insertPattern(int index, BannerPattern pattern, DyeColor color);

    BannerItemMeta removePattern(int index);

    static BannerPattern patternById(String id) {
        for(BannerPattern b : BannerPattern.values())
            if(b.getId().equals(id))
                return b;

        return null;
    }
}


