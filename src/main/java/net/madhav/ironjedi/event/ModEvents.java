package net.madhav.ironjedi.event;

import net.madhav.ironjedi.IronJedi;
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

//    @SubscribeEvent
//    public void onLeftClick(PlayerInteractEvent.LeftClickEmpty event) {
//        Entity sourceEntity = event.getEntity();
//
//        if (sourceEntity instanceof Player) {
//            for (ItemStack held : sourceEntity.getHandSlots()) {
//                if (held.getItem() == ModItems.LIGHTSABER.get()) {
//                    LightsaberItem saber = (LightsaberItem) held.getItem();
//                    if (saber.getActive()) {
//                        // Play a random swing sound
//                        if (new Random().nextFloat() > 0.5) {
//                            sourceEntity.getLevel().playSound((Player) sourceEntity, sourceEntity.blockPosition(), ModSounds.LIGHTSABER_SWING1.get(),
//                                    SoundSource.PLAYERS, 0.7f, 1f);
//                        } else {
//                            sourceEntity.getLevel().playSound((Player) sourceEntity, sourceEntity.blockPosition(), ModSounds.LIGHTSABER_SWING2.get(),
//                                    SoundSource.PLAYERS, 0.7f, 1f);
//                        }
//                    }
//                }
//            }
//        }
//    }

}
