package dev.lumareth.aqueensdecor.Item;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import dev.lumareth.aqueensdecor.AQueensDecor;
import net.minecraft.world.item.ToolMaterial;

import java.util.function.Function;

public class ModItems {
    // Constants:
    public static final float battleaxeAttackSpeed = -3.5F;

    // Item Definitions:
        // Simple Items:
        public static final Item RAW_BRONZE = register("raw_bronze", Item::new, new Item.Properties());
        public static final Item BRONZE_INGOT = register("bronze_ingot", Item::new, new Item.Properties());
        public static final Item RAW_STYGIAN_IRON = register("raw_stygian_iron", Item::new, new Item.Properties());
        public static final Item STYGIAN_IRON_INGOT = register("stygian_iron_ingot", Item::new, new Item.Properties());
        public static final Item IMPERIAL_GOLD = register("imperial_gold", Item::new, new Item.Properties());
        public static final Item CELESTIAL_BRONZE = register("celestial_bronze", Item::new, new Item.Properties());
        public static final Item HARDENED_MOONLIGHT = register("hardened_moonlight", Item::new, new Item.Properties());

        // Advanced Items:
        // Modded Tools and Weapons:
        public static final Item BRONZE_SWORD = register("bronze_sword", Item::new, new Item.Properties().sword(ModToolMaterials.BRONZE_TOOL_MATERIAL, 3.0F, -2.1F));
        public static final Item BRONZE_AXE = register("bronze_axe", Item::new, new Item.Properties().axe(ModToolMaterials.BRONZE_TOOL_MATERIAL, 5.5F, -3.0F));
        public static final Item BRONZE_PICKAXE = register("bronze_pickaxe", Item::new, new Item.Properties().pickaxe(ModToolMaterials.BRONZE_TOOL_MATERIAL, 1.2F, -3.0F));
        public static final Item BRONZE_SHOVEL = register("bronze_shovel", Item::new, new Item.Properties().shovel(ModToolMaterials.BRONZE_TOOL_MATERIAL, 1.5F, -3.0F));
        public static final Item BRONZE_HOE = register("bronze_hoe", Item::new, new Item.Properties().hoe(ModToolMaterials.BRONZE_TOOL_MATERIAL, 0.0F, -3.0F));
        public static final Item BRONZE_BATTLEAXE = register("bronze_battleaxe", props -> new BattleaxeItem(ModToolMaterials.BRONZE_TOOL_MATERIAL, 7.0F, battleaxeAttackSpeed, props), new Item.Properties());
        public static final Item STYGIAN_IRON_SWORD = register("stygian_iron_sword", Item::new, new Item.Properties().sword(ModToolMaterials.STYGIAN_IRON_TOOL_MATERIAL, 6.0F, -2.0F));
        public static final Item STYGIAN_IRON_AXE = register("stygian_iron_axe", Item::new, new Item.Properties().axe(ModToolMaterials.STYGIAN_IRON_TOOL_MATERIAL, 8.0F, -3.0F));
        public static final Item STYGIAN_IRON_BATTLEAXE = register("stygian_iron_battleaxe", props -> new BattleaxeItem(ModToolMaterials.STYGIAN_IRON_TOOL_MATERIAL, 10.0F, battleaxeAttackSpeed, props), new Item.Properties());
        public static final Item CELESTIAL_BRONZE_SWORD = register("celestial_bronze_sword", Item::new, new Item.Properties().sword(ModToolMaterials.CELESTIAL_BRONZE_TOOL_MATERIAL, 6.0F, -2.0F));
        public static final Item CELESTIAL_BRONZE_AXE = register("celestial_bronze_axe", Item::new, new Item.Properties().axe(ModToolMaterials.CELESTIAL_BRONZE_TOOL_MATERIAL, 8.0F, -3.0F));
        public static final Item CELESTIAL_BRONZE_BATTLEAXE = register("celestial_bronze_battleaxe", props -> new BattleaxeItem(ModToolMaterials.CELESTIAL_BRONZE_TOOL_MATERIAL, 10.0F, battleaxeAttackSpeed, props), new Item.Properties());
        public static final Item IMPERIAL_GOLD_SWORD = register("imperial_gold_sword", Item::new, new Item.Properties().sword(ModToolMaterials.IMPERIAL_GOLD_TOOL_MATERIAL, 6.0F, -2.0F));
        public static final Item IMPERIAL_GOLD_AXE = register("imperial_gold_axe", Item::new, new Item.Properties().axe(ModToolMaterials.IMPERIAL_GOLD_TOOL_MATERIAL, 8.0F, -3.0F));
        public static final Item IMPERIAL_GOLD_BATTLEAXE = register("imperial_gold_battleaxe", props -> new BattleaxeItem(ModToolMaterials.IMPERIAL_GOLD_TOOL_MATERIAL, 10.0F, battleaxeAttackSpeed, props), new Item.Properties());
        public static final Item HARDENED_MOONLIGHT_SWORD = register("hardened_moonlight_sword", Item::new, new Item.Properties().sword(ModToolMaterials.HARDENED_MOONLIGHT_TOOL_MATERIAL, 6.0F, -2.0F));
        public static final Item HARDENED_MOONLIGHT_AXE = register("hardened_moonlight_axe", Item::new, new Item.Properties().axe(ModToolMaterials.HARDENED_MOONLIGHT_TOOL_MATERIAL, 8.0F, -3.0F));
        public static final Item HARDENED_MOONLIGHT_BOW = register("hardened_moonlight_bow", BowItem::new, (new Item.Properties().durability(3096).enchantable(1)));

        // Vanilla Modded Items:
        public static final Item STONE_BATTLEAXE = register("stone_battleaxe", props -> new BattleaxeItem(ToolMaterial.STONE, 4.0F, battleaxeAttackSpeed, props), new Item.Properties());
        public static final Item IRON_BATTLEAXE = register("iron_battleaxe", props -> new BattleaxeItem(ToolMaterial.IRON, 5.0F, battleaxeAttackSpeed, props), new Item.Properties());
        public static final Item DIAMOND_BATTLEAXE = register("diamond_battleaxe", props -> new BattleaxeItem(ToolMaterial.DIAMOND, 6.0F, battleaxeAttackSpeed, props), new Item.Properties());
        public static final Item NETHERITE_BATTLEAXE = register("netherite_battleaxe", props -> new BattleaxeItem(ToolMaterial.NETHERITE, 7.0F, battleaxeAttackSpeed, props), new Item.Properties());

    // Initializer:
    public static void initialize() {
        AQueensDecor.LOGGER.info(AQueensDecor.MOD_ID+": Registering Items");
    }
    // Item Factory:
    public static Item register(String name, Function<Item.Properties, Item> itemFactory, Item.Properties settings) {
        // Create the Item Key:
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AQueensDecor.MOD_ID, name));
        // Create the Item Instance:
        Item item = itemFactory.apply(settings.setId(itemKey));
        // Register the Item:
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);
        return item;
    }
}
