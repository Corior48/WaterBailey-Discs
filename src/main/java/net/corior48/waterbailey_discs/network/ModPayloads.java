package net.corior48.waterbailey_discs.network;

import net.corior48.waterbailey_discs.WaterBaileyDiscs;
import net.corior48.waterbailey_discs.screen.custom.LyricsPopupScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.JukeboxBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.JukeboxBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;

@EventBusSubscriber(modid = WaterBaileyDiscs.MODID)
public class ModPayloads {

    @SubscribeEvent
    public static void register(RegisterPayloadHandlersEvent event) {
        var registrar = event.registrar("1");

        registrar.playToServer(
                RequestNearbyJukeboxLyricsPayload.TYPE,
                RequestNearbyJukeboxLyricsPayload.STREAM_CODEC,
                ModPayloads::handleLyricsRequest
        );

        registrar.playToClient(
                NearbyJukeboxLyricsResultPayload.TYPE,
                NearbyJukeboxLyricsResultPayload.STREAM_CODEC,
                ModPayloads::handleLyricsResult
        );
    }

    private static void handleLyricsRequest(RequestNearbyJukeboxLyricsPayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            var player = context.player();
            if (player == null || player.level() == null) {
                return;
            }

            Item discItem = findNearbyJukeboxDiscServer((ServerPlayer) player);
            String itemId = discItem != null
                    ? BuiltInRegistries.ITEM.getKey(discItem).toString()
                    : "minecraft:air";

            PacketDistributor.sendToPlayer((ServerPlayer) player, new NearbyJukeboxLyricsResultPayload(itemId));
        });

    }

    private static void handleLyricsResult(NearbyJukeboxLyricsResultPayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            Minecraft minecraft = Minecraft.getInstance();
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

    private static Item findNearbyJukeboxDiscServer(net.minecraft.server.level.ServerPlayer player) {

        BlockPos playerPos = player.blockPosition();
        int radius = 12;

        Item closestDisc = null;
        double closestDistance = Double.MAX_VALUE;

        for (BlockPos pos : BlockPos.betweenClosed(
                playerPos.offset(-radius, -radius, -radius),
                playerPos.offset(radius, radius, radius))) {

            Item disc = getDiscFromJukeboxAt(player, pos);
            if (disc == null) {
                continue;
            }

            double dist = pos.distToCenterSqr(player.position());
            if (dist < closestDistance) {
                closestDistance = dist;
                closestDisc = disc;
            }
        }

        return closestDisc;
    }

    private static Item getDiscFromJukeboxAt(net.minecraft.server.level.ServerPlayer player, BlockPos pos) {
        BlockState state = player.level().getBlockState(pos);
        if (!(state.getBlock() instanceof JukeboxBlock)) {
            return null;
        }

        BlockEntity be = player.level().getBlockEntity(pos);
        if (!(be instanceof JukeboxBlockEntity jukebox)) {
            return null;
        }

        ItemStack stack = jukebox.getTheItem();
        if (stack.isEmpty()) {
            return null;
        }

        return stack.getItem();
    }
}