package net.madhav.ironjedi.entity.client.armor;

import net.madhav.ironjedi.item.custom.JediCloakItem;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class JediCloakRenderer extends GeoArmorRenderer<JediCloakItem> {

    public JediCloakRenderer() {
        super(new JediCloakModel());

        this.bodyBone = "armorBody";
        this.rightArmBone = "armorRightArm";
        this.leftArmBone = "armorLeftArm";
    }

}
