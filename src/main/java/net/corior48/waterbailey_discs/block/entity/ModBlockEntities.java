package net.corior48.waterbailey_discs.block.entity;

import net.corior48.waterbailey_discs.WaterBaileyDiscs;
import net.corior48.waterbailey_discs.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, WaterBaileyDiscs.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<MusicBlockEntity>> MUSIC_BE =
            BLOCK_ENTITIES.register("music_be",
                    () -> BlockEntityType.Builder.of(
                            MusicBlockEntity::new,
                            ModBlocks.MUSIC_BLOCK.get()
                    ).build(null));

    public static void register(IEventBus bus) {
        BLOCK_ENTITIES.register(bus);
    }
}
