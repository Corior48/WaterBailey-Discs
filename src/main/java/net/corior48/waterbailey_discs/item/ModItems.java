package net.corior48.waterbailey_discs.item;

import net.corior48.waterbailey_discs.WaterBaileyDiscs;
import net.corior48.waterbailey_discs.sound.ModSounds;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

import static net.corior48.waterbailey_discs.WaterBaileyDiscs.MODID;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    public static final DeferredItem<Item> BLANK_DISC = ITEMS.register("blank_disc",
            () -> new Item((new Item.Properties()).stacksTo(32)) {
                public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.waterbaileydiscs.blank_disc.tooltip"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });
// Discs
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

    public static final DeferredItem<Item> BLAZING_HEART = ITEMS.registerItem("blazing_heart",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.BLAZING_HEART_KEY).stacksTo(1)));

    public static final DeferredItem<Item> DISCO_EGGMANS_ANNOUCNEMENT = ITEMS.registerItem("disco_eggmans_announcement",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.DISCO_EGGMANS_ANNOUNCEMENT_KEY).stacksTo(1)));

    public static final DeferredItem<Item> DONT_MINE_AT_NIGHT = ITEMS.registerItem("dont_mine_at_night",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.DONT_MINE_AT_NIGHT_KEY).stacksTo(1)));

    public static final DeferredItem<Item> EMBERFIRE = ITEMS.registerItem("emberfire",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.EMBERFIRE_KEY).stacksTo(1)));

    public static final DeferredItem<Item> ENDLESS_ENCORE = ITEMS.registerItem("endless_encore",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.ENDLESS_ENCORE_KEY).stacksTo(1)));

    public static final DeferredItem<Item> FURINA_THEME = ITEMS.registerItem("furina_theme",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.FURINA_THEME_KEY).stacksTo(1)));

    public static final DeferredItem<Item> KYLES_MOM = ITEMS.registerItem("kyles_mom",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.KYLES_MOM_KEY).stacksTo(1)));

    public static final DeferredItem<Item> MAJIN_FOREST_ESCAPE = ITEMS.registerItem("majin_forest_escape",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.MAJIN_FOREST_ESCAPE_KEY).stacksTo(1)));

    public static final DeferredItem<Item> NEVER_GONNA_GIVE_YOU_UP = ITEMS.registerItem("never_gonna_give_you_up",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.NEVER_GONNA_GIVE_YOU_UP_KEY).stacksTo(1)));

    public static final DeferredItem<Item> RESULTS_AND_CHILL = ITEMS.registerItem("results_and_chill",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.RESULTS_AND_CHILL_KEY).stacksTo(1)));

    public static final DeferredItem<Item> STILL_ALIVE = ITEMS.registerItem("still_alive",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.STILL_ALIVE_KEY).stacksTo(1)));

    public static final DeferredItem<Item> WANT_YOU_GONE = ITEMS.registerItem("want_you_gone",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.WANT_YOU_GONE_KEY).stacksTo(1)));

    public static final DeferredItem<Item> WATER_ME_DOWN = ITEMS.registerItem("water_me_down",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.WATER_ME_DOWN_KEY).stacksTo(1)));

    public static final DeferredItem<Item> AMONG_US_LOFI = ITEMS.registerItem("among_us_lofi",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.AMONG_US_LOFI_KEY).stacksTo(1)));

    public static final DeferredItem<Item> VS_SONIC_EXE_RERUN = ITEMS.registerItem("vs_sonic_exe_rerun",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.VS_SONIC_EXE_RERUN_KEY).stacksTo(1)));

    public static final DeferredItem<Item> GOD_DEVOURING_MANIA = ITEMS.registerItem("god_devouring_mania",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.GOD_DEVOURING_MANIA_KEY).stacksTo(1)));

    public static final DeferredItem<Item> INTERSTELLAR_DRIFT = ITEMS.registerItem("interstellar_drift",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.INTERSTELLAR_DRIFT_KEY).stacksTo(1)));

    public static final DeferredItem<Item> IRRESISTIBLE_FORCE = ITEMS.registerItem("irresistible_force",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.IRRESISTIBLE_FORCE_KEY).stacksTo(1)));

    public static final DeferredItem<Item> KITCHEN_GUN = ITEMS.registerItem("kitchen_gun",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.KITCHEN_GUN_KEY).stacksTo(1)));

    public static final DeferredItem<Item> LA_VAGUELETTE = ITEMS.registerItem("la_vaguelette",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.LA_VAGUELETTE_KEY).stacksTo(1)));

    public static final DeferredItem<Item> PLEASABT_TIPSINESS = ITEMS.registerItem("pleasant_tipsiness",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.PLEASANT_TIPSINESS_KEY).stacksTo(1)));

    public static final DeferredItem<Item> POLUMNIA_OMNIA = ITEMS.registerItem("polumnia_omnia",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.POLUMNIA_OMNIA_KEY).stacksTo(1)));

    public static final DeferredItem<Item> TAR_TAR_TAGLIA = ITEMS.registerItem("tar_tar_taglia",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.TAR_TAR_TAGLIA_KEY).stacksTo(1)));

    public static final DeferredItem<Item> ASGORE_RUNS_OVER_DESS = ITEMS.registerItem("asgore_runs_over_dess",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.ASGORE_RUNS_OVER_DESS_KEY).stacksTo(1)));

    public static final DeferredItem<Item> NOD_KRAI = ITEMS.registerItem("nod_krai",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.NOD_KRAI_KEY).stacksTo(1)));

    public static final DeferredItem<Item> SUMMER_TROPICALA = ITEMS.registerItem("summer_tropicala",
            (properties) -> new Item(properties.jukeboxPlayable(ModSounds.SUMMER_TROPICALA_KEY).stacksTo(1)));

    public static final DeferredItem<Item> DIGGY_DIGGY_HOLE = ITEMS.registerItem("diggy_diggy_hole",
            (properties -> new Item(properties.jukeboxPlayable(ModSounds.DIGGY_DIGGY_HOLE_KEY).stacksTo(1))));

    public static final DeferredItem<Item> HOUSE_OF_MIRRORS = ITEMS.registerItem("house_of_mirrors",
            (properties -> new Item(properties.jukeboxPlayable(ModSounds.HOUSE_OF_MIRRORS_KEY).stacksTo(1))));

    public static final DeferredItem<Item> REVENGE = ITEMS.registerItem("revenge",
            (properties -> new Item(properties.jukeboxPlayable(ModSounds.REVENGE_KEY).stacksTo(1))));
// MISC ITEMS
    public static final DeferredItem<Item> WBUCK = ITEMS.registerItem("wbuck",
        (properties -> new Item(properties)));

    public static final DeferredItem<Item> PRINTER =  ITEMS.registerItem("printer",
            (properties) -> new Item(properties.durability(9999999).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));

    public static final DeferredItem<Item> PRIMOGEM = ITEMS.registerItem("primogem",
            properties ->  new Item(properties.durability(9999999).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));

    public static final DeferredItem<Item> COPPER_TOKEN = ITEMS.registerItem("copper_token",
            properties -> new Item(properties));

    public static final DeferredItem<Item> IRON_TOKEN = ITEMS.registerItem("iron_token",
            properties -> new Item(properties));

    public static final DeferredItem<Item> GOLD_TOKEN = ITEMS.registerItem("gold_token",
            properties -> new Item(properties));

    public static final DeferredItem<Item> NETHERITE_TOKEN = ITEMS.registerItem("netherite_token",
            properties -> new Item(properties.component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));

//Icons
    public static final DeferredItem<Item> CREATIVE_ICON = ITEMS.registerItem("creative_icon",
            Item::new, new Item.Properties());

    public static final DeferredItem<Item> CREATIVE_DISCS_ICON = ITEMS.registerItem("creative_discs_icon",
            Item::new, new Item.Properties());


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
