package net.corior48.waterbailey_discs.client;

import net.corior48.waterbailey_discs.Config;
import net.corior48.waterbailey_discs.WaterBaileyDiscs;
import net.corior48.waterbailey_discs.network.RequestNearbyJukeboxLyricsPayload;
import net.corior48.waterbailey_discs.screen.custom.LyricsPopupScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.JukeboxBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.JukeboxBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber(modid = WaterBaileyDiscs.MODID, value = Dist.CLIENT)
public class ClientKeyHandler {

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.player == null || minecraft.level == null) {
            return;
        }

        while (ModKeybinds.OPEN_LYRICS.consumeClick()) {
            if (!Config.ENABLE_JUKEBOX_KEYBIND_LYRICS.get()) {
                return;
            }

            PacketDistributor.sendToServer(new RequestNearbyJukeboxLyricsPayload());
        }
    }

    private static Item findNearbyJukeboxDisc(Minecraft minecraft) {
        if (minecraft.player == null || minecraft.level == null) {
            return null;
        }

        // Check looked-at block first
        if (minecraft.hitResult instanceof BlockHitResult blockHit) {
            BlockPos lookedPos = blockHit.getBlockPos();
            System.out.println("Looking at block: " + lookedPos);
            Item lookedDisc = getDiscFromJukeboxAt(minecraft, lookedPos);
            if (lookedDisc != null) {
                System.out.println("Found looked-at jukebox disc: " + lookedDisc);
                return lookedDisc;
            }
        }

        // Fallback: scan nearby
        BlockPos playerPos = minecraft.player.blockPosition();
        int radius = 12;

        Item closestDisc = null;
        double closestDistance = Double.MAX_VALUE;

        for (BlockPos pos : BlockPos.betweenClosed(
                playerPos.offset(-radius, -radius, -radius),
                playerPos.offset(radius, radius, radius))) {

            Item disc = getDiscFromJukeboxAt(minecraft, pos);
            if (disc == null) {
                continue;
            }

            System.out.println("Found jukebox with disc at: " + pos + " -> " + disc);

            double dist = pos.distToCenterSqr(minecraft.player.position());
            if (dist < closestDistance) {
                closestDistance = dist;
                closestDisc = disc;
            }
        }

        return closestDisc;
    }

    private static Item getDiscFromJukeboxAt(Minecraft minecraft, BlockPos pos) {
        if (minecraft.level == null) {
            return null;
        }

        BlockState state = minecraft.level.getBlockState(pos);
        if (!(state.getBlock() instanceof JukeboxBlock)) {
            return null;
        }

        System.out.println("Jukebox block found at: " + pos);

        BlockEntity be = minecraft.level.getBlockEntity(pos);
        if (!(be instanceof JukeboxBlockEntity jukebox)) {
            System.out.println("Block entity at jukebox position is not a JukeboxBlockEntity");
            return null;
        }

        ItemStack stack = jukebox.getTheItem();
        System.out.println("Jukebox stack at " + pos + ": " + stack);

        if (stack.isEmpty()) {
            return null;
        }

        return stack.getItem();
    }
}