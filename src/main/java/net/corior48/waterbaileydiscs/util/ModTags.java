package net.corior48.waterbaileydiscs.util;

import net.corior48.waterbaileydiscs.WaterBaileyDiscs;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {

    public static class Items {
    public static final TagKey<Item> MUSIC_DISCS = createTag("music_discs");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create( ResourceLocation.fromNamespaceAndPath(WaterBaileyDiscs.MODID, name));
        }
    }


}
