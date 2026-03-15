package net.corior48.waterbailey_discs;

import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

public class Config {
    public static final ModConfigSpec SPEC;
    public static final ModConfigSpec.BooleanValue SHOW_LYRICS;
    public static final ModConfigSpec.BooleanValue ENABLE_JUKEBOX_KEYBIND_LYRICS;
    public static final ModConfigSpec.BooleanValue SHOW_LYRICS_BUTTON;

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
        builder.pop();

        SPEC = builder.build();
    }
}