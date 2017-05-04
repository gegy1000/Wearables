package net.gegy1000.wearables.server.tab;

import net.gegy1000.wearables.Wearables;
import net.gegy1000.wearables.client.ClientEventHandler;
import net.gegy1000.wearables.server.block.BlockRegistry;
import net.gegy1000.wearables.server.item.ItemRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class TabRegistry {
    public static final CreativeTabs GENERAL = new CreativeTabs(Wearables.MODID + ".general") {
        @Override
        public Item getTabIconItem() {
            return Item.getItemFromBlock(BlockRegistry.WEARABLE_FABRICATOR);
        }
    };
    public static final CreativeTabs TEMPLATES = new CreativeTabs(Wearables.MODID + ".templates") {
        private List<ItemStack> subtypes;

        @Override
        public Item getTabIconItem() {
            return ItemRegistry.WEARABLE_CHEST;
        }

        @Override
        public ItemStack getIconItemStack() {
            if (this.subtypes == null) {
                this.subtypes = new ArrayList<>();
                ItemRegistry.WEARABLE_HEAD.getSubItems(ItemRegistry.WEARABLE_HEAD, this, this.subtypes);
                ItemRegistry.WEARABLE_CHEST.getSubItems(ItemRegistry.WEARABLE_CHEST, this, this.subtypes);
                ItemRegistry.WEARABLE_LEGS.getSubItems(ItemRegistry.WEARABLE_LEGS, this, this.subtypes);
                ItemRegistry.WEARABLE_FEET.getSubItems(ItemRegistry.WEARABLE_FEET, this, this.subtypes);
            }
            return this.subtypes.get((ClientEventHandler.ticks / 20) % this.subtypes.size());
        }
    };
}
