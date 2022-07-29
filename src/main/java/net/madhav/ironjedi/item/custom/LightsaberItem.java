package net.madhav.ironjedi.item.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.madhav.ironjedi.item.client.LightsaberRenderer;
import net.madhav.ironjedi.particle.ModParticles;
import net.madhav.ironjedi.sound.ModSounds;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Random;
import java.util.function.Consumer;

public class LightsaberItem extends Item implements IAnimatable {

    private final float attackDamage;
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public AnimationFactory factory = new AnimationFactory(this);

    private boolean active = false;

    public LightsaberItem(Properties properties) {
        super(properties);
        this.attackDamage = 13f;
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", (double)this.attackDamage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", (double)1.0, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }

    public boolean getActive() {
        return active;
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        super.initializeClient(consumer);
        consumer.accept(new IClientItemExtensions() {
            private final BlockEntityWithoutLevelRenderer renderer = new LightsaberRenderer();

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return renderer;
            }
        });
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
    }

    private <E extends IAnimatable>PlayState predicate(AnimationEvent<E> event) {
        if (active) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("lightsaber_on", false));
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("lightsaber_off", false)
                    .addAnimation("lightsaber_idle_off", true));
        }

        return PlayState.CONTINUE;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide() && hand == InteractionHand.MAIN_HAND) {
            active = !active;
        }
        if (active) {
            level.playSound(player, player, ModSounds.LIGHTSABER_DEACTIVATE.get(),
                    SoundSource.PLAYERS, 0.7f, 1f);
        } else {
            level.playSound(player, player, ModSounds.LIGHTSABER_ACTIVATE.get(),
                    SoundSource.PLAYERS, 0.7f, 1f);
        }

        return super.use(level, player, hand);
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
        if (entity.getLevel().isClientSide() && getActive()) {
            // Play a random swing sound
            if (entity.level.random.nextFloat() > 0.5) {
                entity.getLevel().playSound((Player) entity, entity.blockPosition(), ModSounds.LIGHTSABER_SWING1.get(),
                        SoundSource.PLAYERS, 0.7f, 1f);
            } else {
                entity.getLevel().playSound((Player) entity, entity.blockPosition(), ModSounds.LIGHTSABER_SWING2.get(),
                        SoundSource.PLAYERS, 0.7f, 1f);
            }
            spawnAttackParticles((Player) entity);
        }

        return super.onEntitySwing(stack, entity);
    }

    private void spawnAttackParticles(Player player) {
        for (int i = 0; i < 180; i+=10) {
            double angle = Math.toRadians(i - player.getYRot() + 180);
            float radius = 1.4f;

            double dx = Math.cos(angle) * radius;
            double dz = -Math.sin(angle) * radius;

            player.getLevel().addParticle(ModParticles.LIGHTSABER_SWING_PARTICLES.get(),
                    player.getX() + dx, player.getY() + 1.1f, player.getZ() + dz,
                    0.0d, 0.0d, 0.0d);
        }
    }

    public boolean hurtEnemy(ItemStack p_43278_, LivingEntity p_43279_, LivingEntity p_43280_) {
        p_43278_.hurtAndBreak(1, p_43280_, (p_43296_) -> {
            p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });
        return true;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot equipmentSlot) {
        return equipmentSlot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(equipmentSlot);
    }

}
