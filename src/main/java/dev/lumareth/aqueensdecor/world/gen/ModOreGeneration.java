package dev.lumareth.aqueensdecor.world.gen;

import dev.lumareth.aqueensdecor.AQueensDecor;
import dev.lumareth.aqueensdecor.world.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModOreGeneration {
    public static void initialize() {
        AQueensDecor.LOGGER.info(AQueensDecor.MOD_ID + "Registering ModOreGeneration");
    }
    public static void GenerateOres(){
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.BRONZE_ORE_PLACE_KEY);
    }
}
