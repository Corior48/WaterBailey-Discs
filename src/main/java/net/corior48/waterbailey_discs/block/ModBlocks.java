package net.corior48.waterbailey_discs.block;

import net.corior48.waterbailey_discs.WaterBaileyDiscs;
import net.corior48.waterbailey_discs.block.custom.MusicBlock;
import net.corior48.waterbailey_discs.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(WaterBaileyDiscs.MODID);

    public static final DeferredBlock<Block> MUSIC_BLOCK = registerBlock("music_block",
            () -> new MusicBlock(BlockBehaviour.Properties.of().noOcclusion().strength(4.0F).requiresCorrectToolForDrops().sound(SoundType.WOOD)));



    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItems(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItems(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem((Block)block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
