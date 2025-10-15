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

    public static final Supplier<SoundEvent> SEWERCATS = registerSoundEvent("sewercats");
    public static final ResourceKey<JukeboxSong> SEWERCATS_KEY = createSong("sewercats");

    public static final Supplier<SoundEvent> ANGEL_HARE_SIDE_A = registerSoundEvent("angel_hare_side_a");
    public static final ResourceKey<JukeboxSong> ANGEL_HARE_SIDE_A_KEY = createSong("angel_hare_side_a");

    public static final Supplier<SoundEvent> OH_DESPAIR = registerSoundEvent("oh_despair");
    public static final ResourceKey<JukeboxSong> OH_DESPAIR_KEY = createSong("oh_despair");

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
