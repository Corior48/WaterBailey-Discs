package net.corior48.waterbailey_discs.screen.custom;

import net.corior48.waterbailey_discs.Config;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

public class ConfigScreen extends Screen {

    private final Screen parent;

    private boolean showLyrics;
    private boolean enableJukeboxKeybindLyrics;
    private boolean showLyricsButton;

    public ConfigScreen(Screen parent) {
        super(Component.literal("WaterBailey Discs Settings"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        super.init();

        this.showLyrics = Config.SHOW_LYRICS.get();
        this.enableJukeboxKeybindLyrics = Config.ENABLE_JUKEBOX_KEYBIND_LYRICS.get();
        this.showLyricsButton = Config.SHOW_LYRICS_BUTTON.get();

        int centerX = this.width / 2;
        int y = this.height / 2 - 40;

        this.addRenderableWidget(Button.builder(
                Component.literal(getShowLyricsText()),
                button -> {
                    this.showLyrics = !this.showLyrics;
                    button.setMessage(Component.literal(getShowLyricsText()));
                }
        ).bounds(centerX - 100, y, 200, 20).build());

        this.addRenderableWidget(Button.builder(
                Component.literal(getJukeboxKeybindText()),
                button -> {
                    this.enableJukeboxKeybindLyrics = !this.enableJukeboxKeybindLyrics;
                    button.setMessage(Component.literal(getJukeboxKeybindText()));
                }
        ).bounds(centerX - 100, y + 24, 200, 20).build());

        this.addRenderableWidget(Button.builder(
                Component.literal(getLyricsButtonText()),
                button -> {
                    this.showLyricsButton = !this.showLyricsButton;
                    button.setMessage(Component.literal(getLyricsButtonText()));
                }
        ).bounds(centerX - 100, y + 48, 200, 20).build());

        this.addRenderableWidget(Button.builder(
                Component.literal("Done"),
                button -> {
                    saveConfig();
                    if (this.minecraft != null) {
                        this.minecraft.setScreen(parent);
                    }
                }
        ).bounds(centerX - 100, y + 84, 98, 20).build());

        this.addRenderableWidget(Button.builder(
                Component.literal("Cancel"),
                button -> {
                    if (this.minecraft != null) {
                        this.minecraft.setScreen(parent);
                    }
                }
        ).bounds(centerX + 2, y + 84, 98, 20).build());
    }

    private String getShowLyricsText() {
        return "Lyrics: " + (this.showLyrics ? "ON" : "OFF");
    }

    private String getJukeboxKeybindText() {
        return "Jukebox Lyrics Keybind: " + (this.enableJukeboxKeybindLyrics ? "ON" : "OFF");
    }

    private String getLyricsButtonText() {
        return "Lyrics Button: " + (this.showLyricsButton ? "ON" : "OFF");
    }

    private void saveConfig() {
        Config.SHOW_LYRICS.set(this.showLyrics);
        Config.ENABLE_JUKEBOX_KEYBIND_LYRICS.set(this.enableJukeboxKeybindLyrics);
        Config.SHOW_LYRICS_BUTTON.set(this.showLyricsButton);

        Config.SPEC.save();
    }

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);

        guiGraphics.drawCenteredString(
                this.font,
                this.title,
                this.width / 2,
                this.height / 2 - 64,
                0xFFFFFF
        );

        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }
}