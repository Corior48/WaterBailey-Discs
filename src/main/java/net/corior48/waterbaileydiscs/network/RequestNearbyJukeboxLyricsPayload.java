package net.corior48.waterbaileydiscs.network;

import io.netty.buffer.ByteBuf;
import net.corior48.waterbaileydiscs.WaterBaileyDiscs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record RequestNearbyJukeboxLyricsPayload() implements CustomPacketPayload {
    public static final Type<RequestNearbyJukeboxLyricsPayload> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(WaterBaileyDiscs.MODID, "request_nearby_jukebox_lyrics"));

    public static final StreamCodec<ByteBuf, RequestNearbyJukeboxLyricsPayload> STREAM_CODEC =
            StreamCodec.unit(new RequestNearbyJukeboxLyricsPayload());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}