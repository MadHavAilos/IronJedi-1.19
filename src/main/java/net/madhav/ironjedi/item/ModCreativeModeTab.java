package net.madhav.ironjedi.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {

    public static final CreativeModeTab IRON_JEDI_TAB = new CreativeModeTab("ironjeditab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.KYBER_CRYSTAL.get());
        }
    };

}
