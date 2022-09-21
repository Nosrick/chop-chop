package com.github.nosrick.chopchop.block;

import com.github.nosrick.chopchop.ChopChopMod;
import com.github.nosrick.chopchop.block.entity.BlockEntityTypeRegistry;
import com.github.nosrick.chopchop.block.entity.ChoppingBoardBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
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
    public BlockState getPlacementState(ItemPlacementContext context) {

        return this.getDefaultState()
                .with(
                        Properties.HORIZONTAL_FACING,
                        context.getPlayerFacing().getOpposite());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(Properties.HORIZONTAL_FACING);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return BlockEntityTypeRegistry.CHOPPING_BOARD.get().instantiate(pos, state);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        if(world.isClient || hand == Hand.OFF_HAND) {
            return ActionResult.PASS;
        }

        if(!(world.getBlockEntity(pos) instanceof ChoppingBoardBlockEntity blockEntity)) {
            return ActionResult.FAIL;
        }

        ItemStack itemStack = player.getMainHandStack();
        if(itemStack.isFood()){
            blockEntity.addItemStack(itemStack);

            return ActionResult.SUCCESS;
        }
        else if(itemStack.isEmpty() && !blockEntity.isEmpty()) {
            ItemStack fromChoppingBoard = blockEntity.removeItemStack();
            player.giveItemStack(fromChoppingBoard);

            return ActionResult.SUCCESS;
        }

        return ActionResult.CONSUME;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
