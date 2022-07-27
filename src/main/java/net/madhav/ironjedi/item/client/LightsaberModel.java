package net.madhav.ironjedi.item.client;

import net.madhav.ironjedi.IronJedi;
import net.madhav.ironjedi.item.custom.LightsaberItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class LightsaberModel extends AnimatedGeoModel<LightsaberItem> {
    @Override
    public ResourceLocation getModelResource(LightsaberItem object) {
        return new ResourceLocation(IronJedi.MOD_ID, "geo/lightsaber.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(LightsaberItem object) {
        return new ResourceLocation(IronJedi.MOD_ID, "textures/item/lightsaber_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(LightsaberItem animatable) {
        return new ResourceLocation(IronJedi.MOD_ID, "animations/lightsaber.animation.json");
    }
}
