package com.github.nosrick.chopchop.block.entity;

import com.github.nosrick.chopchop.ChopChopMod;
import com.github.nosrick.chopchop.registry.BlockRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

import java.util.Arrays;
import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public enum BlockEntityTypeRegistry {

    CHOPPING_BOARD("chopping_board", ChoppingBoardBlockEntity.class, ChoppingBoardBlockEntity::new, BlockRegistry.CHOPPING_BOARD);

    protected final String pathName;

    protected final Class<? extends BlockEntity> blockEntityClass;

    protected final Supplier<BlockEntityType<? extends BlockEntity>> blockEntityTypeSupplier;

    protected BlockEntityType<? extends BlockEntity> blockEntityType;

    BlockEntityTypeRegistry(
            String pathName,
            Class<? extends BlockEntity> blockEntityClass,
            FabricBlockEntityTypeBuilder.Factory<? extends BlockEntity> blockEntityTypeSupplier,
            BlockRegistry... blockRegistries) {
        this.pathName = pathName;
        this.blockEntityClass = blockEntityClass;
        this.blockEntityTypeSupplier = () -> FabricBlockEntityTypeBuilder.create(
                        blockEntityTypeSupplier,
                        Arrays.stream(blockRegistries)
                                .map(BlockRegistry::get)
                                .toArray(Block[]::new))
                .build();
    }

    public <T extends BlockEntity> BlockEntityType<T> get() {
        if (this.blockEntityType == null) {
            this.blockEntityType = this.blockEntityTypeSupplier.get();
        }

        return (BlockEntityType<T>) this.blockEntityType;
    }

    public String getId() {
        return Registry.BLOCK_ENTITY_TYPE.getId(this.get()).toString();
    }

    public static void registerAll() {
        for(BlockEntityTypeRegistry value : values()) {
            Registry.register(Registry.BLOCK_ENTITY_TYPE, ChopChopMod.getId(value.pathName), value.get());
        }
    }
}
