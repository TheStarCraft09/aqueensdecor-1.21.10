package dev.lumareth.aqueensdecor;

import dev.lumareth.aqueensdecor.Block.ModBlocks;
import dev.lumareth.aqueensdecor.Enchantment.ModEnchantments;
import dev.lumareth.aqueensdecor.Enchantment.ModRunicEnchantments;
import dev.lumareth.aqueensdecor.Item.ModCreativeModeTabs;
import dev.lumareth.aqueensdecor.Item.ModItems;
import dev.lumareth.aqueensdecor.Item.ModToolMaterials;
import dev.lumareth.aqueensdecor.Tags.ModBlockTags;
import dev.lumareth.aqueensdecor.Tags.ModItemTags;
import dev.lumareth.aqueensdecor.Util.ModDataComponents;
import dev.lumareth.aqueensdecor.Util.RunicUtils;
import dev.lumareth.aqueensdecor.world.ModConfiguredFeatures;
import dev.lumareth.aqueensdecor.world.ModDimensions;
import dev.lumareth.aqueensdecor.world.ModOrePlacement;
import dev.lumareth.aqueensdecor.world.ModPlacedFeatures;
import dev.lumareth.aqueensdecor.world.gen.ModOreGeneration;
import dev.lumareth.aqueensdecor.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.resources.ResourceLocation;

import net.minecraft.world.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AQueensDecor implements ModInitializer {
	public static final String MOD_ID = "aqueensdecor";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, blockEntity) -> {
			ItemStack held = player.getMainHandItem();
			return !RunicUtils.isDepleted(held);
		});
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
		ModDimensions.initialize();
		ModDataComponents.initialize();
		ModRunicEnchantments.initialize();
		LOGGER.info(MOD_ID + " is loaded");
	}

	public static ResourceLocation id(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}
}
