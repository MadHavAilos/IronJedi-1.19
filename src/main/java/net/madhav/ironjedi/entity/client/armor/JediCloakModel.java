package net.madhav.ironjedi.entity.client.armor;

import net.madhav.ironjedi.IronJedi;
import net.madhav.ironjedi.item.custom.JediCloakItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class JediCloakModel extends AnimatedGeoModel<JediCloakItem> {
    @Override
    public ResourceLocation getModelResource(JediCloakItem object) {
        return new ResourceLocation(IronJedi.MOD_ID, "geo/jedi_cloak.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(JediCloakItem object) {
        return new ResourceLocation(IronJedi.MOD_ID, "textures/models/armor/jedi_cloak_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(JediCloakItem animatable) {
        return new ResourceLocation(IronJedi.MOD_ID, "animations/armor_animation.json");
    }
}
