package net.corior48.waterbaileydiscs.client.key;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class ModKeybinds {
    public static final String CATEGORY = "key.categories.waterbailey_discs";

    public static final KeyMapping OPEN_LYRICS = new KeyMapping(
            "key.waterbaileydiscs.open_lyrics",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_L,
            CATEGORY
    );
}
