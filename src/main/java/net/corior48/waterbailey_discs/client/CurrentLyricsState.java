package net.corior48.waterbailey_discs.client;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class CurrentLyricsState {
    private static Item currentDisc = Items.AIR;

    public static void setCurrentDisc(Item item) {
        currentDisc = item;
    }

    public static Item getCurrentDisc() {
        return currentDisc;
    }

    public static boolean hasCurrentDisc() {
        return currentDisc != Items.AIR;
    }

    public static void clear() {
        currentDisc = Items.AIR;
    }
}
