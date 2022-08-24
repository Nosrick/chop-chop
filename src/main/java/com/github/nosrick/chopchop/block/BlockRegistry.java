package com.github.nosrick.chopchop.block;

import com.github.nosrick.chopchop.ChopChopMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;

public enum BlockRegistry {

    CHOPPING_BOARD("chopping_board", () -> new ChoppingBoardBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG)), true);

    protected final String pathName;

    protected final Supplier<Block> blockSupplier;

    protected final boolean isCutout;

    protected Block block;

    BlockRegistry(String pathName, Supplier<Block> blockSupplier, boolean isCutout) {
        this.pathName = pathName;
        this.blockSupplier = blockSupplier;
        this.isCutout = isCutout;
    }

    BlockRegistry(String pathName, Supplier<Block> blockSupplier) {
        this(pathName, blockSupplier, false);
    }

    public Block get() {
        if (this.block == null) {
            this.block = this.blockSupplier.get();
        }

        return this.block;
    }

    public String getId() {
        return Registry.BLOCK.getId(this.get()).toString();
    }

    public static void registerAll() {
        for (BlockRegistry value : values()) {
            Block block = value.get();
            Registry.register(Registry.BLOCK, ChopChopMod.getId(value.pathName), block);
        }
    }

    @Environment(EnvType.CLIENT)
    public static void registerRenderLayer() {
        for (BlockRegistry value : values()) {
            if (value.isCutout) {
                BlockRenderLayerMap.INSTANCE.putBlock(value.get(), RenderLayer.getCutout());
            }
        }
    }
}
