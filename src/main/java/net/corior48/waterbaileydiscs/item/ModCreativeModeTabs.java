package net.corior48.waterbaileydiscs.item;

import net.corior48.waterbaileydiscs.WaterBaileyDiscs;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static net.corior48.waterbaileydiscs.item.ModItems.*;
import static net.corior48.waterbaileydiscs.item.ModItems.COPPER_TOKEN;
import static net.corior48.waterbaileydiscs.item.ModItems.GOLD_TOKEN;
import static net.corior48.waterbaileydiscs.item.ModItems.IRON_TOKEN;
import static net.corior48.waterbaileydiscs.item.ModItems.NETHERITE_TOKEN;
import static net.corior48.waterbaileydiscs.item.ModItems.PRIMOGEM;
import static net.corior48.waterbaileydiscs.item.ModItems.TEST_DISC;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, WaterBaileyDiscs.MODID);


    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> WATERBAILEY_ITEMS = CREATIVE_MODE_TAB.register("waterbailey_items", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.waterbaileydiscs.items")) //The language key for the title of your CreativeModeTab
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> CREATIVE_ICON.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(PRINTER.get());
                output.accept(WBUCK.get());
                output.accept(PRIMOGEM.get());
                output.accept(COPPER_TOKEN.get());
                output.accept(IRON_TOKEN.get());
                output.accept(GOLD_TOKEN.get());
                output.accept(NETHERITE_TOKEN.get());
            }).build());

 public static final DeferredHolder<CreativeModeTab, CreativeModeTab> WATERBAILEY_DISCS = CREATIVE_MODE_TAB.register("waterbailey_discs", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.waterbaileydiscs.discs")) //The language key for the title of your CreativeModeTab
            .withTabsBefore(ResourceLocation.fromNamespaceAndPath(WaterBaileyDiscs.MODID, "waterbailey_items"))
            .icon(() -> CREATIVE_DISCS_ICON.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(TEST_DISC.get());
                output.accept(SEWERCATS.get());
            }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
