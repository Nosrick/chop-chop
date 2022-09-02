package com.github.nosrick.chopchop.block;

import com.github.nosrick.chopchop.ChopChopMod;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class ChoppingBoardBlock extends BlockWithEntity {

    public ChoppingBoardBlock() {
        super(ChopChopMod.DefaultBlockSettings
                .strength(0.5f)
                .nonOpaque());
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return null;
    }
}
