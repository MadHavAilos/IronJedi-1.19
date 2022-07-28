package net.madhav.ironjedi.event;

import net.madhav.ironjedi.IronJedi;
import net.madhav.ironjedi.item.ModItems;
import net.madhav.ironjedi.item.custom.LightsaberItem;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
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
            // force smite -> now handled as a custom enchantment
//            for (ItemStack armor : sourceEntity.getArmorSlots()) {
//                if (armor.getItem() == ModItems.JEDI_CLOAK.get()) {
//                    if (new Random().nextFloat() > 0.9f) { // 10% chance to smite the attacked entity
//                        EntityType.LIGHTNING_BOLT.spawn((ServerLevel) event.getEntity().level, null, null,
//                                event.getEntity().blockPosition(), MobSpawnType.TRIGGERED, true, true);
//                    }
//                }
//            }
        }
    }

}
