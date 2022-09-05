package com.github.nosrick.chopchop;

import com.github.nosrick.chopchop.registry.BlockRegistry;
import com.github.nosrick.chopchop.block.entity.BlockEntityTypeRegistry;
import com.github.nosrick.chopchop.registry.ItemRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChopChopMod implements ModInitializer {

	public static final String MOD_ID = "chopchop";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
			new Identifier(MOD_ID, "main"),
			() -> new ItemStack(Items.COOKED_PORKCHOP));

	public static Identifier getId(String name) {
		return new Identifier(MOD_ID, name);
	}

	public static FabricItemSettings DefaultItemSettings = new FabricItemSettings().group(ITEM_GROUP);
	public static FabricBlockSettings DefaultBlockSettings = FabricBlockSettings.of(Material.WOOD);

	@Override
	public void onInitialize() {
		BlockRegistry.registerAll();
		BlockEntityTypeRegistry.registerAll();
		ItemRegistry.registerAll();
	}
}
