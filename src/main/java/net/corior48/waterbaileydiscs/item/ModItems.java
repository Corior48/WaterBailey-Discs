package net.corior48.waterbaileydiscs.item;


import net.corior48.waterbaileydiscs.WaterBaileyDiscs;
import net.corior48.waterbaileydiscs.sound.ModSounds;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(WaterBaileyDiscs.MODID);

    public static final DeferredItem<Item> TEST_DISC = ITEMS.registerItem("test_disc",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.TEST_DISC_KEY).stacksTo(1)));

    public static final DeferredItem<Item> MEGALOVANIA_1 = ITEMS.registerItem("megalovania_1",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.MEGALOVANIA_1_KEY).stacksTo(1)));

    public static final DeferredItem<Item> ANGEL_HARE_SIDE_A = ITEMS.registerItem("angel_hare_side_a",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.ANGEL_HARE_SIDE_A_KEY).stacksTo(1)));

    public static final DeferredItem<Item> OH_DESPAIR = ITEMS.registerItem("oh_despair",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.OH_DESPAIR_KEY).stacksTo(1)));

    public static final DeferredItem<Item> VILLAGER_LULLABY = ITEMS.registerItem("villager_lullaby",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.VILLAGER_LULLABY_KEY).stacksTo(1)));

    public static final DeferredItem<Item> JAKA_JAAN = ITEMS.registerItem("jaka_jaan",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.JAKA_JAAN_KEY).stacksTo(1)));

    public static final DeferredItem<Item> JAKA_JAAN_ALTERNATIVE = ITEMS.registerItem("jaka_jaan_alternative",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.JAKA_JAAN_ALTERNATIVE_KEY).stacksTo(1)));

    public static final DeferredItem<Item> AMONG_US_REMIX = ITEMS.registerItem("among_us_remix",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.AMONG_US_REMIX_KEY).stacksTo(1)));

    public static final DeferredItem<Item> GANGNAM_STYLE = ITEMS.registerItem("gangnam_style",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.GANGNAM_STYLE_KEY).stacksTo(1)));

    public static final DeferredItem<Item> A_MOTHERS_LOVE = ITEMS.registerItem("a_mothers_love",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.A_MOTHERS_LOVE_KEY).stacksTo(1)));


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
