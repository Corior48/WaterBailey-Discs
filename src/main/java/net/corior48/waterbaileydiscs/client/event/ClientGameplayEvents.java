package net.corior48.waterbaileydiscs.client.event;

import net.corior48.waterbaileydiscs.WaterBaileyDiscs;
import net.corior48.waterbaileydiscs.client.key.ModKeybinds;
import net.corior48.waterbaileydiscs.config.ModConfigValues;
import net.corior48.waterbaileydiscs.network.RequestNearbyJukeboxLyricsPayload;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber(modid = WaterBaileyDiscs.MODID, value = Dist.CLIENT)
public class ClientGameplayEvents {

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player == null || minecraft.level == null) {
            return;
        }

        if (!ModConfigValues.enableJukeboxKeybindLyrics()) {
            return;
        }

        while (ModKeybinds.OPEN_LYRICS.consumeClick()) {
            if (minecraft.player != null) {
                minecraft.player.displayClientMessage(Component.literal("Lyrics key pressed"), true);
            }
            PacketDistributor.sendToServer(new RequestNearbyJukeboxLyricsPayload());
        }
    }
}