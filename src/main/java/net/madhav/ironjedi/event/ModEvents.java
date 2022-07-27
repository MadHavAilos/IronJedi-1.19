package net.madhav.ironjedi.event;

import net.madhav.ironjedi.IronJedi;
import net.madhav.ironjedi.item.ModItems;
import net.madhav.ironjedi.item.custom.LightsaberItem;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = IronJedi.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public void lightsaberAttack(LivingHurtEvent event) {
        Entity sourceEntity = event.getSource().getEntity();

        if (sourceEntity instanceof Player) {
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

}
