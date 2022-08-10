package net.madhav.ironjedi.item.custom;

import net.madhav.ironjedi.effect.ModEffects;
import net.madhav.ironjedi.item.ModArmorMaterials;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class IronGoldArmorItem extends ArmorItem {

    private static final MobEffectInstance[] FULL_ARMOR_EFFECTS = {new MobEffectInstance(ModEffects.FLY.get(), 300, 1)};

    public IronGoldArmorItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
        super(materialIn, slot, builder);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        if(!world.isClientSide()) {
            if (hasFullArmorOn(player)) {
                addStatusEffectForMaterial(player, FULL_ARMOR_EFFECTS);
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

    private boolean hasFullArmorOn(Player player) {
        return hasArmorInAllSlots(player) && hasCorrectArmorOn(player);
    }

    private boolean hasCorrectArmorOn(Player player) {
        ArmorItem boots = ((ArmorItem) player.getInventory().getArmor(0).getItem());
        ArmorItem leggings = ((ArmorItem) player.getInventory().getArmor(1).getItem());
        ArmorItem chestplate = ((ArmorItem) player.getInventory().getArmor(2).getItem());
        ArmorItem helmet = ((ArmorItem) player.getInventory().getArmor(3).getItem());

        return boots.getMaterial() == ModArmorMaterials.IRON_GOLD && leggings.getMaterial() == ModArmorMaterials.IRON_GOLD &&
                chestplate.getMaterial() == ModArmorMaterials.IRON_GOLD && helmet.getMaterial() == ModArmorMaterials.IRON_GOLD;

    }

    private boolean hasArmorInAllSlots(Player player) {
        return hasArmorInSlot(player, 0) && hasArmorInSlot(player, 1) && hasArmorInSlot(player, 2) && hasArmorInSlot(player, 3);
    }

    private boolean hasArmorInSlot(Player player, int slot) {
        ItemStack armorItem = player.getInventory().getArmor(slot);

        return !armorItem.isEmpty();
    }

}
