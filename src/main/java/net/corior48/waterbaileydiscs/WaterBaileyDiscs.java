package net.corior48.waterbaileydiscs;

import net.corior48.waterbaileydiscs.block.ModBlocks;
import net.corior48.waterbaileydiscs.block.entity.ModBlockEntities;
import net.corior48.waterbaileydiscs.config.ModClientConfig;
import net.corior48.waterbaileydiscs.config.ModCommonConfig;
import net.corior48.waterbaileydiscs.item.ModCreativeModeTabs;
import net.corior48.waterbaileydiscs.item.ModItems;
import net.corior48.waterbaileydiscs.menu.ModMenuTypes;
import net.corior48.waterbaileydiscs.sound.ModSounds;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(WaterBaileyDiscs.MODID)
public class WaterBaileyDiscs {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "waterbaileydiscs";
    // Directly reference a slf4j logger
    public WaterBaileyDiscs(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        //Class Registries
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModSounds.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.MENUS.register(modEventBus);

        //Config
        modContainer.registerConfig(ModConfig.Type.CLIENT, ModClientConfig.SPEC);
        modContainer.registerConfig(ModConfig.Type.COMMON, ModCommonConfig.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
    }
}
