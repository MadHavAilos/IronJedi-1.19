package net.madhav.ironjedi.item;

import net.madhav.ironjedi.IronJedi;
import net.madhav.ironjedi.item.custom.JediCloakItem;
import net.madhav.ironjedi.item.custom.LightsaberItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, IronJedi.MOD_ID);

    public static final RegistryObject<Item> KYBER_CRYSTAL = ITEMS.register("kyber_crystal",
            () -> new Item(new Item.Properties().fireResistant().tab(ModCreativeModeTab.IRON_JEDI_TAB)));

    public static final RegistryObject<Item> LIGHTSABER = ITEMS.register("lightsaber",
            () -> new LightsaberItem(new Item.Properties().tab(ModCreativeModeTab.IRON_JEDI_TAB)));

    public static final RegistryObject<Item> JEDI_CLOAK = ITEMS.register("jedi_cloak",
            () -> new JediCloakItem(ModArmorMaterials.CLOAK, EquipmentSlot.CHEST,
                    new Item.Properties().tab(ModCreativeModeTab.IRON_JEDI_TAB)));

    public static final RegistryObject<Item> JEDI_CLOAK_HOOD = ITEMS.register("jedi_cloak_hood",
            () -> new JediCloakItem(ModArmorMaterials.CLOAK, EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModCreativeModeTab.IRON_JEDI_TAB)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
