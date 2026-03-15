package net.corior48.waterbaileydiscs.client.util;

import com.google.gson.Gson;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.world.item.Item;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Items;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class DiscLyrics {

    private static boolean loaded = false;

    public record LyricEntry(String title, List<String> lines, String texture, String font) {}

    private static final Map<Item, LyricEntry> LYRICS = new HashMap<>();
    private static final Gson GSON = new Gson();

    public static boolean hasLyrics(Item item) {
        ResourceLocation id = BuiltInRegistries.ITEM.getKey(item);
        boolean found = LYRICS.containsKey(item);
        return found;
    }

    public static LyricEntry getLyrics(Item item) {
        ResourceLocation id = BuiltInRegistries.ITEM.getKey(item);
        LyricEntry entry = LYRICS.get(item);
        return entry;
    }

    public static void clear() {
        LYRICS.clear();
    }

    public static void loadAll() {
        if (loaded) return;

        clear();

        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft == null) {
            return;
        }

        try {
            Map<ResourceLocation, Resource> resources = minecraft.getResourceManager()
                    .listResources("lyrics", path -> path.getPath().endsWith(".json"));

            for (Map.Entry<ResourceLocation, Resource> entry : resources.entrySet()) {
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(entry.getValue().open(), StandardCharsets.UTF_8))) {

                    JsonLyricFile json = GSON.fromJson(reader, JsonLyricFile.class);
                    if (json == null || json.item == null || json.lines == null) {
                        continue;
                    }

                    ResourceLocation itemId = ResourceLocation.tryParse(json.item);
                    if (itemId == null) {
                        continue;
                    }

                    Item item = BuiltInRegistries.ITEM.get(itemId);
                    if (item == null || item == Items.AIR) {
                        continue;
                    }

                    String title = json.title != null ? json.title : item.getDescription().getString();
                    String texture = json.texture != null ? json.texture : "default";

                    LYRICS.put(item, new LyricEntry(title, List.copyOf(json.lines), texture, json.font));
                    System.out.println("Loaded lyric JSON for item: " + json.item);
                } catch (Exception e) {
                    System.err.println("Failed to load lyric file: " + entry.getKey() + " - " + e.getMessage());
                }
            }

            loaded = true; // <-- move it here
        } catch (Exception e) {
            System.err.println("Failed to scan lyric resources: " + e.getMessage());
        }
    }

    private static class JsonLyricFile {
        String item;
        String title;
        String texture;
        String font;
        List<String> lines;
    }
}