package com.github.nosrick.chopchop.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

public class SingleStackInventory implements SidedInventory {

    protected DefaultedList<ItemStack> inventory;

    public SingleStackInventory() {
        this.inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        return new int[0];
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        return false;
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return false;
    }

    @Override
    public int size() {
        return this.inventory.size();
    }

    @Override
    public boolean isEmpty() {
        return this.inventory.isEmpty();
    }

    @Override
    public ItemStack getStack(int slot) {
        if(this.validateSlotIndex(slot)){
            return this.inventory.get(slot);
        }

        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        ItemStack stack = this.getStack(slot);
        if(stack.isEmpty()) {
            return ItemStack.EMPTY;
        }

        int removal = Math.min(amount, stack.getMaxCount());
        stack.decrement(removal);
        this.inventory.set(slot, stack);

        return stack;
    }

    @Override
    public ItemStack removeStack(int slot) {
        ItemStack stack = this.getStack(slot);

        if(stack.isEmpty()){
            return ItemStack.EMPTY;
        }

        this.inventory.set(slot, ItemStack.EMPTY);

        return stack;
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        if (this.validateSlotIndex(slot)) {
            this.inventory.set(slot, stack);
        }
    }

    protected boolean validateSlotIndex(int slot) {
        return slot >= 0 && slot < this.inventory.size();
    }

    @Override
    public void markDirty() {

    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return false;
    }

    @Override
    public void clear() {
        this.inventory.clear();
    }

    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, this.inventory);
    }

    public NbtCompound writeNbt(NbtCompound nbt) {
        return Inventories.writeNbt(nbt, this.inventory);
    }
}
