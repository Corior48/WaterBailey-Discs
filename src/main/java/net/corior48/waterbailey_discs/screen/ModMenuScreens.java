package net.corior48.waterbailey_discs.screen;

import net.corior48.waterbailey_discs.WaterBaileyDiscs;
import net.corior48.waterbailey_discs.screen.custom.MusicBlockScreen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(modid = WaterBaileyDiscs.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModMenuScreens {
    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenuTypes.MUSIC_BLOCK_MENU.get(), MusicBlockScreen::new);
    }
}
