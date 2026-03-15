package net.corior48.waterbaileydiscs.client.screen;

import net.corior48.waterbaileydiscs.client.util.DiscLyrics;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;

import java.util.List;

public class LyricsPopupScreen extends Screen {



    private static final ResourceLocation DEFAULT_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("waterbaileydiscs", "textures/gui/lyrics_popup.png");

    private final Screen parent;
    private final Item discItem;
    private int scrollOffset = 0;
    private int scrollOffsetY = 0;
    private int scrollOffsetX = 0;

    private static final int PANEL_WIDTH = 240;
    private static final int PANEL_HEIGHT = 180;

    private Component makeLyricComponent(String text) {
        DiscLyrics.LyricEntry entry = DiscLyrics.getLyrics(discItem);

        if (entry != null && entry.font() != null && !entry.font().isBlank()) {
            ResourceLocation fontId = ResourceLocation.tryParse(entry.font());
            if (fontId != null) {
                return Component.literal(text)
                        .withStyle(style -> style.withFont(fontId));
            }
        }

        return Component.literal(text);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }

    private ResourceLocation getLyricsTexture() {
        DiscLyrics.LyricEntry entry = DiscLyrics.getLyrics(discItem);
        if (entry != null && entry.texture() != null && !entry.texture().isBlank()) {
            return ResourceLocation.fromNamespaceAndPath(
                    "waterbaileydiscs",
                    "textures/gui/lyrical_bgs/" + entry.texture() + ".png"
            );
        }

        return DEFAULT_TEXTURE;
    }

    public LyricsPopupScreen(Screen parent, Item discItem) {
        super(Component.literal("Lyrics"));
        this.parent = parent;
        this.discItem = discItem;

        DiscLyrics.loadAll();
    }

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        guiGraphics.fill(0, 0, this.width, this.height, 0x88000000);

        int panelX = (this.width - PANEL_WIDTH) / 2;
        int panelY = (this.height - PANEL_HEIGHT) / 2;
        int contentX = panelX + 8;
        int contentY = panelY + 17;

        int contentWidth = PANEL_WIDTH - 16;
        int contentHeight = PANEL_HEIGHT - 30;

        guiGraphics.blit(getLyricsTexture(), panelX, panelY, 0, 0, this.PANEL_WIDTH, this.PANEL_HEIGHT);

        DiscLyrics.LyricEntry entry = DiscLyrics.getLyrics(discItem);
        String title = entry != null ? entry.title() : "No Lyrics";

        guiGraphics.drawString(this.font, title, panelX + 6, panelY + 3, 0xFFFFFFFF, false);

        // manual X button
        int closeX = panelX + PANEL_WIDTH - 12;
        int closeY = panelY + 3;
        guiGraphics.drawString(this.font, "X", closeX, closeY, 0xFFFF6060, false);

        int textBoxX = contentX;
        int textBoxY = contentY;
        int textBoxWidth = contentWidth;
        int textBoxHeight = contentHeight;

        if (entry != null) {
            List<String> lines = entry.lines();

            int lineHeight = 10;
            int visibleLines = textBoxHeight / lineHeight;

            guiGraphics.enableScissor(
                    textBoxX,
                    textBoxY,
                    textBoxX + textBoxWidth,
                    textBoxY + textBoxHeight
            );

            for (int i = 0; i < visibleLines; i++) {
                int lineIndex = i + this.scrollOffsetY;
                if (lineIndex >= lines.size()) {
                    break;
                }

                Component lyricLine = makeLyricComponent(lines.get(lineIndex));
                drawBorderedText(
                        guiGraphics,
                        lyricLine,
                        textBoxX - this.scrollOffsetX,
                        textBoxY + (i * lineHeight),
                        0xFFFFFFFF,
                        0xFF000000
                );
            }
            guiGraphics.disableScissor();

            int hintY = contentY + contentHeight + 1;

            guiGraphics.drawString(
                    this.font,
                    Component.literal("Shift + Wheel = Horizontal Scroll"),
                    contentX,
                    hintY,
                    0xFF5555,
                    false
            );
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int panelX = (this.width - PANEL_WIDTH) / 2;
        int panelY = (this.height - PANEL_HEIGHT) / 2;

        int closeX = panelX + PANEL_WIDTH - 12;
        int closeY = panelY + 3;

        // click X to close
        if (mouseX >= closeX && mouseX < closeX + 8 && mouseY >= closeY && mouseY < closeY + 8) {
            if (this.minecraft != null) {
                this.minecraft.setScreen(parent);
            }
            return true;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        DiscLyrics.LyricEntry entry = DiscLyrics.getLyrics(discItem);
        if (entry == null || this.minecraft == null) {
            return super.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
        }

        int panelX = (this.width - this.PANEL_WIDTH) / 2;
        int panelY = (this.height - this.PANEL_HEIGHT) / 2;

        int textBoxX = panelX + 6;
        int textBoxY = panelY + 20;
        int textBoxWidth = this.PANEL_WIDTH - 12;
        int textBoxHeight = this.PANEL_HEIGHT - 36; // leave room for footer text

        boolean overTextBox = mouseX >= textBoxX && mouseX < textBoxX + textBoxWidth
                && mouseY >= textBoxY && mouseY < textBoxY + textBoxHeight;

        if (!overTextBox) {
            return super.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
        }

        long window = this.minecraft.getWindow().getWindow();
        boolean shiftHeld =
                GLFW.glfwGetKey(window, GLFW.GLFW_KEY_LEFT_SHIFT) == GLFW.GLFW_PRESS
                        || GLFW.glfwGetKey(window, GLFW.GLFW_KEY_RIGHT_SHIFT) == GLFW.GLFW_PRESS;

        // Longest line width for horizontal scroll clamp
        int maxWidth = 0;

        for (String line : entry.lines()) {
            maxWidth = Math.max(maxWidth, this.font.width(makeLyricComponent(line)));
        }

        int maxOffsetX = Math.max(0, maxWidth - textBoxWidth + 4);

        // Vertical scroll clamp
        int lineHeight = 10;
        int visibleLines = textBoxHeight / lineHeight;
        int maxOffsetY = Math.max(0, entry.lines().size() - visibleLines);

        // Horizontal if Shift is held OR if the device sends horizontal scroll directly
        if (shiftHeld || scrollX != 0) {
            double horizontalDelta = scrollX != 0 ? scrollX : scrollY;

            if (horizontalDelta > 0) {
                this.scrollOffsetX -= 10;
            } else if (horizontalDelta < 0) {
                this.scrollOffsetX += 10;
            }

            // Clamp to valid range
            this.scrollOffsetX = Math.max(0, Math.min(this.scrollOffsetX, maxOffsetX));

            return true;
        }

        // Otherwise vertical scroll
        if (scrollY > 0) {
            this.scrollOffsetY = Math.max(0, this.scrollOffsetY - 1);
        } else if (scrollY < 0) {
            this.scrollOffsetY = Math.min(maxOffsetY, this.scrollOffsetY + 1);
        }

        return true;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) { // ESC
            if (this.minecraft != null) {
                this.minecraft.setScreen(parent);
            }
            return true;
        }

        if (keyCode == 263) { // left arrow
            this.scrollOffsetX = Math.max(0, this.scrollOffsetX - 10);
            return true;
        }

        if (keyCode == 262) { // right arrow
            this.scrollOffsetX += 10;
            return true;
        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    private void drawBorderedText(GuiGraphics guiGraphics, Component text, int x, int y, int textColor, int borderColor) {
        guiGraphics.drawString(this.font, text, x - 1, y, borderColor, false);
        guiGraphics.drawString(this.font, text, x + 1, y, borderColor, false);
        guiGraphics.drawString(this.font, text, x, y - 1, borderColor, false);
        guiGraphics.drawString(this.font, text, x, y + 1, borderColor, false);

        guiGraphics.drawString(this.font, text, x, y, textColor, false);
    }

}