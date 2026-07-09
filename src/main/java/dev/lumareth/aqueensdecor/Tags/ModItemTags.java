package dev.lumareth.aqueensdecor.Tags;

import dev.lumareth.aqueensdecor.AQueensDecor;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModItemTags {
    public static void initialize() {
        AQueensDecor.LOGGER.info(AQueensDecor.MOD_ID + ": Registering Item Tags");
    }

    public static final TagKey<Item> BRONZE = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AQueensDecor.MOD_ID, "bronze_ingot"));
    public static final TagKey<Item> STYGIAN_IRON = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AQueensDecor.MOD_ID, "stygian_iron_ingot"));
    public static final TagKey<Item> CELESTIAL_BRONZE = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AQueensDecor.MOD_ID, "celestial_bronze"));
    public static final TagKey<Item> IMPERIAL_GOLD = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AQueensDecor.MOD_ID, "imperial_gold"));
    public static final TagKey<Item> HARDENED_MOONLIGHT = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AQueensDecor.MOD_ID, "hardened_moonlight"));
}
