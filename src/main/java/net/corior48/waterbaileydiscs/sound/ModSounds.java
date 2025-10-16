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
