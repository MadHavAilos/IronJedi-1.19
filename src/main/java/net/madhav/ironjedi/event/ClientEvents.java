package net.madhav.ironjedi.event;

import com.mojang.blaze3d.shaders.Effect;
import net.madhav.ironjedi.IronJedi;
import net.madhav.ironjedi.effect.FlyEffect;
import net.madhav.ironjedi.effect.ModEffects;
import net.madhav.ironjedi.util.KeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ClientEvents {

    @Mod.EventBusSubscriber(modid = IronJedi.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {

        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if (KeyBinding.FLY_KEY.isDown()) {
                Player player = Minecraft.getInstance().player;
                if (player.hasEffect(ModEffects.FLY.get())) {
                    player.setDeltaMovement(0.0, 1.0, 0.0);
                }
            }
        }

    }

    @Mod.EventBusSubscriber(modid = IronJedi.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {

        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeyBinding.FLY_KEY);
        }

    }

}
