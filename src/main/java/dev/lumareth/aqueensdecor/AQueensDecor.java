package dev.lumareth.aqueensdecor;

import dev.lumareth.aqueensdecor.Block.ModBlocks;
import dev.lumareth.aqueensdecor.Enchantment.ModEnchantments;
import dev.lumareth.aqueensdecor.Item.ModCreativeModeTabs;
import dev.lumareth.aqueensdecor.Item.ModItems;
import dev.lumareth.aqueensdecor.Item.ModToolMaterials;
import dev.lumareth.aqueensdecor.Tags.ModBlockTags;
import dev.lumareth.aqueensdecor.Tags.ModItemTags;
import dev.lumareth.aqueensdecor.world.ModConfiguredFeatures;
import dev.lumareth.aqueensdecor.world.ModOrePlacement;
import dev.lumareth.aqueensdecor.world.ModPlacedFeatures;
import dev.lumareth.aqueensdecor.world.gen.ModOreGeneration;
import dev.lumareth.aqueensdecor.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.ResourceLocation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AQueensDecor implements ModInitializer {
	public static final String MOD_ID = "aqueensdecor";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.initialize();
		ModToolMaterials.initialize();
		ModBlockTags.initialize();
		ModItemTags.initialize();
		ModCreativeModeTabs.initialize();
		ModBlocks.initialize();
		ModOrePlacement.initialize();
		ModWorldGeneration.initialize();
		ModWorldGeneration.generateWorldGen();
		ModConfiguredFeatures.initialize();
		ModOreGeneration.initialize();
		ModPlacedFeatures.initialize();
		ModEnchantments.initialize();
		LOGGER.info(MOD_ID + " is loaded");
	}

	public static ResourceLocation id(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}
}
