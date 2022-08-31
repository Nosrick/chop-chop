package com.github.nosrick.chopchop.item;

import com.github.nosrick.chopchop.ChopChopMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;

public enum ItemRegistry {

    IRON_KNIFE("iron_knife", () -> new Item(new FabricItemSettings().maxCount(1).group(ChopChopMod.ITEM_GROUP)));

    private Item item;
    private Supplier<Item> itemSupplier;
    private String pathName;

    ItemRegistry(String pathName, Supplier<Item> itemSupplier) {
        this.pathName = pathName;
        this.itemSupplier = itemSupplier;
    }

    public static void registerAll() {
        for(ItemRegistry value : values()) {
            net.minecraft.util.registry.Registry.register(Registry.ITEM, ChopChopMod.getId(value.pathName), value.get());
        }
    }

    public Item get() {
        if(this.item == null) {
            this.item = this.itemSupplier.get();
        }

        return this.item;
    }

    public String getId() { return Registry.ITEM.getId(this.get()).toString();}
}
