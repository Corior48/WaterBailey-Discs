package net.corior48.waterbaileydiscs.client.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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

    public record LyricLine(List<LyricSegment> segments, String color, String font) {}
    public record LyricSegment(String text, String color, String font) {}
    public record LyricEntry(String title, List<LyricLine> lines, String texture, String font, String defaultColor) {}

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
                    String currentFont = json.font;

                    List<LyricLine> parsedLines = new ArrayList<>();

                    for (JsonElement lineElement : json.lines) {

                        // 🔹 check for font switch command
                        if (lineElement.isJsonObject()) {
                            JsonObject obj = lineElement.getAsJsonObject();

                            if (obj.has("setFont")) {
                                currentFont = obj.get("setFont").getAsString();
                                continue; // skip rendering line
                            }
                        }

                        LyricLine line = parseLine(lineElement);

                        // apply current font if line doesn't override
                        if (line.font() == null || line.font().isBlank()) {
                            line = new LyricLine(line.segments(), line.color(), currentFont);
                        }

                        parsedLines.add(line);
                    }

                    String defaultColor = json.defaultColor != null ? json.defaultColor : "#FFFFFF";

                    LYRICS.put(item, new LyricEntry(title, List.copyOf(parsedLines), texture, json.font, defaultColor));
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

    private static LyricLine parseLine(JsonElement element) {
        List<LyricSegment> segments = new ArrayList<>();
        String lineColor = null;
        String lineFont = null;

        if (element == null || element.isJsonNull()) {
            segments.add(new LyricSegment("", null, null));
            return new LyricLine(segments, null, null);
        }

        // Old format: plain string
        if (element.isJsonPrimitive() && element.getAsJsonPrimitive().isString()) {
            segments.add(new LyricSegment(element.getAsString(), null, null));
            return new LyricLine(segments, null, null);
        }

        // Array format: list of segment objects
        if (element.isJsonArray()) {
            JsonArray array = element.getAsJsonArray();

            for (JsonElement segElement : array) {
                if (!segElement.isJsonObject()) continue;

                JsonObject obj = segElement.getAsJsonObject();
                String text = obj.has("text") ? obj.get("text").getAsString() : "";
                String color = obj.has("color") ? obj.get("color").getAsString() : null;
                String font = obj.has("font") ? obj.get("font").getAsString() : null;

                segments.add(new LyricSegment(text, color, font));
            }

            if (segments.isEmpty()) {
                segments.add(new LyricSegment("", null, null));
            }

            return new LyricLine(segments, null, null);
        }

        // Object format: line-level color/font + text or segments
        if (element.isJsonObject()) {
            JsonObject obj = element.getAsJsonObject();

            if (obj.has("color")) {
                lineColor = obj.get("color").getAsString();
            }
            if (obj.has("font")) {
                lineFont = obj.get("font").getAsString();
            }

            // Simple text line
            if (obj.has("text")) {
                String text = obj.get("text").getAsString();
                segments.add(new LyricSegment(text, null, null));
            }

            // Segment list
            if (obj.has("segments")) {
                JsonArray array = obj.getAsJsonArray("segments");

                for (JsonElement segElement : array) {
                    if (!segElement.isJsonObject()) continue;

                    JsonObject segObj = segElement.getAsJsonObject();
                    String text = segObj.has("text") ? segObj.get("text").getAsString() : "";
                    String color = segObj.has("color") ? segObj.get("color").getAsString() : null;
                    String font = segObj.has("font") ? segObj.get("font").getAsString() : null;

                    segments.add(new LyricSegment(text, color, font));
                }
            }
        }

        if (segments.isEmpty()) {
            segments.add(new LyricSegment("", null, null));
        }

        return new LyricLine(segments, lineColor, lineFont);
    }

    private static class JsonLyricFile {
        String item;
        String title;
        String texture;
        String font;
        String defaultColor;
        List<JsonElement> lines;
    }
}