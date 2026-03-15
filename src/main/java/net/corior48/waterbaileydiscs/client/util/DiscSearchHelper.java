package net.corior48.waterbaileydiscs.client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Locale;
import java.util.StringJoiner;

public class DiscSearchHelper {
    private DiscSearchHelper() {
    }

    public static boolean matchesQuery(ItemStack stack, String query) {
        if (query == null || query.isBlank()) {
            return true;
        }

        String searchable = buildSearchText(stack);
        String normalizedQuery = normalize(query);

        return searchable.contains(normalizedQuery);
    }

    public static String buildSearchText(ItemStack stack) {
        StringJoiner joiner = new StringJoiner(" ");

        // Item display name
        joiner.add(normalize(stack.getHoverName().getString()));

        // Tooltip / description lines
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft != null) {
            List<Component> tooltipLines = Screen.getTooltipFromItem(minecraft, stack);

            for (Component line : tooltipLines) {
                String text = line.getString();
                if (!text.isBlank()) {
                    joiner.add(normalize(text));
                }
            }
        }

        return joiner.toString();
    }

    private static String normalize(String text) {
        return text.toLowerCase(Locale.ROOT).trim();
    }
    public static String getDisplayDescription(ItemStack stack) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft != null) {
            List<Component> tooltipLines = Screen.getTooltipFromItem(minecraft, stack);

            // Line 0 is usually the item name, so start at 1
            for (int i = 1; i < tooltipLines.size(); i++) {
                String text = tooltipLines.get(i).getString().trim();
                if (!text.isBlank()) {
                    return text;
                }
            }
        }

        // fallback if no extra description exists
        return stack.getHoverName().getString();
    }
}

