package net.corior48.waterbaileydiscs.client.handler;

import net.corior48.waterbaileydiscs.client.screen.LyricsPopupScreen;
import net.corior48.waterbaileydiscs.network.NearbyJukeboxLyricsResultPayload;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ClientPayloadHandler {

    public static void handleLyricsResult(NearbyJukeboxLyricsResultPayload payload) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.execute(() -> {
            if (minecraft.player == null) {
                return;
            }

            ResourceLocation id = ResourceLocation.tryParse(payload.itemId());
            if (id == null) {
                minecraft.player.displayClientMessage(Component.literal("No jukebox disc found nearby."), true);
                return;
            }

            Item item = BuiltInRegistries.ITEM.get(id);
            if (item == Items.AIR) {
                minecraft.player.displayClientMessage(Component.literal("No jukebox disc found nearby."), true);
                return;
            }

            Screen parent = minecraft.screen;
            minecraft.setScreen(new LyricsPopupScreen(parent, item));
        });
    }

    public interface ClientAccess {
        void handleLyricsResult(NearbyJukeboxLyricsResultPayload payload);
    }
}