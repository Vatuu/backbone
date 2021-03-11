package dev.vatuu.backbone.item;

import com.google.common.collect.Lists;
import dev.vatuu.backbone.item.meta.BannerItemMeta;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.DyeColor;

import java.util.List;

public enum NumericBanners {
        ZERO(Lists.newArrayList(
                BannerPattern.BASE, BannerPattern.STRIPE_TOP, BannerPattern.STRIPE_RIGHT, BannerPattern.STRIPE_BOTTOM,
                BannerPattern.STRIPE_LEFT, BannerPattern.BORDER),
                Lists.newArrayList(false, true, true, true, true, false)
        ),
        ONE(Lists.newArrayList(
                BannerPattern.BASE, BannerPattern.SQUARE_TOP_LEFT, BannerPattern.TRIANGLES_TOP, BannerPattern.STRIPE_CENTER,
                BannerPattern.BORDER),
                Lists.newArrayList(false, true, false, true, false)
        ),
        TWO(Lists.newArrayList(
                BannerPattern.BASE, BannerPattern.STRIPE_TOP, BannerPattern.RHOMBUS_MIDDLE, BannerPattern.STRIPE_DOWNLEFT,
                BannerPattern.STRIPE_BOTTOM, BannerPattern.BORDER),
                Lists.newArrayList(false, true, false, true, true, false)
        ),
        THREE(Lists.newArrayList(
                BannerPattern.BASE, BannerPattern.STRIPE_MIDDLE, BannerPattern.STRIPE_LEFT, BannerPattern.STRIPE_BOTTOM,
                BannerPattern.STRIPE_RIGHT, BannerPattern.STRIPE_TOP, BannerPattern.BORDER),
                Lists.newArrayList(false, true, false, true, true, true, false)
        ),
        FOUR(Lists.newArrayList(
                BannerPattern.BASE, BannerPattern.STRIPE_LEFT, BannerPattern.HALF_HORIZONTAL_MIRROR, BannerPattern.STRIPE_RIGHT,
                BannerPattern.STRIPE_MIDDLE, BannerPattern.BORDER),
                Lists.newArrayList(false, true, false, true, true, false)
        ),
        FIVE(Lists.newArrayList(
                BannerPattern.BASE, BannerPattern.STRIPE_BOTTOM, BannerPattern.STRIPE_DOWNLEFT, BannerPattern.CURLY_BORDER,
                BannerPattern.SQUARE_BOTTOM_LEFT, BannerPattern.STRIPE_TOP, BannerPattern.BORDER),
                Lists.newArrayList(false, true, true, false, true, true, false)
        ),
        SIX(Lists.newArrayList(
                BannerPattern.BASE, BannerPattern.STRIPE_BOTTOM, BannerPattern.STRIPE_RIGHT, BannerPattern.HALF_HORIZONTAL,
                BannerPattern.STRIPE_MIDDLE, BannerPattern.STRIPE_TOP, BannerPattern.STRIPE_LEFT, BannerPattern.BORDER),
                Lists.newArrayList(false, true, true, false, true, true, true, false)
        ),
        SEVEN(Lists.newArrayList(
                BannerPattern.BASE, BannerPattern.STRIPE_TOP, BannerPattern.DIAGONAL_RIGHT, BannerPattern.STRIPE_DOWNLEFT,
                BannerPattern.SQUARE_BOTTOM_LEFT, BannerPattern.BORDER),
                Lists.newArrayList(false, true, false, true, true, false)
        ),
        EIGHT(Lists.newArrayList(
                BannerPattern.BASE, BannerPattern.STRIPE_TOP, BannerPattern.STRIPE_LEFT, BannerPattern.STRIPE_MIDDLE,
                BannerPattern.STRIPE_BOTTOM, BannerPattern.STRIPE_RIGHT, BannerPattern.BORDER),
                Lists.newArrayList(false, true, true, true, true, true, false)
        ),
        NINE(Lists.newArrayList(
                BannerPattern.BASE, BannerPattern.STRIPE_LEFT, BannerPattern.HALF_HORIZONTAL_MIRROR, BannerPattern.STRIPE_MIDDLE,
                BannerPattern.STRIPE_TOP, BannerPattern.STRIPE_RIGHT, BannerPattern.STRIPE_BOTTOM, BannerPattern.BORDER),
                Lists.newArrayList(false, true, false, true, true, true, true, false));

        private final List<BannerPattern> patterns;
        private final List<Boolean> isFontPattern;

        NumericBanners(List<BannerPattern> patterns, List<Boolean> isFont) {
            this.patterns = patterns;
            this.isFontPattern = isFont;
        }

        public ItemStack getBannerItem(DyeColor background, DyeColor foreground) {
            ItemStack stack = new ItemStack(Items.WHITE_BANNER, 1);
            BannerItemMeta.Builder meta = BannerItemMeta.Builder.create();
            for (int i = 0; i < patterns.size(); i++)
                meta.addPattern(patterns.get(i), isFontPattern.get(i) ? foreground : background);
            meta.applyToStack(stack);
            return stack;
        }
}
