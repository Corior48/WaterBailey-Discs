package net.corior48.waterbaileydiscs.block.entity;

import net.corior48.waterbaileydiscs.item.ModItems;
import net.corior48.waterbaileydiscs.common.DiscCatalog;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;

public class MusicBlockEntity extends BlockEntity {
    public static final int INPUT_SLOT = 0;
    public static final int OUTPUT_SLOT = 1;

    private final ItemStackHandler items = new ItemStackHandler(2) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();

            if (slot == INPUT_SLOT) {
                updateOutput();
            }
        }

        @Override
        public boolean isItemValid(int slot, ItemStack stack) {
            if (slot == INPUT_SLOT) {
                return stack.is(ModItems.BLANK_DISC.get());
            }
            return false;
        }
    };

    private int selectedRecord = 0;

    public MusicBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MUSIC_BE.get(), pos, state);
    }

    public ItemStackHandler getItemHandler() {
        return items;
    }

    public int getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(int selectedRecord) {
        this.selectedRecord = selectedRecord;
        updateOutput();
        setChanged();
    }

    public boolean hasBlankDisc() {
        ItemStack stack = items.getStackInSlot(INPUT_SLOT);
        return !stack.isEmpty() && stack.is(ModItems.BLANK_DISC.get());
    }

    public void updateOutput() {
        ItemStack result = ItemStack.EMPTY;

        if (hasBlankDisc() && selectedRecord >= 0 && selectedRecord < DiscCatalog.size()) {
            result = new ItemStack(DiscCatalog.get(selectedRecord));
        }

        items.setStackInSlot(OUTPUT_SLOT, result);
        setChanged();
    }

    public void consumeInputForCraft() {
        ItemStack input = items.getStackInSlot(INPUT_SLOT);
        if (!input.isEmpty() && input.is(ModItems.BLANK_DISC.get())) {
            input.shrink(1);
            items.setStackInSlot(INPUT_SLOT, input);
            updateOutput();
            setChanged();
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);

        tag.put("inventory", this.items.serializeNBT(registries));
        tag.putInt("selected_record", this.selectedRecord);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);

        if (tag.contains("inventory")) {
            this.items.deserializeNBT(registries, tag.getCompound("inventory"));
        }

        this.selectedRecord = tag.getInt("selected_record");
        updateOutput();
    }
}
