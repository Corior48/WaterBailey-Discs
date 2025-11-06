package net.corior48.waterbaileydiscs.sound;

import net.corior48.waterbaileydiscs.WaterBaileyDiscs;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, WaterBaileyDiscs.MODID);

    public static final Supplier<SoundEvent> TEST_DISC = registerSoundEvent("test_disc");
    public static final ResourceKey<JukeboxSong> TEST_DISC_KEY = createSong("test_disc");

    public static final Supplier<SoundEvent> MEGALOVANIA_1 = registerSoundEvent("megalovania_1");
    public static final ResourceKey<JukeboxSong> MEGALOVANIA_1_KEY = createSong("megalovania_1");

    public static final Supplier<SoundEvent> ANGEL_HARE_SIDE_A = registerSoundEvent("angel_hare_side_a");
    public static final ResourceKey<JukeboxSong> ANGEL_HARE_SIDE_A_KEY = createSong("angel_hare_side_a");

    public static final Supplier<SoundEvent> OH_DESPAIR = registerSoundEvent("oh_despair");
    public static final ResourceKey<JukeboxSong> OH_DESPAIR_KEY = createSong("oh_despair");

    public static final Supplier<SoundEvent> VILLAGER_LULLABY = registerSoundEvent("villager_lullaby");
    public static final ResourceKey<JukeboxSong> VILLAGER_LULLABY_KEY = createSong("villager_lullaby");

    public static final Supplier<SoundEvent> JAKA_JAAN = registerSoundEvent("jaka_jaan");
    public static final ResourceKey<JukeboxSong> JAKA_JAAN_KEY = createSong("jaka_jaan");

    public static final Supplier<SoundEvent> JAKA_JAAN_ALTERNATIVE = registerSoundEvent("jaka_jaan_alternative");
    public static final ResourceKey<JukeboxSong> JAKA_JAAN_ALTERNATIVE_KEY = createSong("jaka_jaan_alternative");

    public static final Supplier<SoundEvent> AMONG_US_REMIX = registerSoundEvent("among_us_remix");
    public static final ResourceKey<JukeboxSong> AMONG_US_REMIX_KEY = createSong("among_us_remix");

    public static final Supplier<SoundEvent> GANGNAM_STYLE = registerSoundEvent("gangnam_style");
    public static final ResourceKey<JukeboxSong> GANGNAM_STYLE_KEY = createSong("gangnam_style");

    public static final Supplier<SoundEvent> A_MOTHERS_LOVE = registerSoundEvent("a_mothers_love");
    public static final ResourceKey<JukeboxSong> A_MOTHERS_LOVE_KEY = createSong("a_mothers_love");

    public static final Supplier<SoundEvent> BLAZING_HEART = registerSoundEvent("blazing_heart");
    public static final ResourceKey<JukeboxSong> BLAZING_HEART_KEY = createSong("blazing_heart");

    public static final Supplier<SoundEvent> DISCO_EGGMANS_ANNOUNCEMENT = registerSoundEvent("disco_eggmans_announcement");
    public static final ResourceKey<JukeboxSong> DISCO_EGGMANS_ANNOUNCEMENT_KEY = createSong("disco_eggmans_announcement");

    public static final Supplier<SoundEvent> DONT_MINE_AT_NIGHT = registerSoundEvent("dont_mine_at_night");
    public static final ResourceKey<JukeboxSong> DONT_MINE_AT_NIGHT_KEY = createSong("dont_mine_at_night");

    public static final Supplier<SoundEvent> EMBERFIRE = registerSoundEvent("emberfire");
    public static final ResourceKey<JukeboxSong> EMBERFIRE_KEY = createSong("emberfire");

    public static final Supplier<SoundEvent> ENDLESS_ENCORE = registerSoundEvent("endless_encore");
    public static final ResourceKey<JukeboxSong> ENDLESS_ENCORE_KEY = createSong("endless_encore");

    public static final Supplier<SoundEvent> FURINA_THEME = registerSoundEvent("furina_theme");
    public static final ResourceKey<JukeboxSong> FURINA_THEME_KEY = createSong("furina_theme");

    public static final Supplier<SoundEvent> KYLES_MOM = registerSoundEvent("kyles_mom");
    public static final ResourceKey<JukeboxSong> KYLES_MOM_KEY = createSong("kyles_mom");

    public static final Supplier<SoundEvent> MAJIN_FOREST_ESCAPE = registerSoundEvent("majin_forest_escape");
    public static final ResourceKey<JukeboxSong> MAJIN_FOREST_ESCAPE_KEY = createSong("majin_forest_escape");

    public static final Supplier<SoundEvent> NEVER_GONNA_GIVE_YOU_UP = registerSoundEvent("never_gonna_give_you_up");
    public static final ResourceKey<JukeboxSong> NEVER_GONNA_GIVE_YOU_UP_KEY = createSong("never_gonna_give_you_up");

    public static final Supplier<SoundEvent> RESULTS_AND_CHILL = registerSoundEvent("results_and_chill");
    public static final ResourceKey<JukeboxSong> RESULTS_AND_CHILL_KEY = createSong("results_and_chill");

    public static final Supplier<SoundEvent> STILL_ALIVE = registerSoundEvent("still_alive");
    public static final ResourceKey<JukeboxSong> STILL_ALIVE_KEY = createSong("still_alive");

    public static final Supplier<SoundEvent> WANT_YOU_GONE = registerSoundEvent("want_you_gone");
    public static final ResourceKey<JukeboxSong> WANT_YOU_GONE_KEY = createSong("want_you_gone");

    public static final Supplier<SoundEvent> WATER_ME_DOWN = registerSoundEvent("water_me_down");
    public static final ResourceKey<JukeboxSong> WATER_ME_DOWN_KEY = createSong("water_me_down");

    public static final Supplier<SoundEvent> AMONG_US_LOFI = registerSoundEvent("among_us_lofi");
    public static final ResourceKey<JukeboxSong> AMONG_US_LOFI_KEY = createSong("among_us_lofi");

    public static final Supplier<SoundEvent> VS_SONIC_EXE_RERUN = registerSoundEvent("vs_sonic_exe_rerun");
    public static final ResourceKey<JukeboxSong> VS_SONIC_EXE_RERUN_KEY = createSong("vs_sonic_exe_rerun");

    public static final Supplier<SoundEvent> GOD_DEVOURING_MANIA = registerSoundEvent("god_devouring_mania");
    public static final ResourceKey<JukeboxSong> GOD_DEVOURING_MANIA_KEY = createSong("god_devouring_mania");

    public static final Supplier<SoundEvent> INTERSTELLAR_DRIFT = registerSoundEvent("interstellar_drift");
    public static final ResourceKey<JukeboxSong> INTERSTELLAR_DRIFT_KEY = createSong("interstellar_drift");

    public static final Supplier<SoundEvent> IRRESISTIBLE_FORCE = registerSoundEvent("irresistible_force");
    public static final ResourceKey<JukeboxSong> IRRESISTIBLE_FORCE_KEY = createSong("irresistible_force");

    public static final Supplier<SoundEvent> KITCHEN_GUN = registerSoundEvent("kitchen_gun");
    public static final ResourceKey<JukeboxSong> KITCHEN_GUN_KEY = createSong("kitchen_gun");

    public static final Supplier<SoundEvent> LA_VAGUELETTE = registerSoundEvent("la_vaguelette");
    public static final ResourceKey<JukeboxSong> LA_VAGUELETTE_KEY = createSong("la_vaguelette");

    public static final Supplier<SoundEvent> PLEASANT_TIPSINESS = registerSoundEvent("pleasant_tipsiness");
    public static final ResourceKey<JukeboxSong> PLEASANT_TIPSINESS_KEY = createSong("pleasant_tipsiness");

    public static final Supplier<SoundEvent> POLUMNIA_OMNIA = registerSoundEvent("polumnia_omnia");
    public static final ResourceKey<JukeboxSong> POLUMNIA_OMNIA_KEY = createSong("polumnia_omnia");

    public static final Supplier<SoundEvent> TAR_TAR_TAGLIA = registerSoundEvent("tar_tar_taglia");
    public static final ResourceKey<JukeboxSong> TAR_TAR_TAGLIA_KEY = createSong("tar_tar_taglia");

    public static final Supplier<SoundEvent> ASGORE_RUNS_OVER_DESS = registerSoundEvent("asgore_runs_over_dess");
    public static final ResourceKey<JukeboxSong> ASGORE_RUNS_OVER_DESS_KEY = createSong("asgore_runs_over_dess");

    public static final Supplier<SoundEvent> NOD_KRAI = registerSoundEvent("nod_krai");
    public static final ResourceKey<JukeboxSong> NOD_KRAI_KEY = createSong("nod_krai");

    public static final Supplier<SoundEvent> SUMMER_TROPICALA = registerSoundEvent("summer_tropicala");
    public static final ResourceKey<JukeboxSong> SUMMER_TROPICALA_KEY = createSong("summer_tropicala");

    public static final Supplier<SoundEvent> DIGGY_DOGGY_HOLE = registerSoundEvent("diggy_diggy_hole");
    public static final ResourceKey<JukeboxSong> DIGGY_DIGGY_HOLE_KEY = createSong("diggy_diggy_hole");

    public static final Supplier<SoundEvent> HOUSE_OF_MIRRORS = registerSoundEvent("house_of_mirrors");
    public static final ResourceKey<JukeboxSong> HOUSE_OF_MIRRORS_KEY = createSong("house_of_mirrors");

    public static final Supplier<SoundEvent> REVENGE = registerSoundEvent("revenge");
    public static final ResourceKey<JukeboxSong> REVENGE_KEY = createSong("revenge");


    public static final Supplier<SoundEvent> MUSICIAN_USE = registerSoundEvent("musician_use");

    private static ResourceKey<JukeboxSong> createSong(String name) {
        return ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(WaterBaileyDiscs.MODID, name));
    }

    private static Supplier<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(WaterBaileyDiscs.MODID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
