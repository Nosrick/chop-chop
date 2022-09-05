package com.github.nosrick.chopchop.registry;

import com.github.nosrick.chopchop.ChopChopMod;
import com.github.nosrick.chopchop.block.ChoppingBoardBlock;
import net.minecraft.block.Block;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;

public enum BlockRegistry {

    CHOPPING_BOARD("chopping_board", ChoppingBoardBlock::new, true);

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

    public boolean isCutout() {
        return this.isCutout;
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
}
