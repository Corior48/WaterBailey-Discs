package net.corior48.waterbaileydiscs.network;

import net.corior48.waterbaileydiscs.WaterBaileyDiscs;
import net.corior48.waterbaileydiscs.client.handler.ClientPayloadHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.JukeboxBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.JukeboxBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
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

    private static Item findNearbyJukeboxDiscServer(ServerPlayer player) {
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

    private static Item getDiscFromJukeboxAt(ServerPlayer player, BlockPos pos) {
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
    private static void handleLyricsRequest(RequestNearbyJukeboxLyricsPayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            var player = context.player();
            if (!(player instanceof ServerPlayer serverPlayer)) {
                return;
            }

            Item discItem = findNearbyJukeboxDiscServer(serverPlayer);
            String itemId = discItem != null
                    ? BuiltInRegistries.ITEM.getKey(discItem).toString()
                    : "minecraft:air";

            PacketDistributor.sendToPlayer(serverPlayer, new NearbyJukeboxLyricsResultPayload(itemId));
        });
    }
    private static void handleLyricsResult(NearbyJukeboxLyricsResultPayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            net.corior48.waterbaileydiscs.common.ClientHooks.ACCESS.handleLyricsResult(payload);
        });
    }
}