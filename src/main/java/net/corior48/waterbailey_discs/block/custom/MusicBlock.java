package net.corior48.waterbailey_discs.block.custom;

import net.corior48.waterbailey_discs.block.entity.MusicBlockEntity;
import net.corior48.waterbailey_discs.screen.custom.MusicBlockMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class MusicBlock extends Block implements EntityBlock {
    public MusicBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos,
                                               Player player, BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);

            if (blockEntity instanceof MusicBlockEntity musicBlockEntity) {
                MenuProvider menuProvider = new SimpleMenuProvider(
                        (containerId, playerInventory, p) -> new MusicBlockMenu(
                                containerId,
                                playerInventory,
                                musicBlockEntity,
                                ContainerLevelAccess.create(level, pos),
                                new SimpleContainerData(2)
                        ),
                        Component.literal("Disc Creator")
                );

                player.openMenu(menuProvider);
            }
        }

        return level.isClientSide() ? InteractionResult.SUCCESS : InteractionResult.CONSUME;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new MusicBlockEntity(blockPos, blockState);
    }
}
