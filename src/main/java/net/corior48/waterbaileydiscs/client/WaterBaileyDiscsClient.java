package net.corior48.waterbaileydiscs.client;

import net.corior48.waterbaileydiscs.WaterBaileyDiscs;
import net.corior48.waterbaileydiscs.common.ClientHooks;
import net.corior48.waterbaileydiscs.client.handler.ClientPayloadHandler;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = WaterBaileyDiscs.MODID, dist = Dist.CLIENT)
public class WaterBaileyDiscsClient {
    public WaterBaileyDiscsClient() {
        ClientHooks.ACCESS = ClientPayloadHandler::handleLyricsResult;
    }
}
