package net.madhav.ironjedi.item.client;

import net.madhav.ironjedi.item.custom.LightsaberItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class LightsaberRenderer extends GeoItemRenderer<LightsaberItem> {
    public LightsaberRenderer() {
        super(new LightsaberModel());
    }
}
