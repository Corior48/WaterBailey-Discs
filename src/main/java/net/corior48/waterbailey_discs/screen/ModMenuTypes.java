package net.corior48.waterbailey_discs.screen;

import net.corior48.waterbailey_discs.WaterBaileyDiscs;
import net.corior48.waterbailey_discs.screen.custom.MusicBlockMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, WaterBaileyDiscs.MODID);


    public static final DeferredHolder<MenuType<?>, MenuType<MusicBlockMenu>> MUSIC_BLOCK_MENU =
            MENUS.register("music_block_menu",
                    () -> new MenuType<>(MusicBlockMenu::new, FeatureFlags.DEFAULT_FLAGS));


    public static void register(IEventBus EventBus) {
        MENUS.register(EventBus);
    }
}
