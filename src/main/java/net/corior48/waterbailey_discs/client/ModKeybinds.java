package net.corior48.waterbailey_discs.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.corior48.waterbailey_discs.WaterBaileyDiscs;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = WaterBaileyDiscs.MODID, value = Dist.CLIENT)
public class ModKeybinds {
    public static final String CATEGORY = "key.categories.waterbailey_discs";

    public static final KeyMapping OPEN_LYRICS = new KeyMapping(
            "key.waterbailey_discs.open_lyrics",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_L,
            CATEGORY
    );

    @SubscribeEvent
    public static void register(RegisterKeyMappingsEvent event) {
        event.register(OPEN_LYRICS);
    }
}
