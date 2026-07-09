package dev.lumareth.aqueensdecor.Tags;

import dev.lumareth.aqueensdecor.AQueensDecor;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModBlockTags {
    public static void initialize() {
        AQueensDecor.LOGGER.info(AQueensDecor.MOD_ID + ": Registering Block Tags");
    }

    public static final TagKey<Block> BATTLEAXE_MINEABLE = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(AQueensDecor.MOD_ID, "battleaxe_mineable"));
}