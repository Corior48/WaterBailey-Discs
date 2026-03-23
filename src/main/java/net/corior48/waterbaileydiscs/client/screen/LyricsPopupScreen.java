package net.corior48.waterbaileydiscs.client.screen;

import net.corior48.waterbaileydiscs.client.util.DiscLyrics;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;

import java.util.List;

public class LyricsPopupScreen extends Screen {

    // =========================================================
    // Textures / static popup layout values
    // =========================================================

    private static final ResourceLocation DEFAULT_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("waterbaileydiscs", "textures/gui/lyrics_popup.png");

    private static final int PANEL_WIDTH = 240;
    private static final int PANEL_HEIGHT = 180;

    // =========================================================
    // Parent screen reference / popup data
    // =========================================================

    private final Screen parent;
    private final Item discItem;

    // =========================================================
    // Scroll state
    // =========================================================

    private int scrollOffset = 0;
    private int scrollOffsetY = 0;
    private int scrollOffsetX = 0;

    // =========================================================
    // Construction / setup
    // =========================================================

    public LyricsPopupScreen(Screen parent, Item discItem) {
        super(Component.literal("Lyrics"));
        this.parent = parent;
        this.discItem = discItem;

        // Ensure lyric data is available before rendering the popup
        DiscLyrics.loadAll();
    }

    // =========================================================
    // Core screen behavior
    // =========================================================

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    // =========================================================
    // Main popup rendering
    // =========================================================

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        // Dim the full background behind the popup
        guiGraphics.fill(0, 0, this.width, this.height, 0x88000000);

        // Center the popup panel on screen
        int panelX = (this.width - PANEL_WIDTH) / 2;
        int panelY = (this.height - PANEL_HEIGHT) / 2;

        // Inner content area of the popup
        int contentX = panelX + 8;
        int contentY = panelY + 17;
        int contentWidth = PANEL_WIDTH - 16;
        int contentHeight = PANEL_HEIGHT - 30;

        // Draw panel background texture
        guiGraphics.blit(getLyricsTexture(), panelX, panelY, 0, 0, this.PANEL_WIDTH, this.PANEL_HEIGHT);

        // Resolve current lyric entry and title
        DiscLyrics.LyricEntry entry = DiscLyrics.getLyrics(discItem);
        String title = entry != null ? entry.title() : "No Lyrics";

        // Draw title at the top of the popup
        guiGraphics.drawString(this.font, title, panelX + 6, panelY + 3, 0xFFFFFFFF, false);

        // Draw manual close button in top-right corner
        int closeX = panelX + PANEL_WIDTH - 12;
        int closeY = panelY + 3;
        guiGraphics.drawString(this.font, "X", closeX, closeY, 0xFFFF6060, false);

        // Text rendering box inside the popup body
        int textBoxX = contentX;
        int textBoxY = contentY;
        int textBoxWidth = contentWidth;
        int textBoxHeight = contentHeight;

        if (entry != null) {
            List<DiscLyrics.LyricLine> lines = entry.lines();

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

                DiscLyrics.LyricLine line = lines.get(lineIndex);

                drawColoredLine(
                        guiGraphics,
                        entry,
                        line,
                        textBoxX - this.scrollOffsetX,
                        textBoxY + (i * lineHeight)
                );
            }

            guiGraphics.disableScissor();

            // Draw horizontal-scroll help text below the lyric area
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

    // =========================================================
    // Mouse input handling
    // =========================================================

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int panelX = (this.width - PANEL_WIDTH) / 2;
        int panelY = (this.height - PANEL_HEIGHT) / 2;

        int closeX = panelX + PANEL_WIDTH - 12;
        int closeY = panelY + 3;

        // Close popup when the custom X button is clicked
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

        // Scrollable lyric text area bounds
        int textBoxX = panelX + 6;
        int textBoxY = panelY + 20;
        int textBoxWidth = this.PANEL_WIDTH - 12;
        int textBoxHeight = this.PANEL_HEIGHT - 36; // leave room for footer text

        boolean overTextBox = mouseX >= textBoxX && mouseX < textBoxX + textBoxWidth
                && mouseY >= textBoxY && mouseY < textBoxY + textBoxHeight;

        // Only scroll lyrics when the mouse is actually over the text area
        if (!overTextBox) {
            return super.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
        }

        long window = this.minecraft.getWindow().getWindow();
        boolean shiftHeld =
                GLFW.glfwGetKey(window, GLFW.GLFW_KEY_LEFT_SHIFT) == GLFW.GLFW_PRESS
                        || GLFW.glfwGetKey(window, GLFW.GLFW_KEY_RIGHT_SHIFT) == GLFW.GLFW_PRESS;

        // Calculate widest rendered lyric line for horizontal scroll clamping
        int maxWidth = 0;
        for (DiscLyrics.LyricLine line : entry.lines()) {
            maxWidth = Math.max(maxWidth, getLineWidth(entry, line));
        }
        int maxOffsetX = Math.max(0, maxWidth - textBoxWidth + 4);

        // Calculate maximum vertical scroll based on visible lines
        int lineHeight = 10;
        int visibleLines = textBoxHeight / lineHeight;
        int maxOffsetY = Math.max(0, entry.lines().size() - visibleLines);

        // Shift + wheel (or direct horizontal wheel input) scrolls sideways
        if (shiftHeld || scrollX != 0) {
            double horizontalDelta = scrollX != 0 ? scrollX : scrollY;

            if (horizontalDelta > 0) {
                this.scrollOffsetX -= 10;
            } else if (horizontalDelta < 0) {
                this.scrollOffsetX += 10;
            }

            // Keep horizontal scroll inside valid bounds
            this.scrollOffsetX = Math.max(0, Math.min(this.scrollOffsetX, maxOffsetX));
            return true;
        }

        // Standard wheel scroll moves vertically through lyric lines
        if (scrollY > 0) {
            this.scrollOffsetY = Math.max(0, this.scrollOffsetY - 1);
        } else if (scrollY < 0) {
            this.scrollOffsetY = Math.min(maxOffsetY, this.scrollOffsetY + 1);
        }

        return true;
    }

    // =========================================================
    // Keyboard input handling
    // =========================================================

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        // ESC returns to the parent screen
        if (keyCode == 256) {
            if (this.minecraft != null) {
                this.minecraft.setScreen(parent);
            }
            return true;
        }

        // Left arrow scrolls text horizontally to the left
        if (keyCode == 263) {
            this.scrollOffsetX = Math.max(0, this.scrollOffsetX - 10);
            return true;
        }

        // Right arrow scrolls text horizontally to the right
        if (keyCode == 262) {
            this.scrollOffsetX += 10;
            return true;
        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    // =========================================================
    // Lyrics data / styling helpers
    // =========================================================

    private Component makeLyricComponent(
            String text,
            DiscLyrics.LyricEntry entry,
            DiscLyrics.LyricLine line,
            DiscLyrics.LyricSegment segment
    ) {
        String fontName = segment.font();

        if (fontName == null || fontName.isBlank()) {
            fontName = line.font();
        }
        if (fontName == null || fontName.isBlank()) {
            fontName = entry.font();
        }

        MutableComponent component = Component.literal(text);

        if (fontName != null && !fontName.isBlank()) {
            ResourceLocation fontId = ResourceLocation.tryParse(fontName);
            if (fontId != null) {
                component = component.withStyle(style -> style.withFont(fontId));
            }
        }

        return component;
    }

    private ResourceLocation getLyricsTexture() {
        DiscLyrics.LyricEntry entry = DiscLyrics.getLyrics(discItem);

        // Use a disc-specific lyrics background if one exists
        if (entry != null && entry.texture() != null && !entry.texture().isBlank()) {
            return ResourceLocation.fromNamespaceAndPath(
                    "waterbaileydiscs",
                    "textures/gui/lyrical_bgs/" + entry.texture() + ".png"
            );
        }

        // Fall back to the default popup texture otherwise
        return DEFAULT_TEXTURE;
    }

    // =========================================================
    // Small draw helpers
    // =========================================================

    private int resolveColor(
            DiscLyrics.LyricEntry entry,
            DiscLyrics.LyricLine line,
            DiscLyrics.LyricSegment segment
    ) {
        String color = segment.color();

        if (color == null || color.isBlank()) {
            color = line.color();
        }
        if (color == null || color.isBlank()) {
            color = entry.defaultColor();
        }

        return parseHexColor(color, 0xFFFFFFFF);
    }

    private int getLineWidth(DiscLyrics.LyricEntry entry, DiscLyrics.LyricLine line) {
        int width = 0;

        for (DiscLyrics.LyricSegment segment : line.segments()) {
            width += this.font.width(makeLyricComponent(segment.text(), entry, line, segment));
        }
        return width;
    }
    private int parseHexColor(String color, int fallback) {
        if (color == null || color.isBlank()) return fallback;

        try {
            String clean = color.startsWith("#") ? color.substring(1) : color;
            if (clean.length() == 6) {
                return (int) (0xFF000000L | Long.parseLong(clean, 16));
            }
            if (clean.length() == 8) {
                return (int) Long.parseLong(clean, 16);
            }
        } catch (Exception ignore) {
        }
        return fallback;
    }

    private void drawBorderedSegment(GuiGraphics guiGraphics, Component text, int x, int y, int textColor, int borderColor) {
        guiGraphics.drawString(this.font, text, x - 1, y, borderColor, false);
        guiGraphics.drawString(this.font, text, x + 1, y, borderColor, false);
        guiGraphics.drawString(this.font, text, x, y - 1, borderColor, false);
        guiGraphics.drawString(this.font, text, x, y + 1, borderColor, false);

        guiGraphics.drawString(this.font, text, x - 1, y - 1, borderColor, false);
        guiGraphics.drawString(this.font, text, x + 1, y - 1, borderColor, false);
        guiGraphics.drawString(this.font, text, x - 1, y + 1, borderColor, false);
        guiGraphics.drawString(this.font, text, x + 1, y + 1, borderColor, false);

        guiGraphics.drawString(this.font, text, x, y, textColor, false);
    }

    private void drawColoredLine(
            GuiGraphics guiGraphics,
            DiscLyrics.LyricEntry entry,
            DiscLyrics.LyricLine line,
            int x,
            int y
    ) {
        int currentX = x;

        for (DiscLyrics.LyricSegment segment : line.segments()) {
            Component component = makeLyricComponent(segment.text(), entry, line, segment);
            int textColor = resolveColor(entry, line, segment);

            drawBorderedSegment(guiGraphics, component, currentX, y, textColor, 0xFF000000);
            currentX += this.font.width(component);
        }
    }
}