package net.corior48.waterbaileydiscs.common;

import net.corior48.waterbaileydiscs.config.ModClientConfig;
import net.corior48.waterbaileydiscs.item.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;

public class DiscCatalog {
    public enum DiscCategory {
        ALL("ALL"),
        VANILLA("Vanilla"),
        WATERFALL_X_BAILEY("Waterfall x Bailey"),
        GENSHIN_IMPACT("Genshin Impact"),
        THE_AETHER("The Aether"),
        TWILIGHT_FOREST("Twilight Forest"),
        CREATE("Create"),
        HARDCORE("Hardcore");

        public final String displayName;

        DiscCategory(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }

    }
    public enum HardcoreSubCategory {
        ALL("All Hardcore"),
        NON_MAIN("Non-Main"),
        PODCASTS("Podcasts"),
        SOUNDCLOUD("Soundcloud");

        public final String displayName;
        HardcoreSubCategory(String displayName) {this.displayName = displayName;}

        public String getDisplayName() {return displayName;}
    }
    public record DiscEntry(
            Item item,
            DiscCategory category,
            HardcoreSubCategory hardcoreSubCategory,
            int xpCost,
            boolean adminOnly
    ) {}

    private static List<DiscEntry> ALL = buildDiscList();


    private static List<DiscEntry> buildDiscList() {
        List<DiscEntry> discs = new ArrayList<>();

        //Vanila
        add(discs, Items.MUSIC_DISC_13, DiscCategory.VANILLA, null, 1);
        add(discs, Items.MUSIC_DISC_CAT, DiscCategory.VANILLA,null, 1);
        add(discs, Items.MUSIC_DISC_BLOCKS, DiscCategory.VANILLA,null, 1);
        add(discs, Items.MUSIC_DISC_CHIRP, DiscCategory.VANILLA,null, 1);
        add(discs, Items.MUSIC_DISC_FAR, DiscCategory.VANILLA, null,1);
        add(discs, Items.MUSIC_DISC_MALL, DiscCategory.VANILLA, null,1);
        add(discs, Items.MUSIC_DISC_MELLOHI, DiscCategory.VANILLA,null, 1);
        add(discs, Items.MUSIC_DISC_STAL, DiscCategory.VANILLA, null,1);
        add(discs, Items.MUSIC_DISC_STRAD, DiscCategory.VANILLA,null, 1);
        add(discs, Items.MUSIC_DISC_WARD, DiscCategory.VANILLA,null, 1);
        add(discs, Items.MUSIC_DISC_11, DiscCategory.VANILLA, null,1);
        add(discs, Items.MUSIC_DISC_WAIT, DiscCategory.VANILLA, null, 1);
        add(discs, Items.MUSIC_DISC_OTHERSIDE, DiscCategory.VANILLA, null, 1);
        add(discs, Items.MUSIC_DISC_5, DiscCategory.VANILLA, null,1);
        add(discs, Items.MUSIC_DISC_PIGSTEP, DiscCategory.VANILLA, null,1);
        add(discs, Items.MUSIC_DISC_RELIC, DiscCategory.VANILLA, null,1);
        add(discs, Items.MUSIC_DISC_PRECIPICE, DiscCategory.VANILLA, null,1);
        add(discs, Items.MUSIC_DISC_CREATOR, DiscCategory.VANILLA, null,1);
        add(discs, Items.MUSIC_DISC_CREATOR_MUSIC_BOX, DiscCategory.VANILLA, null,1);
        // Waterfall X Bailey
        add(discs, ModItems.A_MOTHERS_LOVE.get(), DiscCategory.WATERFALL_X_BAILEY, null,5);
        add(discs, ModItems.ASGORE_RUNS_OVER_DESS.get(), DiscCategory.WATERFALL_X_BAILEY, null,5);
        add(discs, ModItems.AMONG_US_LOFI.get(), DiscCategory.WATERFALL_X_BAILEY, null,5);
        add(discs, ModItems.AMONG_US_REMIX.get(), DiscCategory.WATERFALL_X_BAILEY, null,5);
        add(discs, ModItems.ANGEL_HARE_SIDE_A.get(), DiscCategory.WATERFALL_X_BAILEY, null,5);
        add(discs, ModItems.DIGGY_DIGGY_HOLE.get(), DiscCategory.WATERFALL_X_BAILEY, null,5);
        add(discs, ModItems.DISCO_EGGMANS_ANNOUCNEMENT.get(), DiscCategory.WATERFALL_X_BAILEY, null,5);
        add(discs, ModItems.DONT_MINE_AT_NIGHT.get(), DiscCategory.WATERFALL_X_BAILEY, null,5);
        add(discs, ModItems.ENDLESS_ENCORE.get(), DiscCategory.WATERFALL_X_BAILEY, null,5);
        add(discs, ModItems.GANGNAM_STYLE.get(), DiscCategory.WATERFALL_X_BAILEY, null,5);
        add(discs, ModItems.JAKA_JAAN.get(), DiscCategory.WATERFALL_X_BAILEY, null,5);
        add(discs, ModItems.JAKA_JAAN_ALTERNATIVE.get(), DiscCategory.WATERFALL_X_BAILEY, null,5);
        add(discs, ModItems.KYLES_MOM.get(), DiscCategory.WATERFALL_X_BAILEY, null,5);
        add(discs, ModItems.MEGALOVANIA_1.get(), DiscCategory.WATERFALL_X_BAILEY, null,5);
        add(discs, ModItems.MAJIN_FOREST_ESCAPE.get(), DiscCategory.WATERFALL_X_BAILEY, null,5);
        add(discs, ModItems.KITCHEN_GUN.get(), DiscCategory.WATERFALL_X_BAILEY, null,5);
        add(discs, ModItems.NEVER_GONNA_GIVE_YOU_UP.get(), DiscCategory.WATERFALL_X_BAILEY, null,5);
        add(discs, ModItems.OH_DESPAIR.get(), DiscCategory.WATERFALL_X_BAILEY, null,5);
        add(discs, ModItems.REVENGE.get(), DiscCategory.WATERFALL_X_BAILEY, null,5);
        add(discs, ModItems.RESULTS_AND_CHILL.get(), DiscCategory.WATERFALL_X_BAILEY, null,5);
        add(discs, ModItems.STILL_ALIVE.get(), DiscCategory.WATERFALL_X_BAILEY, null,5);
        add(discs, ModItems.SUMMER_TROPICALA.get(), DiscCategory.WATERFALL_X_BAILEY, null,5);
        add(discs, ModItems.VILLAGER_LULLABY.get(), DiscCategory.WATERFALL_X_BAILEY, null,5);
        add(discs, ModItems.VS_SONIC_EXE_RERUN.get(), DiscCategory.WATERFALL_X_BAILEY, null,5);
        add(discs, ModItems.WANT_YOU_GONE.get(), DiscCategory.WATERFALL_X_BAILEY, null,5);
        add(discs, ModItems.WATER_ME_DOWN.get(), DiscCategory.WATERFALL_X_BAILEY, null,5);
        //Genshin OST
        add(discs, ModItems.BLAZING_HEART.get(), DiscCategory.GENSHIN_IMPACT, null,7);
        add(discs, ModItems.EMBERFIRE.get(), DiscCategory.GENSHIN_IMPACT, null,7);
        add(discs, ModItems.NOD_KRAI.get(), DiscCategory.GENSHIN_IMPACT, null,7);
        add(discs, ModItems.FURINA_THEME.get(), DiscCategory.GENSHIN_IMPACT, null,7);
        add(discs, ModItems.GOD_DEVOURING_MANIA.get(), DiscCategory.GENSHIN_IMPACT, null,7);
        add(discs, ModItems.INTERSTELLAR_DRIFT.get(), DiscCategory.GENSHIN_IMPACT, null,7);
        add(discs, ModItems.IRRESISTIBLE_FORCE.get(), DiscCategory.GENSHIN_IMPACT, null,7);
        add(discs, ModItems.LA_VAGUELETTE.get(), DiscCategory.GENSHIN_IMPACT, null,7);
        add(discs, ModItems.PLEASABT_TIPSINESS.get(), DiscCategory.GENSHIN_IMPACT, null,7);
        add(discs, ModItems.POLUMNIA_OMNIA.get(), DiscCategory.GENSHIN_IMPACT, null,7);
        add(discs, ModItems.TAR_TAR_TAGLIA.get(), DiscCategory.GENSHIN_IMPACT, null,7);
        //OPTIONAL AND OR 2.0 ADDITIONAL DISCS
        addOptional(discs, "aether:music_disc_aether_tune", DiscCategory.THE_AETHER, 10);
        addOptional(discs, "aether:music_disc_ascending_dawn", DiscCategory.THE_AETHER, 10);
        addOptional(discs, "aether:music_disc_sliders_wrath", DiscCategory.THE_AETHER, 10);
        addOptional(discs, "twilightforest:music_disc_thread", DiscCategory.TWILIGHT_FOREST, 10);
        addOptional(discs, "twilightforest:music_disc_findings", DiscCategory.TWILIGHT_FOREST, 10);
        addOptional(discs, "twilightforest:music_disc_radiance", DiscCategory.TWILIGHT_FOREST, 10);
        addOptional(discs, "twilightforest:music_disc_steps", DiscCategory.TWILIGHT_FOREST, 10);
        addOptional(discs, "twilightforest:music_disc_motion", DiscCategory.TWILIGHT_FOREST, 10);
        addOptional(discs, "twilightforest:music_disc_wayfarer", DiscCategory.TWILIGHT_FOREST, 10);
        addOptional(discs, "twilightforest:music_disc_home", DiscCategory.TWILIGHT_FOREST, 10);
        addOptional(discs, "twilightforest:music_disc_maker",  DiscCategory.TWILIGHT_FOREST, 10);
        addOptional(discs, "twilightforest:music_disc_superstitious", DiscCategory.TWILIGHT_FOREST, 10);
        addOptional(discs, "create_connected:music_disc_elevator", DiscCategory.CREATE, 10);
        addOptional(discs, "create_connected:music_disc_interlude", DiscCategory.CREATE, 10);
        //Hardcore Discs
        if (ModClientConfig.HARDCORE_DISCS_ENABLED.get()) {
            add(discs, ModItems.SEXY_LUIGI.get(), DiscCategory.HARDCORE, HardcoreSubCategory.NON_MAIN, 5);
            add(discs, ModItems.HOPES_AND_DREAMS_REMASTER.get(),  DiscCategory.HARDCORE, HardcoreSubCategory.NON_MAIN,  5);
            add(discs, ModItems.FUHUHHUK.get(), DiscCategory.HARDCORE,  HardcoreSubCategory.NON_MAIN, 5);
            add(discs, ModItems.LISTEN_MY_WAY.get(),  DiscCategory.HARDCORE, HardcoreSubCategory.NON_MAIN,  5);
            add(discs, ModItems.MARIOS_INVINCIBLE_SONG.get(), DiscCategory.HARDCORE, HardcoreSubCategory.NON_MAIN,  5);
            add(discs, ModItems.NEVER_GONNA_STOP.get(), DiscCategory.HARDCORE, HardcoreSubCategory.NON_MAIN,  5);
            add(discs, ModItems.ONLY_FORCE_FOR_ME.get(), DiscCategory.HARDCORE, HardcoreSubCategory.NON_MAIN,  5);
            add(discs, ModItems.RAMBLEY_KINITO_RAP.get(),  DiscCategory.HARDCORE, HardcoreSubCategory.NON_MAIN,  5, true);
            add(discs, ModItems.REVENGE_2.get(),  DiscCategory.HARDCORE, HardcoreSubCategory.NON_MAIN,  5);
            add(discs, ModItems.STORY_OF_UNDERTALE_MOTI.get(),  DiscCategory.HARDCORE, HardcoreSubCategory.NON_MAIN,  5);
            add(discs, ModItems.SONICEXE_BENDROWNED_RAP.get(), DiscCategory.HARDCORE, HardcoreSubCategory.NON_MAIN,  5, true);
            add(discs, ModItems.STRONGER_DAFT_SPEED.get(), DiscCategory.HARDCORE, HardcoreSubCategory.NON_MAIN,  5);
            add(discs, ModItems.SUSIE_YURI_RAP.get(), DiscCategory.HARDCORE, HardcoreSubCategory.NON_MAIN,  5, true);
            add(discs, ModItems.TRIPLE_THE_THREAT_NONGAGOS.get(),   DiscCategory.HARDCORE,  HardcoreSubCategory.NON_MAIN, 5);
            //Podcasts
            add(discs, ModItems.BEN_DROWNED.get(),  DiscCategory.HARDCORE,HardcoreSubCategory.PODCASTS, 5, true);
            //Soundcloud
            add(discs, ModItems.BACKBONE_REMIX.get(), DiscCategory.HARDCORE, HardcoreSubCategory.SOUNDCLOUD,5);
            add(discs, ModItems.LIKE_FATHER_LIKE_SON.get(), DiscCategory.HARDCORE, HardcoreSubCategory.SOUNDCLOUD, 5);
            add(discs, ModItems.MEGALOVANIA_DEMITALE.get(), DiscCategory.HARDCORE, HardcoreSubCategory.SOUNDCLOUD, 5);
            add(discs, ModItems.MEGALOVANIA_ELEVATOR_JAZZ.get(), DiscCategory.HARDCORE, HardcoreSubCategory.SOUNDCLOUD, 5);
        }
        return List.copyOf(discs);
    };

    public static void rebuildCatalog() {
        ALL = buildDiscList();
    }

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

    public static boolean isAdminOnly(int index) {
        return ALL.get(index).adminOnly();
    }

    private static void add(List<DiscEntry> discs, Item item, DiscCategory category, HardcoreSubCategory hardcoreSubCategory, int xpCost) {
        add(discs, item, category, hardcoreSubCategory, xpCost, false);
    }

    private static void add(List<DiscEntry> discs, Item item, DiscCategory category, HardcoreSubCategory hardcoreSubCategory, int xpCost, boolean adminOnly) {
        discs.add(new DiscEntry(item, category, hardcoreSubCategory, xpCost, adminOnly));
    }

    private static void addOptional(List<DiscEntry> discs, String itemId, DiscCategory category, int xpCost) {
        addOptional(discs, itemId, category, xpCost, false);
    }

    private static void addOptional(List<DiscEntry> discs, String itemId, DiscCategory category, int xpCost, boolean adminOnly) {
        ResourceLocation id = ResourceLocation.tryParse(itemId);
        if (id == null) {
            return;
        }

        Item item = BuiltInRegistries.ITEM.get(id);
        if (item != Items.AIR) {
            discs.add(new DiscEntry(item, category, null, xpCost, adminOnly));
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

    public static boolean matchesHardcoreSubCategory(int index, HardcoreSubCategory activeSubCategory) {
        DiscEntry entry = getEntry(index);

        if (entry.category() != DiscCategory.HARDCORE) {
            return false;
        }

        if (activeSubCategory == HardcoreSubCategory.ALL) {
            return true;
        }

        return entry.hardcoreSubCategory() == activeSubCategory;
    }
}
