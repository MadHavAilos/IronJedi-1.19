package net.madhav.ironjedi.event;

import net.madhav.ironjedi.IronJedi;
import net.madhav.ironjedi.item.ModItems;
import net.madhav.ironjedi.item.custom.LightsaberItem;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = IronJedi.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public void lightsaberAttack(LivingAttackEvent event) {
        System.out.println(event.getEntity().getMainHandItem().getItem());
        if (event.getEntity().getMainHandItem().getItem() == ModItems.LIGHTSABER.get()) {
            System.out.println("the item is a lightsaber");
            LightsaberItem item = (LightsaberItem) event.getEntity().getMainHandItem().getItem();
            if (!item.getActive()) {
                System.out.println("the lightsaber is inactive, cancelling");
                event.setCanceled(true);
            } else {
                System.out.println("the lightsaber is active, continuing");
            }
        }
    }

}
