package net.madhav.ironjedi.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {

    public static final String KEY_CATEGORY_IRON_JEDI = "key.category.ironjedi.ironjedi";
    public static final String KEY_FLY = "key.ironjedi.fly";

    public static final KeyMapping FLY_KEY = new KeyMapping(KEY_FLY, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_SPACE, KEY_CATEGORY_IRON_JEDI);

}
