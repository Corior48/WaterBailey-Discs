package net.corior48.waterbaileydiscs.config;

import net.corior48.waterbaileydiscs.common.DiscCatalog;
import net.neoforged.neoforge.common.ModConfigSpec;

public class ModClientConfig {
    public static final ModConfigSpec SPEC;

    public static final ModConfigSpec.BooleanValue SHOW_LYRICS;
    public static final ModConfigSpec.BooleanValue ENABLE_JUKEBOX_KEYBIND_LYRICS;
    public static final ModConfigSpec.BooleanValue SHOW_LYRICS_BUTTON;
    public static final ModConfigSpec.BooleanValue HARDCORE_DISCS_ENABLED;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

        builder.push("lyrics");

        SHOW_LYRICS = builder
                .comment("Enable lyrics UI")
                .define("showLyrics", true);

        ENABLE_JUKEBOX_KEYBIND_LYRICS = builder
                .comment("Allow keybind to open lyrics for nearby jukebox disc")
                .define("enableJukeboxKeybindLyrics", true);

        SHOW_LYRICS_BUTTON = builder
                .comment("Show lyrics button in the music block GUI when lyrics exist")
                .define("showLyricsButton", true);

        HARDCORE_DISCS_ENABLED = builder
                .comment("Subscribe to Hardcore Discs?")
                .define("hardcoreDiscsEnabled", false);

        builder.pop();

        SPEC = builder.build();
    }

    public static void setHardcoreDiscEnabled(boolean enabled) {
        HARDCORE_DISCS_ENABLED.set(enabled);
        SPEC.save();
        DiscCatalog.rebuildCatalog();
    }
}