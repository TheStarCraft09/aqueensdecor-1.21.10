package dev.lumareth.aqueensdecor.Enchantment;

import dev.lumareth.aqueensdecor.AQueensDecor;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;

public class ModRunicEnchantments {
    public static final ResourceKey<Enchantment> RUNIC_PRESERVATION = ResourceKey.create(
            Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(AQueensDecor.MOD_ID, "runic/runic_preservation"));
    public static final ResourceKey<Enchantment> RUNIC_TOTEM = ResourceKey.create(
            Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(AQueensDecor.MOD_ID, "runic/runic_totem"));

    public static void initialize() {
        AQueensDecor.LOGGER.info(AQueensDecor.MOD_ID + "Loading Runic Enchantment Resource Keys");
    }
}
