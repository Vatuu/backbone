package dev.vatuu.backbone.item.meta;

import dev.vatuu.backbone.item.ItemTags;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.text.Text;
import net.minecraft.util.registry.Registry;

import java.util.*;

public class ItemMeta {

    private int damage = 0, customModel = 0, repairCost = 0;
    private boolean unbreakable = false;

    private Text displayName;
    private final List<Text> lore = new ArrayList<>();

    private final Map<Enchantment, Short> enchantments = new HashMap<>();

    protected CompoundTag createTag() {
        CompoundTag root = new CompoundTag();

        if(damage != 0)
            root.putInt(ItemTags.DAMAGE, damage);
        if(customModel != 0)
            root.putInt(ItemTags.CUSTOM_MODEL, customModel);
        if(repairCost != 0)
            root.putInt(ItemTags.REPAIR_COST, repairCost);
        if(unbreakable)
            root.putBoolean(ItemTags.UNBREAKABLE, true);

        CompoundTag display = createDisplayTag();
        if(display != null)
            root.put(ItemTags.DISPLAY_ROOT, display);

        ListTag enchantments = createEnchantmentTag();
        if(enchantments != null)
            root.put(ItemTags.ENCH_ROOT, enchantments);

        return root;
    }

    protected CompoundTag createDisplayTag() {
        CompoundTag display = new CompoundTag();

        if(displayName != null)
            display.putString(ItemTags.DISPLAY_NAME, displayName.asString());

        if(!lore.isEmpty()) {
            ListTag l = new ListTag();
            lore.forEach(t -> l.add(StringTag.of(t.asString())));
            display.put(ItemTags.DISPLAY_LORE, l);
        }

        return display.isEmpty() ? null : display;
    }

    private ListTag createEnchantmentTag() {
        ListTag enchs = new ListTag();
        enchantments.forEach((id, lvl) -> {
            CompoundTag e = new CompoundTag();
            e.putString(ItemTags.ENCH_ID, Objects.requireNonNull(Registry.ENCHANTMENT.getId(id)).toString());
            e.putShort(ItemTags.ENCH_LEVEL, lvl);
            enchs.add(e);
        });

        return enchs.isEmpty() ? null : enchs;
    }

    public static class Builder {

        protected ItemMeta meta;
        private final Item item;

        protected Builder(Item item) {
            this.meta = new ItemMeta();
            this.item = item;
        }

        public static Builder create(Item item) {
            return new Builder(item);
        }

        public Builder setDamage(int damage) {
            if(damage < 0)
                meta.damage = 0;
            else if(damage > item.getMaxDamage())
                meta.damage = item.getMaxDamage();
            else meta.damage = damage;
            return this;
        }

        public Builder setCustomModel(int model) {
            meta.customModel = model;
            return this;
        }

        public Builder setRepairCost(int repairCost) {
            if(repairCost < 0)
                meta.repairCost = 0;
            else
                meta.repairCost = repairCost;
            return this;
        }

        public Builder setUnbreakable(boolean unbreakable) {
            meta.unbreakable = unbreakable;
            return this;
        }

        public Builder setDisplayName(Text t) {
            meta.displayName = t;
            return this;
        }

        public Builder addLore(Text t) {
            meta.lore.add(t);
            return this;
        }

        public Builder addAchievement(Enchantment e, short level) {
            meta.enchantments.put(e, level);
            return this;
        }

        public ItemMeta build() {
            return meta;
        }

        public CompoundTag toTag() {
            return meta.createTag();
        }

        public ItemStack applyToStack(ItemStack stack) {
            stack.setTag(toTag());
            return stack;
        }
    }
}
