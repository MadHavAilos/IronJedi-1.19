package net.madhav.ironjedi.event;

import net.madhav.ironjedi.IronJedi;
import net.madhav.ironjedi.effect.ModEffects;
import net.madhav.ironjedi.item.ModItems;
import net.madhav.ironjedi.item.custom.LightsaberItem;
import net.madhav.ironjedi.sound.ModSounds;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = IronJedi.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public void onDamagingEntity(LivingHurtEvent event) {
        Entity sourceEntity = event.getSource().getEntity();

        if (sourceEntity instanceof Player) {
            // limit lightsaber damage to active lightsaber
            for (ItemStack held : sourceEntity.getHandSlots()) {
                if (held.getItem() == ModItems.LIGHTSABER.get()) {
                    LightsaberItem saber = (LightsaberItem) held.getItem();
                    if (!saber.getActive()) {
                        event.setAmount(0);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void applyFly(LivingFallEvent event) {
        Entity sourceEntity = event.getEntity();
        if (sourceEntity instanceof Player) {
            if (((Player) sourceEntity).hasEffect(ModEffects.FLY.get())) {
                event.setDistance(0);
            }
        }
    }

}
