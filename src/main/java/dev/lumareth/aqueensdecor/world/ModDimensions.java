package dev.lumareth.aqueensdecor.world;

import dev.lumareth.aqueensdecor.AQueensDecor;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;


public class ModDimensions {
    public static void initialize(){
        AQueensDecor.LOGGER.info(AQueensDecor.MOD_ID + ": Initializing Dimensions");
    }

    public static final ResourceKey<Level> UNDERWORLD_KEY = ResourceKey.create(
            Registries.DIMENSION,
            ResourceLocation.fromNamespaceAndPath(AQueensDecor.MOD_ID, "underworld")
    );
}
