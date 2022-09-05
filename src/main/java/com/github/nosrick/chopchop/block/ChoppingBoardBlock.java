package com.github.nosrick.chopchop.block;

import com.github.nosrick.chopchop.ChopChopMod;
import com.github.nosrick.chopchop.block.entity.BlockEntityTypeRegistry;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class ChoppingBoardBlock extends BlockWithEntity {

    public ChoppingBoardBlock() {
        super(ChopChopMod.DefaultBlockSettings
                .strength(0.5f)
                .nonOpaque()
                .sounds(BlockSoundGroup.WOOD));
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return BlockEntityTypeRegistry.CHOPPING_BOARD.get().instantiate(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
