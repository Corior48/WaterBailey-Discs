package net.corior48.waterbailey_discs.network;

import io.netty.buffer.ByteBuf;
import net.corior48.waterbailey_discs.WaterBaileyDiscs;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record NearbyJukeboxLyricsResultPayload(String itemId) implements CustomPacketPayload {
    public static final Type<NearbyJukeboxLyricsResultPayload> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(WaterBaileyDiscs.MODID, "nearby_jukebox_lyrics_result"));

    public static final StreamCodec<ByteBuf, NearbyJukeboxLyricsResultPayload> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.STRING_UTF8,
                    NearbyJukeboxLyricsResultPayload::itemId,
                    NearbyJukeboxLyricsResultPayload::new
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
