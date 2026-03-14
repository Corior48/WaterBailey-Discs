package net.corior48.waterbailey_discs.item;

import net.corior48.waterbailey_discs.WaterBaileyDiscs;
import net.corior48.waterbailey_discs.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static net.corior48.waterbailey_discs.item.ModItems.*;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, WaterBaileyDiscs.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> WATERBAILEY_ITEMS = CREATIVE_MODE_TAB.register("waterbailey_items", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.waterbaileydiscs.items")) //The language key for the title of your CreativeModeTab
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> CREATIVE_ICON.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(ModBlocks.MUSIC_BLOCK.get());
                output.accept((BLANK_DISC.get()));
                output.accept(PRINTER.get());
                output.accept(WBUCK.get());
                output.accept(PRIMOGEM.get());
                output.accept(COPPER_TOKEN.get());
                output.accept(IRON_TOKEN.get());
                output.accept(GOLD_TOKEN.get());
                output.accept(NETHERITE_TOKEN.get());
                //output.accept(NETHERITE_NUGGET.get());

            }).build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> WATERBAILEY_DISCS = CREATIVE_MODE_TAB.register("waterbailey_discs", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.waterbaileydiscs.discs")) //The language key for the title of your CreativeModeTab
            .withTabsBefore(ResourceLocation.fromNamespaceAndPath(WaterBaileyDiscs.MODID, "waterbailey_items"))
            .icon(() -> CREATIVE_DISCS_ICON.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(ANGEL_HARE_SIDE_A.get());
                output.accept(OH_DESPAIR.get());
                output.accept(VILLAGER_LULLABY.get());
                output.accept(MEGALOVANIA_1.get());
                output.accept(JAKA_JAAN.get());
                output.accept(JAKA_JAAN_ALTERNATIVE.get());
                output.accept(AMONG_US_REMIX.get());
                output.accept(AMONG_US_LOFI.get());
                output.accept(GANGNAM_STYLE.get());
                output.accept(A_MOTHERS_LOVE.get());
                output.accept(BLAZING_HEART.get());
                output.accept(DISCO_EGGMANS_ANNOUCNEMENT.get());
                output.accept(DONT_MINE_AT_NIGHT.get());
                output.accept(EMBERFIRE.get());
                output.accept(ENDLESS_ENCORE.get());
                output.accept(FURINA_THEME.get());
                output.accept(KYLES_MOM.get());
                output.accept(MAJIN_FOREST_ESCAPE.get());
                output.accept(NEVER_GONNA_GIVE_YOU_UP.get());
                output.accept(RESULTS_AND_CHILL.get());
                output.accept(STILL_ALIVE.get());
                output.accept(WANT_YOU_GONE.get());
                output.accept(WATER_ME_DOWN.get());
                output.accept(VS_SONIC_EXE_RERUN.get());
                output.accept(GOD_DEVOURING_MANIA.get());
                output.accept(INTERSTELLAR_DRIFT.get());
                output.accept(IRRESISTIBLE_FORCE.get());
                output.accept(KITCHEN_GUN.get());
                output.accept(LA_VAGUELETTE.get());
                output.accept(PLEASABT_TIPSINESS.get());
                output.accept(POLUMNIA_OMNIA.get());
                output.accept(TAR_TAR_TAGLIA.get());
                output.accept(ASGORE_RUNS_OVER_DESS.get());
                output.accept(NOD_KRAI.get());
                output.accept(SUMMER_TROPICALA.get());
                output.accept(DIGGY_DIGGY_HOLE.get());
                output.accept(HOUSE_OF_MIRRORS.get());
                output.accept(REVENGE.get());
            }).build());




    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
