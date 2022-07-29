package net.madhav.ironjedi.sound;

import net.madhav.ironjedi.IronJedi;
import net.minecraft.client.sounds.SoundEngine;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, IronJedi.MOD_ID);

    public static final RegistryObject<SoundEvent> LIGHTSABER_ACTIVATE =
            registerSoundEvent("lightsaber_activate");

    public static final RegistryObject<SoundEvent> LIGHTSABER_DEACTIVATE =
            registerSoundEvent("lightsaber_deactivate");

    public static final RegistryObject<SoundEvent> LIGHTSABER_IDLE1 =
            registerSoundEvent("lightsaber_idle1");

    public static final RegistryObject<SoundEvent> LIGHTSABER_IDLE2 =
            registerSoundEvent("lightsaber_idle2");

    public static final RegistryObject<SoundEvent> LIGHTSABER_SWING1 =
            registerSoundEvent("lightsaber_swing1");

    public static final RegistryObject<SoundEvent> LIGHTSABER_SWING2 =
            registerSoundEvent("lightsaber_swing2");

    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(IronJedi.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }

}
