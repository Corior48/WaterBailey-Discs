package net.corior48.waterbailey_discs.utils;

import net.corior48.waterbailey_discs.item.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;

public class DiscOptions {
    public enum DiscCategory {
        ALL("ALL"),
        VANILLA("Vanilla"),
        WATERFALL_X_BAILEY("Waterfall x Bailey"),
        GENSHIN_IMPACT("Genshin Impact"),
        THE_AETHER("The Aether"),
        TWLIGHT_FOREST("Twlight Forest"),
        CREATE("Create");

        public final String displayName;

        DiscCategory(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }

    }
    public record DiscEntry(Item item, DiscCategory category, int xpCost) {}

    public static final List<DiscEntry> ALL = buildDiscList();

    private static List<DiscEntry> buildDiscList() {
        List<DiscEntry> discs = new ArrayList<>();

        //Vanila
        add(discs, Items.MUSIC_DISC_13, DiscCategory.VANILLA, 1);
                add(discs, Items.MUSIC_DISC_CAT, DiscCategory.VANILLA, 1);
                add(discs, Items.MUSIC_DISC_BLOCKS, DiscCategory.VANILLA, 1);
                add(discs, Items.MUSIC_DISC_CHIRP, DiscCategory.VANILLA, 1);
                add(discs, Items.MUSIC_DISC_FAR, DiscCategory.VANILLA, 1);
                add(discs, Items.MUSIC_DISC_MALL, DiscCategory.VANILLA, 1);
                add(discs, Items.MUSIC_DISC_MELLOHI, DiscCategory.VANILLA, 1);
                add(discs, Items.MUSIC_DISC_STAL, DiscCategory.VANILLA, 1);
                add(discs, Items.MUSIC_DISC_STRAD, DiscCategory.VANILLA, 1);
                add(discs, Items.MUSIC_DISC_WARD, DiscCategory.VANILLA, 1);
                add(discs, Items.MUSIC_DISC_11, DiscCategory.VANILLA, 1);
                add(discs, Items.MUSIC_DISC_WAIT, DiscCategory.VANILLA, 1);
                add(discs, Items.MUSIC_DISC_OTHERSIDE, DiscCategory.VANILLA, 1);
                add(discs, Items.MUSIC_DISC_5, DiscCategory.VANILLA, 1);
                add(discs, Items.MUSIC_DISC_PIGSTEP, DiscCategory.VANILLA, 1);
                add(discs, Items.MUSIC_DISC_RELIC, DiscCategory.VANILLA, 1);
                add(discs, Items.MUSIC_DISC_PRECIPICE, DiscCategory.VANILLA, 1);
                add(discs, Items.MUSIC_DISC_CREATOR, DiscCategory.VANILLA, 1);
                add(discs, Items.MUSIC_DISC_CREATOR_MUSIC_BOX, DiscCategory.VANILLA, 1);
                // Waterfall X Bailey
                add(discs, ModItems.A_MOTHERS_LOVE.get(), DiscCategory.WATERFALL_X_BAILEY, 5);
                add(discs, ModItems.ASGORE_RUNS_OVER_DESS.get(), DiscCategory.WATERFALL_X_BAILEY, 5);
                add(discs, ModItems.AMONG_US_LOFI.get(), DiscCategory.WATERFALL_X_BAILEY, 5);
                add(discs, ModItems.AMONG_US_REMIX.get(), DiscCategory.WATERFALL_X_BAILEY, 5);
                add(discs, ModItems.ANGEL_HARE_SIDE_A.get(), DiscCategory.WATERFALL_X_BAILEY, 5);
                add(discs, ModItems.DIGGY_DIGGY_HOLE.get(), DiscCategory.WATERFALL_X_BAILEY, 5);
                add(discs, ModItems.DISCO_EGGMANS_ANNOUCNEMENT.get(), DiscCategory.WATERFALL_X_BAILEY, 5);
                add(discs, ModItems.DONT_MINE_AT_NIGHT.get(), DiscCategory.WATERFALL_X_BAILEY, 5);
                add(discs, ModItems.ENDLESS_ENCORE.get(), DiscCategory.WATERFALL_X_BAILEY, 5);
                add(discs, ModItems.GANGNAM_STYLE.get(), DiscCategory.WATERFALL_X_BAILEY, 5);
                add(discs, ModItems.JAKA_JAAN.get(), DiscCategory.WATERFALL_X_BAILEY, 5);
                add(discs, ModItems.JAKA_JAAN_ALTERNATIVE.get(), DiscCategory.WATERFALL_X_BAILEY, 5);
                add(discs, ModItems.KYLES_MOM.get(), DiscCategory.WATERFALL_X_BAILEY, 5);
                add(discs, ModItems.MEGALOVANIA_1.get(), DiscCategory.WATERFALL_X_BAILEY, 5);
                add(discs, ModItems.MAJIN_FOREST_ESCAPE.get(), DiscCategory.WATERFALL_X_BAILEY, 5);
                add(discs, ModItems.KITCHEN_GUN.get(), DiscCategory.WATERFALL_X_BAILEY, 5);
                add(discs, ModItems.NEVER_GONNA_GIVE_YOU_UP.get(), DiscCategory.WATERFALL_X_BAILEY, 5);
                add(discs, ModItems.REVENGE.get(), DiscCategory.WATERFALL_X_BAILEY, 5);
                add(discs, ModItems.RESULTS_AND_CHILL.get(), DiscCategory.WATERFALL_X_BAILEY, 5);
                add(discs, ModItems.STILL_ALIVE.get(), DiscCategory.WATERFALL_X_BAILEY, 5);
                add(discs, ModItems.SUMMER_TROPICALA.get(), DiscCategory.WATERFALL_X_BAILEY, 5);
                add(discs, ModItems.VILLAGER_LULLABY.get(), DiscCategory.WATERFALL_X_BAILEY, 5);
                add(discs, ModItems.VS_SONIC_EXE_RERUN.get(), DiscCategory.WATERFALL_X_BAILEY, 5);
                add(discs, ModItems.WANT_YOU_GONE.get(), DiscCategory.WATERFALL_X_BAILEY, 5);
                add(discs, ModItems.WATER_ME_DOWN.get(), DiscCategory.WATERFALL_X_BAILEY, 5);
                //Genshin OST
                add(discs, ModItems.BLAZING_HEART.get(), DiscCategory.GENSHIN_IMPACT, 7);
                add(discs, ModItems.EMBERFIRE.get(), DiscCategory.GENSHIN_IMPACT, 7);
                add(discs, ModItems.NOD_KRAI.get(), DiscCategory.GENSHIN_IMPACT, 7);
                add(discs, ModItems.FURINA_THEME.get(), DiscCategory.GENSHIN_IMPACT, 7);
                add(discs, ModItems.GOD_DEVOURING_MANIA.get(), DiscCategory.GENSHIN_IMPACT, 7);
                add(discs, ModItems.INTERSTELLAR_DRIFT.get(), DiscCategory.GENSHIN_IMPACT, 7);
                add(discs, ModItems.IRRESISTIBLE_FORCE.get(), DiscCategory.GENSHIN_IMPACT, 7);
                add(discs, ModItems.LA_VAGUELETTE.get(), DiscCategory.GENSHIN_IMPACT, 7);
                add(discs, ModItems.PLEASABT_TIPSINESS.get(), DiscCategory.GENSHIN_IMPACT, 7);
                add(discs, ModItems.POLUMNIA_OMNIA.get(), DiscCategory.GENSHIN_IMPACT, 7);
                add(discs, ModItems.TAR_TAR_TAGLIA.get(), DiscCategory.GENSHIN_IMPACT, 7);
                //OPTIONAL AND OR 2.0 ADDITIONAL DISCS
                addOptional(discs, "aether:music_disc_aether_tune", DiscCategory.THE_AETHER, 10);
                addOptional(discs, "aether:music_disc_ascending_dawn", DiscCategory.THE_AETHER, 10);
                addOptional(discs, "aether:music_disc_sliders_wrath", DiscCategory.THE_AETHER, 10);
                addOptional(discs, "twilightforest:music_disc_thread", DiscCategory.TWLIGHT_FOREST, 10);
                addOptional(discs, "twilightforest:music_disc_findings", DiscCategory.TWLIGHT_FOREST, 10);
                addOptional(discs, "twilightforest:music_disc_radiance", DiscCategory.TWLIGHT_FOREST, 10);
                addOptional(discs, "twilightforest:music_disc_steps", DiscCategory.TWLIGHT_FOREST, 10);
                addOptional(discs, "twilightforest:music_disc_motion", DiscCategory.TWLIGHT_FOREST, 10);
                addOptional(discs, "twilightforest:music_disc_wayfarer", DiscCategory.TWLIGHT_FOREST, 10);
                addOptional(discs, "twilightforest:music_disc_home", DiscCategory.TWLIGHT_FOREST, 10);
                addOptional(discs, "twilightforest:music_disc_maker",  DiscCategory.TWLIGHT_FOREST, 10);
                addOptional(discs, "twilightforest:music_disc_superstitious", DiscCategory.TWLIGHT_FOREST, 10);
                addOptional(discs, "create_connected:music_disc_elevator", DiscCategory.CREATE, 10);
                addOptional(discs, "create_connected:music_disc_interlude", DiscCategory.CREATE, 10);
        return List.copyOf(discs);
    };

    public static List<DiscCategory> getAvailableCategories() {
        List<DiscCategory> categories = new ArrayList<>();
        categories.add(DiscCategory.ALL);

        for (DiscCategory category : DiscCategory.values()) {
            if (category == DiscCategory.ALL) {
                continue;
            }

            boolean hasAny = false;
            for (DiscEntry entry : ALL) {
                if (entry.category() == category) {
                    hasAny = true;
                    break;
                }
            }

            if (hasAny) {
                categories.add(category);
            }
        }

        return List.copyOf(categories);
    }

    private static void add(List<DiscEntry> discs, Item item, DiscCategory category, int xpCost) {
        discs.add(new DiscEntry(item, category, xpCost));
    }

    private static void addOptional(List<DiscEntry> discs, String itemId, DiscCategory category, int xpCost) {
        ResourceLocation id = ResourceLocation.tryParse(itemId);
        if (id == null) {
            return;
        }

        Item item = BuiltInRegistries.ITEM.get(id);
        if (item != Items.AIR) {
            discs.add(new DiscEntry(item, category, xpCost));
        }
    }

    public static int size() {
        return ALL.size();
    }

    public static Item get(int index) {
        return ALL.get(index).item;
    }

    public static DiscEntry getEntry(int index) {
        return ALL.get(index);
    }

    public static int getXpCost(int index) {
        return ALL.get(index).xpCost;
    }

    public static boolean matchesCategory(int index, DiscCategory activeCategory) {
        if (activeCategory == DiscCategory.ALL) {
            return true;
        }
        return getEntry(index).category() == activeCategory;
    }
}
