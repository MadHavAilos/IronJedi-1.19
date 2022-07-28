package net.madhav.ironjedi.event;

import net.madhav.ironjedi.IronJedi;
import net.madhav.ironjedi.entity.client.armor.JediCloakRenderer;
import net.madhav.ironjedi.item.custom.JediCloakItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

@Mod.EventBusSubscriber(modid = IronJedi.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventClientBusEvents {

    @SubscribeEvent
    public static void registerArmorRenerers(final EntityRenderersEvent.AddLayers event) {
        GeoArmorRenderer.registerArmorRenderer(JediCloakItem.class, new JediCloakRenderer());
    }

}
