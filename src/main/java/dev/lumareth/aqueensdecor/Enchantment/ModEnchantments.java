package dev.lumareth.aqueensdecor.Enchantment;

import dev.lumareth.aqueensdecor.AQueensDecor;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;

public class ModEnchantments {
    public static final ResourceKey<Enchantment> STYX_BLESSING = ResourceKey.create(
            Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(AQueensDecor.MOD_ID, "styx_blessing"));

    public static void initialize() {
        AQueensDecor.LOGGER.info(AQueensDecor.MOD_ID + "Loading Enchantment Resource Keys");
    }
}
