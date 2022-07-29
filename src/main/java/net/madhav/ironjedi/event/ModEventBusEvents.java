package net.madhav.ironjedi.event;

import net.madhav.ironjedi.IronJedi;
import net.madhav.ironjedi.particle.ModParticles;
import net.madhav.ironjedi.particle.custom.LightsaberSwingParticles;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = IronJedi.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(ModParticles.LIGHTSABER_SWING_PARTICLES.get(),
                LightsaberSwingParticles.Provider::new);
    }

}
