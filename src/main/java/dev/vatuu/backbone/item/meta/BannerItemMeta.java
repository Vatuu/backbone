package dev.vatuu.backbone.item.meta;

import com.mojang.datafixers.util.Pair;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.DyeColor;

import java.util.List;

public interface BannerItemMeta extends ItemMeta<BannerItemMeta> {

    List<Pair<BannerPattern, DyeColor>> getPatterns();

    BannerItemMeta setPattern(List<Pair<BannerPattern, DyeColor>> patterns);

    BannerItemMeta addPattern(BannerPattern pattern, DyeColor color);

    BannerItemMeta insertPattern(int index, BannerPattern pattern, DyeColor color);

    BannerItemMeta removePattern(int index);

    static Item byColor(DyeColor color) {
        switch(color) {
            case ORANGE:
                return Items.ORANGE_BANNER;
            case MAGENTA:
                return Items.MAGENTA_BANNER;
            case LIGHT_BLUE:
                return Items.LIGHT_BLUE_BANNER;
            case YELLOW:
                return Items.YELLOW_BANNER;
            case LIME:
                return Items.LIME_BANNER;
            case PINK:
                return Items.PINK_BANNER;
            case GRAY:
                return Items.GRAY_BANNER;
            case LIGHT_GRAY:
                return Items.LIGHT_GRAY_BANNER;
            case CYAN:
                return Items.CYAN_BANNER;
            case PURPLE:
                return Items.PURPLE_BANNER;
            case BLUE:
                return Items.BLUE_BANNER;
            case BROWN:
                return Items.BROWN_BANNER;
            case GREEN:
                return Items.GREEN_BANNER;
            case RED:
                return Items.RED_BANNER;
            case BLACK:
                return Items.BLACK_BANNER;
            default:
                return Items.WHITE_BANNER;
        }
    }

    static BannerPattern patternById(String id) {
        for(BannerPattern b : BannerPattern.values())
            if(b.getId().equals(id))
                return b;

        return null;
    }
}


