package net.corior48.waterbaileydiscs.item;


import net.corior48.waterbaileydiscs.WaterBaileyDiscs;
import net.corior48.waterbaileydiscs.sound.ModSounds;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(WaterBaileyDiscs.MODID);

    public static final DeferredItem<Item> TEST_DISC = ITEMS.register("test_disc",
            (Properties) -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.TEST_SONG_KEY).stacksTo(1)));

    public static final DeferredItem<Item> CREATIVE_ICON = ITEMS.register("creative_icon",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> WBUCK = ITEMS.register("wbuck",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
