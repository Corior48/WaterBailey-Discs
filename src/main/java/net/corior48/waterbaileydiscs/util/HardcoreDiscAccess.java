package net.corior48.waterbaileydiscs.util;

import net.corior48.waterbaileydiscs.config.ModClientConfig;
import net.minecraft.world.entity.player.Player;

public class HardcoreDiscAccess {
    public static boolean shouldShowInCatalog(Player player) {
        return ModClientConfig.HARDCORE_DISCS_ENABLED.get();
    }
}
