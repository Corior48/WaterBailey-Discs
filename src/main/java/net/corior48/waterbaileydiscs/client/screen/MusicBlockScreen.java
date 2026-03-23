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

import static net.minecraft.util.Mth.lerpInt;

public class MusicBlockScreen extends AbstractContainerScreen<MusicBlockMenu> {

    // =========================================================
    // Textures / static UI constants
    // =========================================================

    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(WaterBaileyDiscs.MODID, "textures/gui/music_block.png");

    private static final ResourceLocation LYRICS_BUTTON_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("waterbaileydiscs", "textures/gui/lyrics_button.png");

    private static final int LYRICS_PANEL_WIDTH = 130;
    private static final int LYRICS_PANEL_HEIGHT = 96;
    private static final int LYRICS_BUTTON_WIDTH = 40;
    private static final int LYRICS_BUTTON_HEIGHT = 16;

    private static final float CATEGORY_TEXT_SCALE = 0.75f;
    private static final int CATEGORY_TAB_PADDING = 10;
    private static final int CATEGORY_TAB_HEIGHT = 11;
    private static final int CATEGORY_TAB_SPACING = 2;
    private static final int CATEGORY_BAR_X_OFFSET = -6;

    // =========================================================
    // General screen state
    // =========================================================

    private EditBox searchBox;
    private int displayedCost = 0;

    // =========================================================
    // Disc filtering / selection state
    // =========================================================

    private final List<Integer> filteredIndexes = new ArrayList<>();
    private int filteredSelection = 0;

    // =========================================================
    // Category bar state
    // =========================================================

    private boolean categoryBarVisible = false;
    private int categoryBarHideTicks = 0;
    private float categoryBarAnim = 0.0f;

    private DiscCatalog.DiscCategory activeCategory = DiscCatalog.DiscCategory.ALL;
    private DiscCatalog.HardcoreSubCategory activeHardcoreSubCategory = DiscCatalog.HardcoreSubCategory.ALL;
    private DiscCatalog.DiscCategory lastAnimatedCategory = DiscCatalog.DiscCategory.ALL;

    // =========================================================
    // XP badge animation state
    // =========================================================

    private long badgeAnimStartTime = 0L;
    private boolean badgeAnimating = false;
    private boolean lastHadBlankDisc = false;

    // =========================================================
    // Lyrics UI state
    // =========================================================

    private boolean lyricsPanelOpen = false;
    private int lyricsScrollOffset = 0;
    private float lyricsButtonAnim = 0.0F; // 0 = hidden, 1 = fully open
    private int lastLyricsSelected = -1;
    private boolean lastLyricsAvailable = false;

    // =========================================================
    // Construction / initialization
    // =========================================================

    public MusicBlockScreen(MusicBlockMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageWidth = 196;
        this.imageHeight = 166;
        this.inventoryLabelY = this.imageHeight - 94;
    }

    @Override
    protected void init() {
        super.init();
        DiscLyrics.loadAll();

        // Search box for filtering visible discs by name / query
        this.searchBox = new EditBox(this.font, this.leftPos + 8, this.topPos + 6, 96, 16, Component.literal("Search Discs"));
        this.searchBox.setMaxLength(50);
        this.searchBox.setResponder(value -> rebuildFilteredList());
        this.addRenderableWidget(this.searchBox);

        // Build the first filtered result list after screen setup
        rebuildFilteredList();

        // Config button opens the mod config screen from this GUI
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

    // =========================================================
    // Main rendering lifecycle
    // =========================================================

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
        // Draw the base GUI texture
        guiGraphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);

        // Main selector row positions
        int baseY = this.topPos + 54;
        int leftArrowX = this.leftPos + 10;
        int nameX = this.leftPos + 34;
        int rightArrowX = this.leftPos + 142;

        // Left arrow button
        guiGraphics.fill(leftArrowX, baseY, leftArrowX + 16, baseY + 16, 0xFF555555);
        guiGraphics.drawString(this.font, "<", leftArrowX + 5, baseY + 4, 0xFFFFFFFF, false);

        // Center name/display box
        guiGraphics.fill(nameX, baseY, nameX + 104, baseY + 16, 0xFF333333);

        // Default label shown when no valid disc can be displayed
        String label = getCurrentDiscLabel();

        // Reset lyrics scroll when the selected disc changes
        int selected = this.menu.getSelectedRecord();
        if (selected != this.lastLyricsSelected) {
            this.lastLyricsSelected = selected;
            this.lyricsScrollOffset = 0;

            if (!selectedDiscHasLyrics()) {
                this.lyricsPanelOpen = false;
            }
        }

        // Draw the disc label, using scrolling text if too wide
        drawScrollingClippedText(guiGraphics, label, nameX, baseY, 104, 0xFFFFFFFF);

        // Right arrow button
        guiGraphics.fill(rightArrowX, baseY, rightArrowX + 16, baseY + 16, 0xFF555555);
        guiGraphics.drawString(this.font, ">", rightArrowX + 5, baseY + 4, 0xFFFFFFFF, false);

        // Gray out selector controls if a blank disc is not present
        if (!this.menu.hasBlankDisc()) {
            guiGraphics.fill(leftArrowX, baseY, leftArrowX + 16, baseY + 16, 0x88000000);
            guiGraphics.fill(nameX, baseY, nameX + 104, baseY + 16, 0x88000000);
            guiGraphics.fill(rightArrowX, baseY, rightArrowX + 16, baseY + 16, 0x88000000);
        }

        // Draw the animated lyrics button when visible
        if (this.lyricsButtonAnim > 0.0F) {
            renderLyricsButton(guiGraphics);
        }
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics, mouseX, mouseY, partialTick);
        super.render(graphics, mouseX, mouseY, partialTick);

        // Update animation triggers that depend on current selection / insert state
        updateBadgeAnimationTriggers();
        updateLyricsButtonAnimation();

        // Keep category bar visible while hovered, then allow it to fade away
        if (isHoveringCategoryArea(mouseX, mouseY)) {
            this.categoryBarHideTicks = 14;
        } else if (this.categoryBarHideTicks > 0) {
            this.categoryBarHideTicks--;
        }

        this.categoryBarVisible = this.categoryBarHideTicks > 0;

        // Animate category bar open / closed state
        float animSpeed = 0.15f;
        if (this.categoryBarVisible) {
            this.categoryBarAnim = Math.min(1.0f, this.categoryBarAnim + animSpeed);
        } else {
            this.categoryBarAnim = Math.max(0.0f, this.categoryBarAnim - animSpeed);
        }

        // Overlay UI pieces rendered after the base container
        renderAnimatedCostBadge(graphics);
        renderCategoryBar(graphics, mouseX, mouseY);

        int baseY = this.topPos + 54;
        int nameX = this.leftPos + 34;
        int nameWidth = 104;
        int nameHeight = 16;

        //Disc label tooltip
        if (mouseX >= nameX && mouseX < nameX + nameWidth
                && mouseY >= baseY && mouseY < baseY + nameHeight) {
            graphics.renderTooltip(this.font, Component.literal(getCurrentDiscLabel()), mouseX, mouseY);
        }

        this.renderTooltip(graphics, mouseX, mouseY);
    }

    // =========================================================
    // Mouse / keyboard input handling
    // =========================================================

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        // Focus/unfocus search box based on click position
        if (this.searchBox != null) {
            boolean clickedSearch = this.searchBox.isMouseOver(mouseX, mouseY);
            this.searchBox.setFocused(clickedSearch);
        }

        // Category bar handles its own click routing
        if (handleCategoryBarClick(mouseX, mouseY)) {
            return true;
        }

        int baseY = this.topPos + 54;
        int leftArrowX = this.leftPos + 10;
        int rightArrowX = this.leftPos + 142;

        // If there is nothing selectable, fall back to normal behavior
        if (!this.menu.hasBlankDisc() || this.filteredIndexes.isEmpty()) {
            return super.mouseClicked(mouseX, mouseY, button);
        }

        // Left arrow: move to previous filtered disc
        if (mouseX >= leftArrowX && mouseX < leftArrowX + 16 && mouseY >= baseY && mouseY < baseY + 16) {
            this.filteredSelection = (this.filteredSelection - 1 + this.filteredIndexes.size()) % this.filteredIndexes.size();
            int realIndex = this.filteredIndexes.get(this.filteredSelection);
            this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, realIndex + 1000);
            return true;
        }

        // Right arrow: move to next filtered disc
        if (mouseX >= rightArrowX && mouseX < rightArrowX + 16 && mouseY >= baseY && mouseY < baseY + 16) {
            this.filteredSelection = (this.filteredSelection + 1) % this.filteredIndexes.size();
            int realIndex = this.filteredIndexes.get(this.filteredSelection);
            this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, realIndex + 1000);
            return true;
        }

        // Lyrics button click handling
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
        // Only process lyric scrolling if the lyrics panel is currently open and valid
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

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        // Route keyboard input to the search box when focused
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
        // Route typed characters to the search box when focused
        if (this.searchBox != null && this.searchBox.isFocused()) {
            if (this.searchBox.charTyped(codePoint, modifiers)) {
                rebuildFilteredList();
                return true;
            }
            return true;
        }

        return super.charTyped(codePoint, modifiers);
    }

    // =========================================================
    // Filtering / selection logic
    // =========================================================

    private void rebuildFilteredList() {
        // If the currently active category disappears, fall back to ALL
        if (!DiscCatalog.getAvailableCategories().contains(this.activeCategory)) {
            this.activeCategory = DiscCatalog.DiscCategory.ALL;
        }

        this.filteredIndexes.clear();

        // Pull the current search text safely
        String query = this.searchBox == null ? "" : this.searchBox.getValue().toLowerCase(java.util.Locale.ROOT).trim();

        // Rebuild the visible list based on category, subcategory, and search query
        boolean canUseAdmin = canUseAdminDiscs();

        for (int i = 0; i < DiscCatalog.size(); i++) {
            ItemStack stack = new ItemStack(DiscCatalog.get(i));

            boolean matchesCategory = DiscCatalog.matchesCategory(i, this.activeCategory);
            boolean matchesHardcore = true;

            if (this.activeCategory == DiscCatalog.DiscCategory.HARDCORE) {
                matchesHardcore = DiscCatalog.matchesHardcoreSubCategory(i, this.activeHardcoreSubCategory);
            }

            boolean matchesSearch = query.isEmpty() || DiscSearchHelper.matchesQuery(stack, query);
            boolean allowed = !DiscCatalog.isAdminOnly(i) || canUseAdmin;

            if (matchesCategory && matchesHardcore && matchesSearch && allowed) {
                this.filteredIndexes.add(i);
            }
        }

        // If no results remain, reset selection index only
        if (this.filteredIndexes.isEmpty()) {
            this.filteredSelection = 0;
            return;
        }

        // Keep the same selected disc if it still exists in the filtered list
        int currentSelected = this.menu.getSelectedRecord();
        int foundIndex = this.filteredIndexes.indexOf(currentSelected);

        if (foundIndex >= 0) {
            this.filteredSelection = foundIndex;
        } else {
            this.filteredSelection = 0;
        }

        // Push the newly selected visible disc back into the menu selection
        if (this.menu.hasBlankDisc() && this.minecraft != null && this.minecraft.gameMode != null) {
            int realIndex = this.filteredIndexes.get(this.filteredSelection);
            this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, realIndex + 1000);
        }
    }

    // =========================================================
    // Category bar logic / rendering
    // =========================================================

    private boolean handleCategoryBarClick(double mouseX, double mouseY) {
        List<DiscCatalog.DiscCategory> categories = DiscCatalog.getAvailableCategories();
        int totalWidth = getTotalCategoryBarWidth(categories);
        int rowStartX = this.leftPos + (this.imageWidth / 2) - (totalWidth / 2);
        int rowY = this.topPos - 18;
        int buttonHeight = CATEGORY_TAB_HEIGHT;

        int buttonX = rowStartX;

        // Top-level category buttons
        for (DiscCatalog.DiscCategory category : categories) {
            String text = category.getDisplayName();
            int width = getCategoryTabWidth(text);

            if (mouseX >= buttonX && mouseX < buttonX + width
                    && mouseY >= rowY && mouseY < rowY + buttonHeight) {
                this.activeCategory = category;

                // Leaving HARDCORE resets subcategory selection
                if (category != DiscCatalog.DiscCategory.HARDCORE) {
                    this.activeHardcoreSubCategory = DiscCatalog.HardcoreSubCategory.ALL;
                }

                rebuildFilteredList();
                return true;
            }

            buttonX += width + CATEGORY_TAB_SPACING;
        }

        // Hardcore subcategory buttons
        if (this.activeCategory == DiscCatalog.DiscCategory.HARDCORE) {
            int subTotalWidth = getTotalHardcoreSubcategoryBarWidth();
            int subRowY = this.topPos - 34;
            int subButtonX = this.leftPos + (this.imageWidth / 2) - (subTotalWidth / 2);
            int subButtonHeight = CATEGORY_TAB_HEIGHT;

            for (DiscCatalog.HardcoreSubCategory subCategory : DiscCatalog.HardcoreSubCategory.values()) {
                String text = subCategory.getDisplayName();
                int width = getCategoryTabWidth(text);

                if (mouseX >= subButtonX && mouseX < subButtonX + width
                        && mouseY >= subRowY && mouseY < subRowY + subButtonHeight) {
                    this.activeHardcoreSubCategory = subCategory;
                    rebuildFilteredList();
                    return true;
                }

                subButtonX += width + CATEGORY_TAB_SPACING;
            }
        }

        return false;
    }

    private void renderCategoryBar(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        List<DiscCatalog.DiscCategory> categories = DiscCatalog.getAvailableCategories();

        // If fully hidden, draw only the collapsed tab
        if (!this.categoryBarVisible && this.categoryBarAnim <= 0.0f) {
            renderCollapsedCategoryTab(guiGraphics);
            return;
        }

        int collapsedX = getCollapsedCategoryX();
        int collapsedY = getCollapsedCategoryY();
        int collapsedWidth = getCategoryTabWidth("Categories");
        int collapsedCenterX = collapsedX + collapsedWidth / 2;

        int totalWidth = getTotalCategoryBarWidth(categories);
        int rowStartX = this.leftPos + (this.imageWidth / 2) - (totalWidth / 2);
        int finalY = this.topPos - 15;
        int buttonHeight = CATEGORY_TAB_HEIGHT;

        int buttonX = rowStartX;

        // Animate each category tab from the collapsed tab into its full position
        for (DiscCatalog.DiscCategory category : categories) {
            String text = category.getDisplayName();
            int width = getCategoryTabWidth(text);
            boolean selected = category == this.activeCategory;

            int startX = collapsedCenterX - width / 2;
            int startY = collapsedY;

            int animatedX = lerpInt(this.categoryBarAnim, startX, buttonX);
            int animatedY = lerpInt(this.categoryBarAnim, startY, finalY);

            int alpha = (int) (255 * this.categoryBarAnim);
            int textColor = (alpha << 24) | 0xFFFFFF;
            int bgAlpha = (int) (200 * this.categoryBarAnim);
            int bgColor = selected ? 0xFF666699 : ((bgAlpha << 24) | 0x000000);

            guiGraphics.fill(animatedX, animatedY, animatedX + width, animatedY + buttonHeight, bgColor);

            guiGraphics.pose().pushPose();
            guiGraphics.pose().scale(CATEGORY_TEXT_SCALE, CATEGORY_TEXT_SCALE, 1f);

            guiGraphics.drawString(
                    this.font,
                    text,
                    (int) ((animatedX + 3) / CATEGORY_TEXT_SCALE),
                    (int) ((animatedY + 2) / CATEGORY_TEXT_SCALE),
                    textColor,
                    false
            );

            guiGraphics.pose().popPose();

            buttonX += width + CATEGORY_TAB_SPACING;
        }

        // Only draw hardcore subcategories once the main category bar is basically fully opened
        if (this.activeCategory == DiscCatalog.DiscCategory.HARDCORE && this.categoryBarAnim > 0.95f) {
            renderHardcoreSubcategoryBar(guiGraphics, mouseX, mouseY);
        }
    }

    private void renderCollapsedCategoryTab(GuiGraphics guiGraphics) {
        int collapsedX = getCollapsedCategoryX();
        int collapsedY = getCollapsedCategoryY();
        int collapsedWidth = getCategoryTabWidth("Categories");
        int buttonHeight = CATEGORY_TAB_HEIGHT;

        guiGraphics.fill(
                collapsedX,
                collapsedY,
                collapsedX + collapsedWidth,
                collapsedY + buttonHeight,
                0xCC000000
        );

        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(CATEGORY_TEXT_SCALE, CATEGORY_TEXT_SCALE, 1f);

        guiGraphics.drawString(
                this.font,
                "Categories",
                (int) ((collapsedX + 3) / CATEGORY_TEXT_SCALE),
                (int) ((collapsedY + 2) / CATEGORY_TEXT_SCALE),
                0xFFFFFFFF,
                false
        );

        guiGraphics.pose().popPose();
    }

    private void renderHardcoreSubcategoryBar(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        DiscCatalog.HardcoreSubCategory[] subCategories = DiscCatalog.HardcoreSubCategory.values();

        int totalWidth = getTotalHardcoreSubcategoryBarWidth();
        int rowY = this.topPos - 34;
        int buttonX = this.leftPos + (this.imageWidth / 2) - (totalWidth / 2);
        int buttonHeight = CATEGORY_TAB_HEIGHT;

        for (DiscCatalog.HardcoreSubCategory subCategory : subCategories) {
            String text = subCategory.getDisplayName();
            int width = getCategoryTabWidth(text);
            boolean selected = subCategory == this.activeHardcoreSubCategory;

            guiGraphics.fill(
                    buttonX,
                    rowY,
                    buttonX + width,
                    rowY + buttonHeight,
                    selected ? 0xFF996666 : 0xCC000000
            );

            guiGraphics.pose().pushPose();
            guiGraphics.pose().scale(CATEGORY_TEXT_SCALE, CATEGORY_TEXT_SCALE, 1f);

            guiGraphics.drawString(
                    this.font,
                    text,
                    (int) ((buttonX + 3) / CATEGORY_TEXT_SCALE),
                    (int) ((rowY + 2) / CATEGORY_TEXT_SCALE),
                    0xFFFFFFFF,
                    false
            );

            guiGraphics.pose().popPose();

            buttonX += width + CATEGORY_TAB_SPACING;
        }
    }

    // =========================================================
    // Lyrics helpers / lyrics UI helpers
    // =========================================================

    private boolean selectedDiscHasLyrics() {
        int selected = this.menu.getSelectedRecord();
        if (selected < 0 || selected >= DiscCatalog.size()) {
            return false;
        }

        return DiscLyrics.hasLyrics(DiscCatalog.get(selected));
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

    private int getLyricsButtonX() {
        return this.leftPos + this.imageWidth - 23;
    }

    private int getLyricsButtonY() {
        return this.topPos + 5;
    }

    // =========================================================
    // XP badge animation / rendering
    // =========================================================

    private void startBadgeAnimation() {
        this.badgeAnimStartTime = System.currentTimeMillis();
        this.badgeAnimating = true;
    }

    private void updateBadgeAnimationTriggers() {
        boolean hasBlankDisc = this.menu.hasBlankDisc();

        // Animate when a blank disc is newly inserted
        if (hasBlankDisc && !this.lastHadBlankDisc) {
            startBadgeAnimation();
        }

        // Animate when category changes while a blank disc is present
        if (hasBlankDisc && this.activeCategory != this.lastAnimatedCategory) {
            startBadgeAnimation();
        }

        this.lastHadBlankDisc = hasBlankDisc;
        this.lastAnimatedCategory = this.activeCategory;
    }

    private void renderAnimatedCostBadge(GuiGraphics guiGraphics) {
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
            animatedWidth = Math.max(8, (int) (fullWidth * (0.4F + 0.6F * t)));
        }

        guiGraphics.fill(drawX, drawY, drawX + animatedWidth, drawY + badgeHeight, 0xFF2B2B2B);

        int textWidth = this.font.width(xpLabel);
        int textX = drawX + (animatedWidth / 2) - (textWidth / 2);
        int textY = drawY + 2;

        guiGraphics.drawString(this.font, xpLabel, textX, textY, textColor, false);
    }

    // =========================================================
    // Layout / hover / category sizing helpers
    // =========================================================

    private int getCategoryTabWidth(String text) {
        return (int) (this.font.width(text) * CATEGORY_TEXT_SCALE) + CATEGORY_TAB_PADDING;
    }

    private int getTotalCategoryBarWidth(List<DiscCatalog.DiscCategory> categories) {
        int total = 0;

        for (int i = 0; i < categories.size(); i++) {
            total += getCategoryTabWidth(categories.get(i).getDisplayName());
            if (i < categories.size() - 1) {
                total += CATEGORY_TAB_SPACING;
            }
        }

        return total;
    }

    private int getTotalHardcoreSubcategoryBarWidth() {
        int total = 0;
        DiscCatalog.HardcoreSubCategory[] subCategories = DiscCatalog.HardcoreSubCategory.values();

        for (int i = 0; i < subCategories.length; i++) {
            total += getCategoryTabWidth(subCategories[i].getDisplayName());
            if (i < subCategories.length - 1) {
                total += CATEGORY_TAB_SPACING;
            }
        }

        return total;
    }

    private int getCollapsedCategoryX() {
        int width = getCategoryTabWidth("Categories");
        return this.leftPos + (this.imageWidth / 2) - (width / 2);
    }

    private int getCollapsedCategoryY() {
        return this.topPos + 1;
    }

    private int getCategoryBarXOffset() {
        boolean hasHardcore = DiscCatalog.getAvailableCategories().contains(DiscCatalog.DiscCategory.HARDCORE);
        return hasHardcore ? -45 : -8;
    }

    private boolean isHoveringCategoryArea(int mouseX, int mouseY) {
        List<DiscCatalog.DiscCategory> categories = DiscCatalog.getAvailableCategories();

        int totalWidth = getTotalCategoryBarWidth(categories);
        int rowStartX = this.leftPos + (this.imageWidth / 2) - (totalWidth / 2);
        int rowEndX = rowStartX + totalWidth;

        int collapsedX = getCollapsedCategoryX();
        int collapsedY = getCollapsedCategoryY();
        int collapsedWidth = getCategoryTabWidth("Categories");
        int collapsedEndX = collapsedX + collapsedWidth;

        int panelLeft = Math.min(collapsedX, rowStartX) - 16;
        int panelRight = Math.max(collapsedEndX, rowEndX) + 16;

        int panelTop = Math.min(collapsedY, this.topPos - 15) - 10;
        int panelBottom = this.topPos + CATEGORY_TAB_HEIGHT + 10;

        if (this.activeCategory == DiscCatalog.DiscCategory.HARDCORE) {
            panelTop = Math.min(panelTop, this.topPos - 34 - 10);
        }

        return mouseX >= panelLeft && mouseX <= panelRight
                && mouseY >= panelTop && mouseY <= panelBottom;
    }

    // =========================================================
    // Small utility / drawing helpers
    // =========================================================

    private boolean canUseAdminDiscs() {
        return this.minecraft != null
                && this.minecraft.player != null
                && this.minecraft.player.hasPermissions(2);
    }

    private String getCurrentDiscLabel() {
        if (!this.menu.hasBlankDisc()) {
            return "Insert Blank Disc";
        }

        if (this.filteredIndexes.isEmpty()) {
            return "No matching discs";
        }

        int selected = this.menu.getSelectedRecord();

        if (!this.filteredIndexes.contains(selected)) {
            selected = this.filteredIndexes.get(0);
        }

        if (selected >= 0 && selected < DiscCatalog.size()) {
            return DiscSearchHelper.getDisplayDescription(new ItemStack(DiscCatalog.get(selected)));
        }

        return "Insert Blank Disc";
    }

    private float easeOutCubic(float t) {
        return 1.0F - (float) Math.pow(1.0F - t, 3.0);
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
}