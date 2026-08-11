package dev.lumareth.aqueensdecor.Util;

import dev.lumareth.aqueensdecor.Enchantment.ModRunicEnchantments;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;

public class RunicUtils {
    public static boolean isDepleted(ItemStack stack) {
        return stack.has(ModDataComponents.RUNIC_DEPLETED);
    }

    public static boolean hasRunicTotem(ItemStack stack) {
        return hasEnchantment(stack, ModRunicEnchantments.RUNIC_TOTEM);
    }

    public static boolean hasRunicPreservation(ItemStack stack) {
        return hasEnchantment(stack, ModRunicEnchantments.RUNIC_PRESERVATION);
    }

    private static boolean hasEnchantment(ItemStack stack, ResourceKey<Enchantment> enchantmentKey) {
        ItemEnchantments enchants = stack.get(DataComponents.ENCHANTMENTS);
        if (enchants == null) {
            return false;
        }

        for (Holder<Enchantment> holder : enchants.keySet()) {
            if (holder.is(enchantmentKey)) {
                return true;
            }
        }
        return false;
    }
}
