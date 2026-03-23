package net.corior48.waterbaileydiscs.sound;

import net.corior48.waterbaileydiscs.WaterBaileyDiscs;
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

    //Waterfall x Bailey Base Disc
    public static final Supplier<SoundEvent> TEST_DISC = registerSoundEvent("test_disc");
    public static final ResourceKey<JukeboxSong> TEST_DISC_KEY = createSong("wxb/test_disc");

    public static final Supplier<SoundEvent> MEGALOVANIA_1 = registerSoundEvent("megalovania_1");
    public static final ResourceKey<JukeboxSong> MEGALOVANIA_1_KEY = createSong("wxb/megalovania_1");

    public static final Supplier<SoundEvent> ANGEL_HARE_SIDE_A = registerSoundEvent("angel_hare_side_a");
    public static final ResourceKey<JukeboxSong> ANGEL_HARE_SIDE_A_KEY = createSong("wxb/angel_hare_side_a");

    public static final Supplier<SoundEvent> OH_DESPAIR = registerSoundEvent("oh_despair");
    public static final ResourceKey<JukeboxSong> OH_DESPAIR_KEY = createSong("wxb/oh_despair");

    public static final Supplier<SoundEvent> VILLAGER_LULLABY = registerSoundEvent("villager_lullaby");
    public static final ResourceKey<JukeboxSong> VILLAGER_LULLABY_KEY = createSong("wxb/villager_lullaby");

    public static final Supplier<SoundEvent> JAKA_JAAN = registerSoundEvent("jaka_jaan");
    public static final ResourceKey<JukeboxSong> JAKA_JAAN_KEY = createSong("wxb/jaka_jaan");

    public static final Supplier<SoundEvent> JAKA_JAAN_ALTERNATIVE = registerSoundEvent("jaka_jaan_alternative");
    public static final ResourceKey<JukeboxSong> JAKA_JAAN_ALTERNATIVE_KEY = createSong("wxb/jaka_jaan_alternative");

    public static final Supplier<SoundEvent> AMONG_US_REMIX = registerSoundEvent("among_us_remix");
    public static final ResourceKey<JukeboxSong> AMONG_US_REMIX_KEY = createSong("wxb/among_us_remix");

    public static final Supplier<SoundEvent> GANGNAM_STYLE = registerSoundEvent("gangnam_style");
    public static final ResourceKey<JukeboxSong> GANGNAM_STYLE_KEY = createSong("wxb/gangnam_style");

    public static final Supplier<SoundEvent> A_MOTHERS_LOVE = registerSoundEvent("a_mothers_love");
    public static final ResourceKey<JukeboxSong> A_MOTHERS_LOVE_KEY = createSong("wxb/a_mothers_love");

    public static final Supplier<SoundEvent> BLAZING_HEART = registerSoundEvent("blazing_heart");
    public static final ResourceKey<JukeboxSong> BLAZING_HEART_KEY = createSong("wxb/blazing_heart");

    public static final Supplier<SoundEvent> DISCO_EGGMANS_ANNOUNCEMENT = registerSoundEvent("disco_eggmans_announcement");
    public static final ResourceKey<JukeboxSong> DISCO_EGGMANS_ANNOUNCEMENT_KEY = createSong("wxb/disco_eggmans_announcement");

    public static final Supplier<SoundEvent> DONT_MINE_AT_NIGHT = registerSoundEvent("dont_mine_at_night");
    public static final ResourceKey<JukeboxSong> DONT_MINE_AT_NIGHT_KEY = createSong("wxb/dont_mine_at_night");

    public static final Supplier<SoundEvent> EMBERFIRE = registerSoundEvent("emberfire");
    public static final ResourceKey<JukeboxSong> EMBERFIRE_KEY = createSong("wxb/emberfire");

    public static final Supplier<SoundEvent> ENDLESS_ENCORE = registerSoundEvent("endless_encore");
    public static final ResourceKey<JukeboxSong> ENDLESS_ENCORE_KEY = createSong("wxb/endless_encore");

    public static final Supplier<SoundEvent> FURINA_THEME = registerSoundEvent("furina_theme");
    public static final ResourceKey<JukeboxSong> FURINA_THEME_KEY = createSong("wxb/furina_theme");

    public static final Supplier<SoundEvent> KYLES_MOM = registerSoundEvent("kyles_mom");
    public static final ResourceKey<JukeboxSong> KYLES_MOM_KEY = createSong("wxb/kyles_mom");

    public static final Supplier<SoundEvent> MAJIN_FOREST_ESCAPE = registerSoundEvent("majin_forest_escape");
    public static final ResourceKey<JukeboxSong> MAJIN_FOREST_ESCAPE_KEY = createSong("wxb/majin_forest_escape");

    public static final Supplier<SoundEvent> NEVER_GONNA_GIVE_YOU_UP = registerSoundEvent("never_gonna_give_you_up");
    public static final ResourceKey<JukeboxSong> NEVER_GONNA_GIVE_YOU_UP_KEY = createSong("wxb/never_gonna_give_you_up");

    public static final Supplier<SoundEvent> RESULTS_AND_CHILL = registerSoundEvent("results_and_chill");
    public static final ResourceKey<JukeboxSong> RESULTS_AND_CHILL_KEY = createSong("wxb/results_and_chill");

    public static final Supplier<SoundEvent> STILL_ALIVE = registerSoundEvent("still_alive");
    public static final ResourceKey<JukeboxSong> STILL_ALIVE_KEY = createSong("wxb/still_alive");

    public static final Supplier<SoundEvent> WANT_YOU_GONE = registerSoundEvent("want_you_gone");
    public static final ResourceKey<JukeboxSong> WANT_YOU_GONE_KEY = createSong("wxb/want_you_gone");

    public static final Supplier<SoundEvent> WATER_ME_DOWN = registerSoundEvent("water_me_down");
    public static final ResourceKey<JukeboxSong> WATER_ME_DOWN_KEY = createSong("wxb/water_me_down");

    public static final Supplier<SoundEvent> AMONG_US_LOFI = registerSoundEvent("among_us_lofi");
    public static final ResourceKey<JukeboxSong> AMONG_US_LOFI_KEY = createSong("wxb/among_us_lofi");

    public static final Supplier<SoundEvent> VS_SONIC_EXE_RERUN = registerSoundEvent("vs_sonic_exe_rerun");
    public static final ResourceKey<JukeboxSong> VS_SONIC_EXE_RERUN_KEY = createSong("wxb/vs_sonic_exe_rerun");

    public static final Supplier<SoundEvent> GOD_DEVOURING_MANIA = registerSoundEvent("god_devouring_mania");
    public static final ResourceKey<JukeboxSong> GOD_DEVOURING_MANIA_KEY = createSong("wxb/god_devouring_mania");

    public static final Supplier<SoundEvent> INTERSTELLAR_DRIFT = registerSoundEvent("interstellar_drift");
    public static final ResourceKey<JukeboxSong> INTERSTELLAR_DRIFT_KEY = createSong("wxb/interstellar_drift");

    public static final Supplier<SoundEvent> IRRESISTIBLE_FORCE = registerSoundEvent("irresistible_force");
    public static final ResourceKey<JukeboxSong> IRRESISTIBLE_FORCE_KEY = createSong("wxb/irresistible_force");

    public static final Supplier<SoundEvent> KITCHEN_GUN = registerSoundEvent("kitchen_gun");
    public static final ResourceKey<JukeboxSong> KITCHEN_GUN_KEY = createSong("wxb/kitchen_gun");

    public static final Supplier<SoundEvent> LA_VAGUELETTE = registerSoundEvent("la_vaguelette");
    public static final ResourceKey<JukeboxSong> LA_VAGUELETTE_KEY = createSong("wxb/la_vaguelette");

    public static final Supplier<SoundEvent> PLEASANT_TIPSINESS = registerSoundEvent("pleasant_tipsiness");
    public static final ResourceKey<JukeboxSong> PLEASANT_TIPSINESS_KEY = createSong("wxb/pleasant_tipsiness");

    public static final Supplier<SoundEvent> POLUMNIA_OMNIA = registerSoundEvent("polumnia_omnia");
    public static final ResourceKey<JukeboxSong> POLUMNIA_OMNIA_KEY = createSong("wxb/polumnia_omnia");

    public static final Supplier<SoundEvent> TAR_TAR_TAGLIA = registerSoundEvent("tar_tar_taglia");
    public static final ResourceKey<JukeboxSong> TAR_TAR_TAGLIA_KEY = createSong("wxb/tar_tar_taglia");

    public static final Supplier<SoundEvent> ASGORE_RUNS_OVER_DESS = registerSoundEvent("asgore_runs_over_dess");
    public static final ResourceKey<JukeboxSong> ASGORE_RUNS_OVER_DESS_KEY = createSong("wxb/asgore_runs_over_dess");

    public static final Supplier<SoundEvent> NOD_KRAI = registerSoundEvent("nod_krai");
    public static final ResourceKey<JukeboxSong> NOD_KRAI_KEY = createSong("wxb/nod_krai");

    public static final Supplier<SoundEvent> SUMMER_TROPICALA = registerSoundEvent("summer_tropicala");
    public static final ResourceKey<JukeboxSong> SUMMER_TROPICALA_KEY = createSong("wxb/summer_tropicala");

    public static final Supplier<SoundEvent> DIGGY_DOGGY_HOLE = registerSoundEvent("diggy_diggy_hole");
    public static final ResourceKey<JukeboxSong> DIGGY_DIGGY_HOLE_KEY = createSong("wxb/diggy_diggy_hole");

    public static final Supplier<SoundEvent> HOUSE_OF_MIRRORS = registerSoundEvent("house_of_mirrors");
    public static final ResourceKey<JukeboxSong> HOUSE_OF_MIRRORS_KEY = createSong("wxb/house_of_mirrors");

    public static final Supplier<SoundEvent> REVENGE = registerSoundEvent("revenge");
    public static final ResourceKey<JukeboxSong> REVENGE_KEY = createSong("wxb/revenge");

    //PRIVATE DISCS
    //Podcasts
    public static final Supplier<SoundEvent> BEN_DROWNED = registerSoundEvent("ben_drowned");
    public static final ResourceKey<JukeboxSong> BEN_DROWNED_KEY = createSong("podcasts/ben_drowned");

    //Non-Main Playlist
    public static final Supplier<SoundEvent> SEXY_LUIGI = registerSoundEvent("sexy_luigi");
    public static final ResourceKey<JukeboxSong> SEXY_LUIGI_KEY = createSong("non-main/sexy_luigi");

    public static final Supplier<SoundEvent> HOPES_AND_DREAMS_REMASTER = registerSoundEvent("hopes_and_dreams_remaster");
    public static final ResourceKey<JukeboxSong> HOPES_AND_DREAMS_REMASTER_KEY = createSong("non-main/hopes_and_dreams_remaster");

    public static final Supplier<SoundEvent> FUHUHUHK = registerSoundEvent("fuhuhuhk");
    public static final ResourceKey<JukeboxSong> FUHUHU_KEY = createSong("non-main/fuhuhuhk");

    public static final Supplier<SoundEvent> LISTEN_MY_WAY =  registerSoundEvent("listen_my_way");
    public static final ResourceKey<JukeboxSong> LISTEN_MY_WAY_KEY = createSong("non-main/listen_my_way");

    public static final Supplier<SoundEvent> MARIOS_INVINCIBLE_SONG = registerSoundEvent("marios_invincible_song");
    public static final ResourceKey<JukeboxSong> MARIOS_INVINCIBLE_SONG_KEY = createSong("non-main/marios_invincible_song");

    public static final Supplier<SoundEvent> NEVER_GONNA_STOP = registerSoundEvent("never_gonna_stop");
    public static final ResourceKey<JukeboxSong> NEVER_GONNA_STOP_KEY = createSong("non-main/never_gonna_stop");

    public static final Supplier<SoundEvent> ONLY_FORCE_FOR_ME =  registerSoundEvent("only_force_for_me");
    public static final ResourceKey<JukeboxSong> ONLY_FORCE_FOR_ME_KEY = createSong("non-main/only_force_for_me");

    public static final Supplier<SoundEvent> RAMBLEY_KINITO_RAP = registerSoundEvent("rambley_kinito_rap");
    public static final ResourceKey<JukeboxSong> RAMBLEY_KINITO_RAP_KEY = createSong("non-main/rambley_kinito_rap");

    public static final Supplier<SoundEvent> REVENGE_2 =  registerSoundEvent("revenge_2");
    public static final ResourceKey<JukeboxSong> REVENGE_2_KEY = createSong("non-main/revenge_2");

    public static final Supplier<SoundEvent> SONICEXE_BENDROWNED_RAP = registerSoundEvent("sonicexe_bendrowned_rap");
    public static final ResourceKey<JukeboxSong> SONICEXE_BENDROWNED_RAP_KEY = createSong("non-main/sonicexe_bendrowned_rap");

    public static final Supplier<SoundEvent> STORY_OF_UNDERTALE_MOTI =  registerSoundEvent("story_of_undertale_moti");
    public static final ResourceKey<JukeboxSong> STORY_OF_UNDERTALE_MOTI_KEY = createSong("non-main/story_of_undertale_moti");

    public static final Supplier<SoundEvent> STRONGER_DAFT_SPEED =  registerSoundEvent("stronger_daft_speed");
    public static final ResourceKey<JukeboxSong> STRONGER_DAFT_SPEED_KEY = createSong("non-main/stronger_daft_speed");

    public static final Supplier<SoundEvent> SUSIE_YURI_RAP =  registerSoundEvent("susie_yuri_rap");
    public static final ResourceKey<JukeboxSong> SUSIE_YURI_RAP_KEY = createSong("non-main/susie_yuri_rap");

    public static final Supplier<SoundEvent> TRIPLE_THE_THREAT_NONGAGOS =   registerSoundEvent("triple_the_threat_nongagos");
    public static final ResourceKey<JukeboxSong> TRIPLE_THE_THREAT_NONGAGOS_KEY = createSong("non-main/triple_the_threat_nongagos");

    //Soundcloud
    public static final Supplier<SoundEvent> BACKBONE_REMIX =  registerSoundEvent("backbone_remix");
    public static final ResourceKey<JukeboxSong> BACKBONE_REMIX_KEY = createSong("soundcloud/backbone_remix");

    public static final Supplier<SoundEvent> LIKE_FATHER_LIKE_SON = registerSoundEvent("like_father_like_son");
    public static final ResourceKey<JukeboxSong> LIKE_FATHER_LIKE_SON_KEY = createSong("soundcloud/like_father_like_son");

    public static final Supplier<SoundEvent> MEGALOVANIA_DEMITALE = registerSoundEvent("megalovania_demitale");
    public static final ResourceKey<JukeboxSong> MEGALOVANIA_DEMITALE_KEY = createSong("soundcloud/megalovania_demitale");

    public static final Supplier<SoundEvent> MEGALOVANIA_ELEVATOR_JAZZ = registerSoundEvent("megalovania_elevator_jazz");
    public static final ResourceKey<JukeboxSong> MEGALOVANIA_ELEVATOR_JAZZ_KEY = createSong("soundcloud/megalovania_elevator_jazz");

    //Song Creation
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

