package net.corior48.waterbaileydiscs.client.screen;

import net.corior48.waterbaileydiscs.Config;
import net.corior48.waterbaileydiscs.WaterBaileyDiscs;
import net.corior48.waterbaileydiscs.client.util.DiscLyrics;
import net.corior48.waterbaileydiscs.client.util.DiscSearchHelper;
import net.corior48.waterbaileydiscs.common.DiscCatalog;
import net.corior48.waterbaileydiscs.config.ModClientConfig;
import net.corior48.waterbaileydiscs.menu.MusicBlockMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class MusicBlockScreen extends AbstractContainerScreen<MusicBlockMenu> {
    private boolean categoryDropdownOpen = false;
    private DiscCatalog.DiscCategory activeCategory = DiscCatalog.DiscCategory.ALL;
    private EditBox searchBox;
    private final List<Integer> filteredIndexes = new ArrayList<>();
    private int filteredSelection = 0;
    private int displayedCost = 0;
    private long badgeAnimStartTime = 0L;
    private boolean badgeAnimating = false;
    private boolean lastHadBlankDisc = false;
    private DiscCatalog.DiscCategory lastAnimatedCategory = DiscCatalog.DiscCategory.ALL;


    private boolean lyricsPanelOpen = false;
    private int lyricsScrollOffset = 0;
    private static final int LYRICS_PANEL_WIDTH = 130;
    private static final int LYRICS_PANEL_HEIGHT = 96;
    private static final int LYRICS_BUTTON_WIDTH = 40;
    private static final int LYRICS_BUTTON_HEIGHT = 16;

    private float lyricsButtonAnim = 0.0F; // 0 = hidden, 1 = fully open
    private int lastLyricsSelected = -1;
    private boolean lastLyricsAvailable = false;


    private void startBadgeAnimation() {
        this.badgeAnimStartTime = System.currentTimeMillis();
        this.badgeAnimating = true;
    }

    private void updateBadgeAnimationTriggers() {
        boolean hasBlankDisc = this.menu.hasBlankDisc();

        // animate when a blank disc is newly inserted
        if (hasBlankDisc && !this.lastHadBlankDisc) {
            startBadgeAnimation();
        }

        // animate when category changes while a blank disc is present
        if (hasBlankDisc && this.activeCategory != this.lastAnimatedCategory) {
            startBadgeAnimation();
        }

        this.lastHadBlankDisc = hasBlankDisc;
        this.lastAnimatedCategory = this.activeCategory;
    }

    private boolean isMouseOverDropdown(int mouseX, int mouseY) {
        if (!this.categoryDropdownOpen) {
            return false;
        }

        int dropdownX = this.leftPos + 108;
        int dropdownY = this.topPos + 6;
        int dropdownListWidth = 110;
        int rowHeight = 16;
        int totalHeight = rowHeight * (DiscCatalog.DiscCategory.values().length + 1);

        return mouseX >= dropdownX && mouseX < dropdownX + dropdownListWidth
                && mouseY >= dropdownY && mouseY < dropdownY + totalHeight;
    }

    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(WaterBaileyDiscs.MODID, "textures/gui/music_block.png");
    private static final ResourceLocation LYRICS_BUTTON_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("waterbaileydiscs", "textures/gui/lyrics_button.png");

    public MusicBlockScreen(MusicBlockMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageWidth = 196;
        this.imageHeight = 166;
        this.inventoryLabelY = this.imageHeight - 94;
    }

    private float easeOutCubic(float t) {
        return 1.0F - (float)Math.pow(1.0F - t, 3.0);
    }

    private void renderAnimatedCostBadge(GuiGraphics guiGraphics) {
        if (this.categoryDropdownOpen) {
            return;
        }

        int selected = this.menu.getSelectedRecord();

        if (!this.menu.hasBlankDisc()
                || selected < 0
                || selected >= DiscCatalog.size()
                || this.minecraft == null
                || this.minecraft.player == null) {
            return;
        }

        int xpCost = DiscCatalog.getXpCost(selected);
        boolean canAfford = this.minecraft.player.isCreative() || this.minecraft.player.experienceLevel >= xpCost;

        String xpLabel = xpCost + "L";
        int textColor = canAfford ? 0xFF80FF20 : 0xFFFF6060;

        int badgeHeight = 12;
        int fullWidth = 36;
        int animatedWidth = fullWidth;

        int drawX = this.leftPos + 133;
        int drawY = this.topPos + 24;

        if (this.badgeAnimating) {
            float durationMs = 550.0F;
            float rawT = (System.currentTimeMillis() - this.badgeAnimStartTime) / durationMs;

            if (rawT >= 1.0F) {
                rawT = 1.0F;
                this.badgeAnimating = false;
            }

            float t = easeOutCubic(rawT);
            animatedWidth = Math.max(8, (int)(fullWidth * (0.4F + 0.6F * t)));
        }

        guiGraphics.fill(drawX, drawY, drawX + animatedWidth, drawY + badgeHeight, 0xFF2B2B2B);

        int textWidth = this.font.width(xpLabel);
        int textX = drawX + (animatedWidth / 2) - (textWidth / 2);
        int textY = drawY + 2;

        guiGraphics.drawString(this.font, xpLabel, textX, textY, textColor, false);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
        guiGraphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
        int baseY = this.topPos + 54;
        int leftArrowX = this.leftPos + 10;
        int nameX = this.leftPos + 34;
        int rightArrowX = this.leftPos + 142;

        guiGraphics.fill(leftArrowX, baseY, leftArrowX + 16, baseY + 16, 0xFF555555);
        guiGraphics.drawString(this.font, "<", leftArrowX + 5, baseY + 4, 0xFFFFFFFF, false);

        guiGraphics.fill(nameX, baseY, nameX + 104, baseY + 16, 0xFF333333);

        String label = "Insert Blank Disc";

        if (this.menu.hasBlankDisc()) {
            if (this.filteredIndexes.isEmpty()) {
                label = "No matching discs";
            } else {
                int selected = this.menu.getSelectedRecord();
                if (selected >= 0 && selected < DiscCatalog.size()) {
                    Item selectedItem = DiscCatalog.get(selected);

                    if (DiscLyrics.hasLyrics(selectedItem)) {
                        DiscLyrics.LyricEntry entry = DiscLyrics.getLyrics(selectedItem);
                        // draw entry.lines()
                    }
                }

                if (!this.filteredIndexes.contains(selected)) {
                    this.filteredSelection = 0;
                    selected = this.filteredIndexes.get(0);
                } else {
                    this.filteredSelection = this.filteredIndexes.indexOf(selected);
                }

                label = DiscSearchHelper.getDisplayDescription(new ItemStack(DiscCatalog.get(selected)));
            }
        }
        int selected = this.menu.getSelectedRecord();
        if (selected != this.lastLyricsSelected) {
            this.lastLyricsSelected = selected;
            this.lyricsScrollOffset = 0;

            if (!selectedDiscHasLyrics()) {
                this.lyricsPanelOpen = false;
            }
        }

        int dropdownX = this.leftPos + 108;
        int dropdownY = this.topPos + 6;
        int dropdownWidth = 60;
        int dropdownListWidth = 110;
        int rowHeight = 16;

        guiGraphics.fill(dropdownX, dropdownY, dropdownX + dropdownWidth, dropdownY + rowHeight, 0xFF000000);

        String buttonText = this.activeCategory.getDisplayName();
        String arrowText = "▼";

// draw category text
        drawScrollingClippedText(guiGraphics, buttonText, dropdownX, dropdownY, dropdownWidth - 10, 0xFFFFFFFF);

// draw dropdown arrow on the far right
        guiGraphics.drawString(this.font, arrowText, dropdownX + dropdownWidth - 10, dropdownY + 4, 0xFFFFFFFF, false);


        drawScrollingClippedText(guiGraphics, label, nameX, baseY, 104, 0xFFFFFFFF);

        guiGraphics.fill(rightArrowX, baseY, rightArrowX + 16, baseY + 16, 0xFF555555);
        guiGraphics.drawString(this.font, ">", rightArrowX + 5, baseY + 4, 0xFFFFFFFF, false);

        if (!this.menu.hasBlankDisc()) {
            guiGraphics.fill(leftArrowX, baseY, leftArrowX + 16, baseY + 16, 0x88000000);
            guiGraphics.fill(nameX, baseY, nameX + 104, baseY + 16, 0x88000000);
            guiGraphics.fill(rightArrowX, baseY, rightArrowX + 16, baseY + 16, 0x88000000);
        }

        //Render LyricButton
        if (this.lyricsButtonAnim > 0.0F) {
            renderLyricsButton(guiGraphics);
        }
    }

    private int getLyricsButtonX() {
        return this.leftPos + this.imageWidth - 23;
    }

    private int getLyricsButtonY() {
        return this.topPos + 5;
    }

    private void updateLyricsButtonAnimation() {
        boolean hasLyrics = ModClientConfig.SHOW_LYRICS.get()
                && ModClientConfig.SHOW_LYRICS_BUTTON.get()
                && this.menu.hasBlankDisc()
                && selectedDiscHasLyrics();

        float speed = 0.12F;

        if (hasLyrics) {
            this.lyricsButtonAnim = Math.min(1.0F, this.lyricsButtonAnim + speed);
        } else {
            this.lyricsButtonAnim = Math.max(0.0F, this.lyricsButtonAnim - speed);
        }

        this.lastLyricsAvailable = hasLyrics;
    }


    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {

        int dropdownX = this.leftPos + 108;
        int dropdownY = this.topPos + 6;
        int dropdownWidth = 60;
        int dropdownListWidth = 110;
        int rowHeight = 16;

        if (mouseX >= dropdownX && mouseX < dropdownX + dropdownWidth
                && mouseY >= dropdownY && mouseY < dropdownY + rowHeight) {
            this.categoryDropdownOpen = !this.categoryDropdownOpen;
            return true;
        }

        if (this.searchBox != null) {
            boolean clickedSearch = this.searchBox.isMouseOver(mouseX, mouseY);
            this.searchBox.setFocused(clickedSearch);
        }

        if (this.categoryDropdownOpen) {
            int totalHeight = rowHeight * (DiscCatalog.DiscCategory.values().length + 1);

            List<DiscCatalog.DiscCategory> values = DiscCatalog.getAvailableCategories();

            for (int i = 0; i < values.size(); i++) {
                int rowY = dropdownY + rowHeight + (i * rowHeight);

                if (mouseX >= dropdownX && mouseX < dropdownX + dropdownListWidth
                        && mouseY >= rowY && mouseY < rowY + rowHeight) {

                    this.activeCategory = values.get(i);
                    this.categoryDropdownOpen = false;
                    rebuildFilteredList();
                    return true;
                }
            }

            if (isMouseOverDropdown((int) mouseX, (int) mouseY)) {
                return true;
            }

            this.categoryDropdownOpen = false;
        }



        int baseY = this.topPos + 54;
        int leftArrowX = this.leftPos + 10;
        int rightArrowX = this.leftPos + 142;

        if (!this.menu.hasBlankDisc() || this.filteredIndexes.isEmpty()) {
            return super.mouseClicked(mouseX, mouseY, button);
        }

        if (mouseX >= leftArrowX && mouseX < leftArrowX + 16 && mouseY >= baseY && mouseY < baseY + 16) {
            this.filteredSelection = (this.filteredSelection - 1 + this.filteredIndexes.size()) % this.filteredIndexes.size();
            int realIndex = this.filteredIndexes.get(this.filteredSelection);
            this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, realIndex + 1000);
            return true;
        }

        if (mouseX >= rightArrowX && mouseX < rightArrowX + 16 && mouseY >= baseY && mouseY < baseY + 16) {
            this.filteredSelection = (this.filteredSelection + 1) % this.filteredIndexes.size();
            int realIndex = this.filteredIndexes.get(this.filteredSelection);
            this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, realIndex + 1000);
            return true;
        }

        int lyricsButtonX = getLyricsButtonX();
        int lyricsButtonY = getLyricsButtonY();
        int visibleLyricsWidth = Math.max(1, (int) (LYRICS_BUTTON_WIDTH * easeOutCubic(this.lyricsButtonAnim)));

        if (this.lyricsButtonAnim > 0.0F
                && mouseX >= lyricsButtonX && mouseX < lyricsButtonX + visibleLyricsWidth
                && mouseY >= lyricsButtonY && mouseY < lyricsButtonY + LYRICS_BUTTON_HEIGHT) {

            int selected = this.menu.getSelectedRecord();

            if (!ModClientConfig.SHOW_LYRICS.get()) {
                return true;
            }

            if (selected >= 0 && selected < DiscCatalog.size() && this.minecraft != null) {
                this.minecraft.setScreen(new LyricsPopupScreen(this, DiscCatalog.get(selected)));
                return true;
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        if (!this.lyricsPanelOpen || !selectedDiscHasLyrics()) {
            return super.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
        }

        int selected = this.menu.getSelectedRecord();
        var item = DiscCatalog.get(selected);
        DiscLyrics.LyricEntry entry = DiscLyrics.getLyrics(item);

        int panelX = this.leftPos + this.imageWidth + 4;
        int panelY = this.topPos + 8;
        int panelWidth = LYRICS_PANEL_WIDTH;
        int panelHeight = LYRICS_PANEL_HEIGHT;

        boolean overPanel = mouseX >= panelX && mouseX < panelX + panelWidth
                && mouseY >= panelY && mouseY < panelY + panelHeight;

        if (!overPanel) {
            return super.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
        }

        int lineHeight = 10;
        int visibleLines = (panelHeight - 20) / lineHeight;
        int maxOffset = Math.max(0, entry.lines().size() - visibleLines);

        if (scrollY > 0) {
            this.lyricsScrollOffset = Math.max(0, this.lyricsScrollOffset - 1);
        } else if (scrollY < 0) {
            this.lyricsScrollOffset = Math.min(maxOffset, this.lyricsScrollOffset + 1);
        }

        return true;
    }

    private void rebuildFilteredList() {

        if (!DiscCatalog.getAvailableCategories().contains(this.activeCategory)) {
            this.activeCategory = DiscCatalog.DiscCategory.ALL;
        }

        this.filteredIndexes.clear();

        String query = this.searchBox == null ? "" : this.searchBox.getValue().toLowerCase(java.util.Locale.ROOT).trim();

        for (int i = 0; i < DiscCatalog.size(); i++) {
            ItemStack stack = new ItemStack(DiscCatalog.get(i));

            boolean matchesCategory = DiscCatalog.matchesCategory(i, this.activeCategory);
            boolean matchesSearch = query.isEmpty() || DiscSearchHelper.matchesQuery(stack, query);

            if (matchesCategory && matchesSearch) {
                this.filteredIndexes.add(i);
            }
        }

        if (this.filteredIndexes.isEmpty()) {
            this.filteredSelection = 0;
            return;
        }

        int currentSelected = this.menu.getSelectedRecord();
        int foundIndex = this.filteredIndexes.indexOf(currentSelected);

        if (foundIndex >= 0) {
            this.filteredSelection = foundIndex;
        } else {
            this.filteredSelection = 0;
        }

        if (this.menu.hasBlankDisc() && this.minecraft != null && this.minecraft.gameMode != null) {
            int realIndex = this.filteredIndexes.get(this.filteredSelection);
            this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, realIndex + 1000);
        }
    }

    @Override
    protected void init() {
        super.init();
        DiscLyrics.loadAll();

        this.searchBox = new EditBox(this.font, this.leftPos + 8, this.topPos + 6, 96, 16, Component.literal("Search Discs"));
        this.searchBox.setMaxLength(50);
        this.searchBox.setResponder(value -> rebuildFilteredList());
        this.addRenderableWidget(this.searchBox);

        rebuildFilteredList();

        this.addRenderableWidget(new ConfigButton(
                this.leftPos + this.imageWidth - 23,
                this.topPos + 141,
                16,
                16,
                Component.literal("⚙"),
                button -> {
                    if (this.minecraft != null) {
                        this.minecraft.setScreen(new ConfigScreen(this));
                    }
                }
        ));
    }


    private boolean selectedDiscHasLyrics() {
        int selected = this.menu.getSelectedRecord();
        if (selected < 0 || selected >= DiscCatalog.size()) {
            return false;
        }

        return DiscLyrics.hasLyrics(DiscCatalog.get(selected));
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics, mouseX, mouseY, partialTick);
        super.render(graphics, mouseX, mouseY, partialTick);

        updateBadgeAnimationTriggers();
        updateLyricsButtonAnimation();

        renderAnimatedCostBadge(graphics);
        renderCategoryDropdown(graphics, mouseX, mouseY);

        int baseY = this.topPos + 54;
        int nameX = this.leftPos + 34;

        if (!isMouseOverDropdown(mouseX, mouseY) && this.menu.hasBlankDisc() && !this.filteredIndexes.isEmpty()) {
            int realIndex = this.filteredIndexes.get(this.filteredSelection);

            if (mouseX >= nameX && mouseX < nameX + 104 && mouseY >= baseY && mouseY < baseY + 16) {
                ItemStack hoveredStack = new ItemStack(DiscCatalog.get(realIndex));
                graphics.renderTooltip(this.font, hoveredStack, mouseX, mouseY);
            }
        }

        if (!isMouseOverDropdown(mouseX, mouseY)) {
            this.renderTooltip(graphics, mouseX, mouseY);
        }
    }
    private void drawScrollingClippedText(GuiGraphics guiGraphics, String text, int boxX, int boxY, int boxWidth, int color) {
        if (text == null || text.isEmpty()) {
            return;
        }

        int textWidth = this.font.width(text);
        int textY = boxY + 4;

        // If it fits, draw it normally
        if (textWidth <= boxWidth - 8) {
            guiGraphics.drawString(this.font, text, boxX + 4, textY, color, false);
            return;
        }

        // Add spacing so the scroll loops nicely
        String scrollingText = text + "   •   ";
        int scrollingWidth = this.font.width(scrollingText);

        long time = System.currentTimeMillis() / 150L; // smaller = faster scroll
        int offset = (int) (time % scrollingWidth);

        // Clip rendering to the text box area
        guiGraphics.enableScissor(boxX, boxY, boxX + boxWidth, boxY + 16);

        guiGraphics.drawString(this.font, scrollingText, boxX + 4 - offset, textY, color, false);
        guiGraphics.drawString(this.font, scrollingText, boxX + 4 - offset + scrollingWidth, textY, color, false);

        guiGraphics.disableScissor();
    }
    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (this.searchBox != null && this.searchBox.isFocused()) {
            // ESC unfocuses the search box instead of trapping you in it
            if (keyCode == 256) { // GLFW_KEY_ESCAPE
                this.searchBox.setFocused(false);
                return true;
            }

            if (this.searchBox.keyPressed(keyCode, scanCode, modifiers)) {
                rebuildFilteredList();
                return true;
            }

            return true;
        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean charTyped(char codePoint, int modifiers) {
        if (this.searchBox != null && this.searchBox.isFocused()) {
            if (this.searchBox.charTyped(codePoint, modifiers)) {
                rebuildFilteredList();
                return true;
            }
            return true;
        }

        return super.charTyped(codePoint, modifiers);
    }

    private void renderCategoryDropdown(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        if (!this.categoryDropdownOpen) {
            return;
        }

        int dropdownX = this.leftPos + 108;
        int dropdownY = this.topPos + 6;
        int dropdownListWidth = 110;
        int rowHeight = 16;

        List<DiscCatalog.DiscCategory> values = DiscCatalog.getAvailableCategories();

        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(0, 0, 500);

        for (int i = 0; i < values.size(); i++) {
            int rowY = dropdownY + rowHeight + (i * rowHeight);
            boolean selected = values.get(i) == this.activeCategory;

            guiGraphics.fill(
                    dropdownX,
                    rowY,
                    dropdownX + dropdownListWidth,
                    rowY + rowHeight,
                    selected ? 0xFF666699 : 0xFF000000
            );

            guiGraphics.drawString(
                    this.font,
                    values.get(i).getDisplayName(),
                    dropdownX + 4,
                    rowY + 4,
                    0xFFFFFFFF,
                    false
            );
        }

        guiGraphics.flush();
        guiGraphics.pose().popPose();
    }
    private void renderLyricsButton(GuiGraphics guiGraphics) {
        if (this.lyricsButtonAnim <= 0.0F) {
            return;
        }

        float eased = easeOutCubic(this.lyricsButtonAnim);
        int visibleWidth = Math.max(1, (int) (LYRICS_BUTTON_WIDTH * eased));

        int buttonX = getLyricsButtonX();
        int buttonY = getLyricsButtonY();

        guiGraphics.blit(
                LYRICS_BUTTON_TEXTURE,
                buttonX,
                buttonY,
                0,
                0,
                visibleWidth,
                LYRICS_BUTTON_HEIGHT,
                LYRICS_BUTTON_WIDTH,
                LYRICS_BUTTON_HEIGHT
        );

        if (visibleWidth >= 20) {
            guiGraphics.drawString(
                    this.font,
                    "Lyrics",
                    buttonX + 5,
                    buttonY + 4,
                    0xFFFFFFFF,
                    false
            );
        }
    }
}

