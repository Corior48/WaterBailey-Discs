package net.corior48.waterbaileydiscs.item;


import net.corior48.waterbaileydiscs.WaterBaileyDiscs;
import net.corior48.waterbaileydiscs.sound.ModSounds;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import javax.swing.*;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(WaterBaileyDiscs.MODID);

    public static final DeferredItem<Item> TEST_DISC = ITEMS.registerItem("test_disc",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.TEST_DISC_KEY).stacksTo(1)));

    public static final DeferredItem<Item> SEWERCATS = ITEMS.registerItem("sewercats",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.SEWERCATS_KEY).stacksTo(1)));

    public static final DeferredItem<Item> ANGEL_HARE_SIDE_A = ITEMS.registerItem("angel_hare_side_a",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.ANGEL_HARE_SIDE_A_KEY).stacksTo(1)));

    public static final DeferredItem<Item> OH_DESPAIR = ITEMS.registerItem("oh_despair",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.OH_DESPAIR_KEY).stacksTo(1)));

    public static final DeferredItem<Item> CREATIVE_ICON = ITEMS.register("creative_icon",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> WBUCK = ITEMS.register("wbuck",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> PRINTER = ITEMS.register("printer",
            () -> new Item(new Item.Properties().durability(9999999).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));

    public static final DeferredItem<Item> PRIMOGEM = ITEMS.register("primogem",
            () -> new Item(new Item.Properties().durability(9999999).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));

    public static final DeferredItem<Item> COPPER_TOKEN = ITEMS.register("copper_token",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> IRON_TOKEN = ITEMS.register("iron_token",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> GOLD_TOKEN = ITEMS.register("gold_token",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> NETHERITE_TOKEN = ITEMS.register("netherite_token",
            () -> new Item(new Item.Properties().component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));

    public static final DeferredItem<Item> NETHERITE_NUGGET = ITEMS.register("netherite_nugget",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> CREATIVE_DISCS_ICON = ITEMS.register("creative_discs_icon",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
