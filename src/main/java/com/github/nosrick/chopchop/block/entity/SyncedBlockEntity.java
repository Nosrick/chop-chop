package com.github.nosrick.chopchop.block.entity;

import com.github.nosrick.chopchop.util.BlockStateUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class SyncedBlockEntity extends BlockEntity {

    public SyncedBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    public void inventoryChanged() {
        if (this.world != null && !this.world.isClient) {
           this.world.updateListeners(this.getPos(), this.getCachedState(), this.getCachedState(), BlockStateUtils.DEFAULT);
           this.world.updateNeighborsAlways(this.getPos(), this.getCachedState().getBlock());
        }
        this.markDirty();
    }
}
