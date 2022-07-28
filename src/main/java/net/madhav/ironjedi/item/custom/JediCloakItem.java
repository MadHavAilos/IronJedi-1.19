package net.madhav.ironjedi.item.custom;

import com.google.common.collect.ImmutableMap;
import net.madhav.ironjedi.item.ModArmorMaterials;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.item.GeoArmorItem;

public class JediCloakItem extends GeoArmorItem implements IAnimatable {

    private AnimationFactory factory = new AnimationFactory(this);

    private static final MobEffectInstance[] CLOAK_EFFECTS = {new MobEffectInstance(MobEffects.REGENERATION, 300, 1),
            new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 300, 2)};

    private static final MobEffectInstance[] HOOD_EFFECTS = {new MobEffectInstance(MobEffects.INVISIBILITY, 300, 1)};

    public JediCloakItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
        super(materialIn, slot, builder);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<JediCloakItem>(this, "controller",
                20, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        if(!world.isClientSide()) {
            if (hasCloakOn(player)) {
                addStatusEffectForMaterial(player, CLOAK_EFFECTS);
            }
            if (hasHoodOn(player)) {
                addStatusEffectForMaterial(player, HOOD_EFFECTS);
            }
        }
    }

    private void addStatusEffectForMaterial(Player player, MobEffectInstance[] mapStatusEffectArray) {
        for (MobEffectInstance mapStatusEffect : mapStatusEffectArray) {
            boolean hasPlayerEffect = player.hasEffect(mapStatusEffect.getEffect());

            if (!hasPlayerEffect) {
                player.addEffect(new MobEffectInstance(mapStatusEffect.getEffect(),
                        mapStatusEffect.getDuration(), mapStatusEffect.getAmplifier()));
            }
        }
    }

    private boolean hasCloakOn(Player player) {
        if (hasArmorInSlot(player,2)) {
            ArmorItem breastplate = ((ArmorItem) player.getInventory().getArmor(2).getItem());

            return breastplate.getMaterial() == ModArmorMaterials.CLOAK;
        }
        return false;
    }

    private boolean hasHoodOn(Player player) {
        if (hasArmorInSlot(player, 3)) {
            ArmorItem helmet = ((ArmorItem) player.getInventory().getArmor(3).getItem());

            return helmet.getMaterial() == ModArmorMaterials.CLOAK;
        }
        return false;
    }

    private boolean hasArmorInSlot(Player player, int slot) {
        ItemStack armorItem = player.getInventory().getArmor(slot);

        return !armorItem.isEmpty();
    }

}
