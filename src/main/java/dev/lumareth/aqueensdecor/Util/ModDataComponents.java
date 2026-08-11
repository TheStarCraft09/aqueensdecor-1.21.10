package dev.lumareth.aqueensdecor.Util;

import dev.lumareth.aqueensdecor.AQueensDecor;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Unit;

public class ModDataComponents {
    public static final DataComponentType<Unit> RUNIC_DEPLETED = Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, ResourceLocation.fromNamespaceAndPath(AQueensDecor.MOD_ID, "runic_depleted"), DataComponentType.<Unit>builder().persistent(Unit.CODEC).build());

    public static void initialize() {
        AQueensDecor.LOGGER.info(AQueensDecor.MOD_ID + ": Registering data components");
    }
}
