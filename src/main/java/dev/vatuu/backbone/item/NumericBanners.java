package dev.vatuu.backbone.item;

import com.google.common.collect.ImmutableMap;
import dev.vatuu.backbone.item.meta.BannerItemMeta;
import dev.vatuu.backbone.item.meta.ItemMeta;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;

import java.util.Map;

public enum NumericBanners {
        ZERO(ImmutableMap.of(BannerPattern.STRIPE_TOP, true, BannerPattern.STRIPE_RIGHT, true,
                BannerPattern.STRIPE_BOTTOM, true, BannerPattern.STRIPE_LEFT, true,
                BannerPattern.BORDER, false)),
        ONE(ImmutableMap.of(BannerPattern.SQUARE_TOP_LEFT, true, BannerPattern.TRIANGLES_TOP, false,
                BannerPattern.STRIPE_CENTER, true, BannerPattern.BORDER, false)),
        TWO(ImmutableMap.of(BannerPattern.STRIPE_TOP, true, BannerPattern.RHOMBUS_MIDDLE, false,
                BannerPattern.STRIPE_DOWNLEFT, true, BannerPattern.STRIPE_BOTTOM, true,
                BannerPattern.BORDER, false)),
        THREE(new ImmutableMap.Builder<BannerPattern, Boolean>().put(BannerPattern.STRIPE_MIDDLE, true).put(BannerPattern.STRIPE_LEFT, false)
                .put(BannerPattern.STRIPE_BOTTOM, true).put(BannerPattern.STRIPE_TOP, true)
                .put(BannerPattern.STRIPE_RIGHT, true).put(BannerPattern.BORDER, false).build()),
        FOUR(ImmutableMap.of(BannerPattern.STRIPE_LEFT, true, BannerPattern.HALF_HORIZONTAL_MIRROR, false,
                BannerPattern.STRIPE_RIGHT, true, BannerPattern.STRIPE_MIDDLE, true,
                BannerPattern.BORDER, false)),
        FIVE(new ImmutableMap.Builder<BannerPattern, Boolean>().put(BannerPattern.STRIPE_BOTTOM, true).put(BannerPattern.STRIPE_DOWNRIGHT, true)
                .put(BannerPattern.CURLY_BORDER, false).put(BannerPattern.SQUARE_BOTTOM_LEFT, true)
                .put(BannerPattern.STRIPE_TOP, true).put(BannerPattern.BORDER, false).build()),
        SIX(new ImmutableMap.Builder<BannerPattern, Boolean>().put(BannerPattern.STRIPE_BOTTOM, true).put(BannerPattern.STRIPE_RIGHT, true)
                .put(BannerPattern.HALF_HORIZONTAL, false).put(BannerPattern.STRIPE_MIDDLE, true)
                .put(BannerPattern.STRIPE_TOP, true).put(BannerPattern.STRIPE_LEFT, true)
                .put(BannerPattern.BORDER, false).build()),
        SEVEN(ImmutableMap.of(BannerPattern.STRIPE_TOP, true, BannerPattern.DIAGONAL_RIGHT, false,
                BannerPattern.STRIPE_DOWNLEFT, true, BannerPattern.SQUARE_BOTTOM_LEFT, true,
                BannerPattern.BORDER, false)),
        EIGHT(new ImmutableMap.Builder<BannerPattern, Boolean>().put(BannerPattern.STRIPE_TOP, true).put(BannerPattern.STRIPE_LEFT, true)
                .put(BannerPattern.STRIPE_MIDDLE, true).put(BannerPattern.STRIPE_BOTTOM, true)
                .put(BannerPattern.STRIPE_RIGHT, true).put(BannerPattern.BORDER, false).build()),
        NINE(new ImmutableMap.Builder<BannerPattern, Boolean>().put(BannerPattern.STRIPE_LEFT, true).put(BannerPattern.HALF_HORIZONTAL_MIRROR, false)
                .put(BannerPattern.STRIPE_MIDDLE, true).put(BannerPattern.STRIPE_TOP, true)
                .put(BannerPattern.STRIPE_RIGHT, true).put(BannerPattern.STRIPE_BOTTOM, true)
                .put(BannerPattern.BORDER, false).build());

        private final Map<BannerPattern, Boolean> patterns;

        NumericBanners(Map<BannerPattern, Boolean> patterns) {
            this.patterns = patterns;
        }

        public ItemStack getBannerItem(DyeColor background, DyeColor foreground) {
            BannerItemMeta meta = ItemMeta.of(BannerItemMeta.byColor(background));
            patterns.forEach((p, f) -> meta.addPattern(p, f ? foreground : background));
            return meta.apply();
        }

        public static NumericBanners getByNumber(int number) {
                if(number < 0)
                        return NumericBanners.ZERO;
                if(number > 9)
                        return NumericBanners.NINE;

                return NumericBanners.values()[number];
        }
}
