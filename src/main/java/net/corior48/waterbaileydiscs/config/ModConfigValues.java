package net.corior48.waterbaileydiscs.config;

public class ModConfigValues {

    public static boolean showLyrics() {
        return ModClientConfig.SHOW_LYRICS.get();
    }

    public static boolean enableJukeboxKeybindLyrics() {
        return ModClientConfig.ENABLE_JUKEBOX_KEYBIND_LYRICS.get();
    }

    public static boolean showLyricsButton() {
        return ModClientConfig.SHOW_LYRICS_BUTTON.get();
    }

    public static boolean hardcoreDiscsEnabled() {return ModClientConfig.HARDCORE_DISCS_ENABLED.get();}
}