package net.corior48.waterbaileydiscs.menu;

import net.corior48.waterbaileydiscs.block.ModBlocks;
import net.corior48.waterbaileydiscs.block.entity.MusicBlockEntity;
import net.corior48.waterbaileydiscs.common.DiscCatalog;
import net.corior48.waterbaileydiscs.item.ModItems;
import net.corior48.waterbaileydiscs.menu.ModMenuTypes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.SlotItemHandler;

public class MusicBlockMenu extends AbstractContainerMenu {
    private final ContainerLevelAccess access;
    private final ContainerData data;
    private final MusicBlockEntity blockEntity;
    private int getSelectedXpCost() {
        int selected = getSelectedRecord();
        if (selected < 0 || selected >= DiscCatalog.size()) {
            return 0;
        }
        return DiscCatalog.getXpCost(selected);
    }

    private boolean canUseAdminDiscs(Player player) {
        return player != null && player.hasPermissions(2);
    }

    private boolean isSelectableBy(Player player, int index) {
        if (index < 0 || index >= DiscCatalog.size()) {
            return false;
        }

        if (DiscCatalog.isAdminOnly(index) && !canUseAdminDiscs(player)) {
            return false;
        }

        return true;
    }

    public static final int INPUT_SLOT = 0;
    public static final int OUTPUT_SLOT = 1;

    public static final int DATA_SELECTED_RECORD = 0;
    public static final int DATA_HAS_BLANK_DISC = 1;

    public MusicBlockMenu(int containerId, Inventory playerInventory) {
        this(containerId, playerInventory, null, ContainerLevelAccess.NULL, new SimpleContainerData(2));
    }

    public MusicBlockMenu(int containerId, Inventory playerInventory, MusicBlockEntity blockEntity,
                          ContainerLevelAccess access, ContainerData data) {
        super(ModMenuTypes.MUSIC_BLOCK_MENU.get(), containerId);

        this.blockEntity = blockEntity;
        this.access = access;
        this.data = data;

        this.addDataSlots(data);

        ItemStackHandler handler = blockEntity != null
                ? blockEntity.getItemHandler()
                : new ItemStackHandler(2);

        this.addSlot(new SlotItemHandler(handler, INPUT_SLOT, 44, 24) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ModItems.BLANK_DISC.get());
            }
        });

        this.addSlot(new SlotItemHandler(handler, OUTPUT_SLOT, 116, 24) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }

            @Override
            public boolean mayPickup(Player player) {
                int xpCost = MusicBlockMenu.this.getSelectedXpCost();
                return player.isCreative() || player.experienceLevel >= xpCost;
            }

            @Override
            public void onTake(Player player, ItemStack stack) {
                int xpCost = MusicBlockMenu.this.getSelectedXpCost();

                if (!player.isCreative() && xpCost > 0) {
                    player.giveExperienceLevels(-xpCost);
                }

                if (MusicBlockMenu.this.blockEntity != null) {
                    MusicBlockMenu.this.blockEntity.consumeInputForCraft();
                    MusicBlockMenu.this.syncDataFromBlockEntity();
                }

                super.onTake(player, stack);
            }


        });


        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        syncDataFromBlockEntity();
    }

    public int getSelectedRecord() {
        return this.data.get(DATA_SELECTED_RECORD);
    }

    public boolean hasBlankDisc() {
        return this.data.get(DATA_HAS_BLANK_DISC) == 1;
    }

    public void setSelectedRecord(Player player, int index) {
        if (this.blockEntity == null) {
            return;
        }

        if (!isSelectableBy(player, index)) {
            return;
        }

        this.blockEntity.setSelectedRecord(index);
        syncDataFromBlockEntity();
        this.broadcastChanges();
    }

    private void syncDataFromBlockEntity() {
        if (this.blockEntity != null) {
            this.data.set(DATA_SELECTED_RECORD, this.blockEntity.getSelectedRecord());
            this.data.set(DATA_HAS_BLANK_DISC, this.blockEntity.hasBlankDisc() ? 1 : 0);
        }
    }

    @Override
    public void broadcastChanges() {
        syncDataFromBlockEntity();
        super.broadcastChanges();
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack copiedStack = ItemStack.EMPTY;
        Slot sourceSlot = this.slots.get(index);

        if (sourceSlot.hasItem()) {
            ItemStack sourceStack = sourceSlot.getItem();
            copiedStack = sourceStack.copy();

            if (index < 2) {
                if (!this.moveItemStackTo(sourceStack, 2, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (sourceStack.is(ModItems.BLANK_DISC.get())) {
                    if (!this.moveItemStackTo(sourceStack, INPUT_SLOT, INPUT_SLOT + 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else {
                    return ItemStack.EMPTY;
                }
            }

            if (sourceStack.isEmpty()) {
                sourceSlot.set(ItemStack.EMPTY);
            } else {
                sourceSlot.setChanged();
            }

            syncDataFromBlockEntity();
        }

        return copiedStack;
    }
    @Override
    public boolean clickMenuButton(Player player, int id) {
        if (this.blockEntity == null) {
            return false;
        }

        int size = DiscCatalog.size();
        if (size <= 0) {
            return false;
        }

        if (id >= 1000) {
            int realIndex = id - 1000;

            if (!isSelectableBy(player, realIndex)) {
                return false;
            }

            setSelectedRecord(player, realIndex);
            return true;
        }

        int selected = getSelectedRecord();

        if (id == 0) {
            for (int i = 0; i < size; i++) {
                selected = (selected - 1 + size) % size;
                if (isSelectableBy(player, selected)) {
                    setSelectedRecord(player, selected);
                    return true;
                }
            }
            return false;
        } else if (id == 1) {
            for (int i = 0; i < size; i++) {
                selected = (selected + 1) % size;
                if (isSelectableBy(player, selected)) {
                    setSelectedRecord(player, selected);
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    private void addPlayerInventory(Inventory inventory) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(inventory, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory inventory) {
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(inventory, col, 8 + col * 18, 142));
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.access, player, ModBlocks.MUSIC_BLOCK.get());
    }
}
