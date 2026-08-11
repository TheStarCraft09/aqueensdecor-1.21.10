package dev.lumareth.aqueensdecor.Util;

import net.minecraft.world.item.ItemStack;

public class RunicUtils {
    public static boolean isDepleted(ItemStack stack) {
        return stack.has(ModDataComponents.RUNIC_DEPLETED);
    }

}
