package net.corior48.waterbaileydiscs.villager;

import com.google.common.collect.ImmutableSet;
import net.corior48.waterbaileydiscs.WaterBaileyDiscs;
import net.corior48.waterbaileydiscs.block.ModBlocks;
import net.corior48.waterbaileydiscs.sound.ModSounds;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModVillagers {
    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(BuiltInRegistries.POINT_OF_INTEREST_TYPE, WaterBaileyDiscs.MODID);

    public static final DeferredRegister<VillagerProfession> VILLAGE_PROFESSIONS =
            DeferredRegister.create(BuiltInRegistries.VILLAGER_PROFESSION, WaterBaileyDiscs.MODID);

    public static final Holder<PoiType> MATT_POI_TYPE = POI_TYPES.register("matt_poi_type",
            () -> new PoiType(ImmutableSet.copyOf(ModBlocks.MUSIC_BLOCK.get().getStateDefinition().getPossibleStates()), 1 ,1));

    public static final Holder<VillagerProfession> MUSICIAN = VILLAGE_PROFESSIONS.register("musician",
            () -> new VillagerProfession("musician", holder -> holder.value() == MATT_POI_TYPE.value(),
                    poiTypeHolder -> poiTypeHolder.value() == MATT_POI_TYPE.value(), ImmutableSet.of(), ImmutableSet.of(),
                    ModSounds.MUSICIAN_USE.get()));


    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
        VILLAGE_PROFESSIONS.register(eventBus);
    }
}
