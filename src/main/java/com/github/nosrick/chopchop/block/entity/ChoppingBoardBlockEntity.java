package com.github.nosrick.chopchop.block.entity;

import com.github.nosrick.chopchop.util.SingleStackInventory;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

public class ChoppingBoardBlockEntity extends SyncedBlockEntity {

    protected final SingleStackInventory inventory;

    public ChoppingBoardBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityTypeRegistry.CHOPPING_BOARD.get(), pos, state);
        this.inventory = new SingleStackInventory();
    }

    public boolean addItemStack(ItemStack itemStack) {
        if(this.isEmpty() && !itemStack.isEmpty()) {
            this.inventory.setStack(0, itemStack.split(1));
            this.inventoryChanged();

            return true;
        }

        return false;
    }

    public ItemStack removeItemStack() {
        if(!this.isEmpty()) {
            ItemStack itemStack = this.inventory.removeStack(0);
            this.inventoryChanged();
            return itemStack;
        }

        return ItemStack.EMPTY;
    }

    public boolean isEmpty() { return this.inventory.getStack(0).isEmpty(); }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        NbtCompound inventory = nbt.getCompound("Inventory");
        this.inventory.readNbt(inventory);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.put("Inventory", this.inventory.writeNbt());
    }
}
