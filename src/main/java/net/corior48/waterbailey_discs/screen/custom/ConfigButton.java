package net.corior48.waterbailey_discs.screen.custom;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

public class ConfigButton extends Button {
    protected ConfigButton(int x, int y, int width, int height, Component message, OnPress onPress) {
        super(x, y, width, height, message, onPress, DEFAULT_NARRATION);
    }

    @Override
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        int color = this.isHovered ? 0xFFFFAA00 : 0xFFFFFFFF;

        graphics.drawCenteredString(
                net.minecraft.client.Minecraft.getInstance().font,
                this.getMessage(),
                this.getX() + this.width / 2,
                this.getY() + 4,
                color
        );
    }
}
