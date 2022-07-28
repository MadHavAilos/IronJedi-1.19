package net.madhav.ironjedi.enchantment;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

import java.util.Random;

public class JediSmiteEnchantment extends Enchantment {

    public JediSmiteEnchantment(Rarity rarity, EnchantmentCategory category, EquipmentSlot... equipmentSlots) {
        super(rarity, category, equipmentSlots);
    }

    @Override
    public void doPostAttack(LivingEntity attacker, Entity target, int level) {
        if (!attacker.level.isClientSide()) {
            ServerLevel world = (ServerLevel) attacker.level;
            BlockPos position = target.blockPosition();

            if (level == 1) {
                if (new Random().nextFloat() > 0.9f) {
                    EntityType.LIGHTNING_BOLT.spawn(world, null, null, position,
                            MobSpawnType.TRIGGERED, true, true);
                }
            }

            if (level == 2) {
                if (new Random().nextFloat() > 0.5f) {
                    EntityType.LIGHTNING_BOLT.spawn(world, null, null, position,
                            MobSpawnType.TRIGGERED, true, true);
                }
            }
        }

        super.doPostAttack(attacker, target, level);
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

}
