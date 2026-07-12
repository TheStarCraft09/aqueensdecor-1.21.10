package dev.lumareth.aqueensdecor.world.gen;

import dev.lumareth.aqueensdecor.AQueensDecor;

public class ModWorldGeneration {
    public static void generateWorldGen(){
        ModOreGeneration.GenerateOres();
    }
    public static void initialize() {
        AQueensDecor.LOGGER.info(AQueensDecor.MOD_ID + "Registering ModWorldGeneration");
    }
}
