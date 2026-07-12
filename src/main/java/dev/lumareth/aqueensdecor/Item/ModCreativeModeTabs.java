package dev.lumareth.aqueensdecor.Item;

import dev.lumareth.aqueensdecor.AQueensDecor;
import dev.lumareth.aqueensdecor.Block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTabs {
    public static final CreativeModeTab A_QUEENS_DECOR_COMBAT = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            ResourceLocation.fromNamespaceAndPath(AQueensDecor.MOD_ID, "aqueensdecor_combat"),
            FabricItemGroup.builder().title(Component.translatable("itemGroups.aqueensdecor_combat"))
            .icon(() -> new ItemStack(ModItems.BRONZE_SWORD))
            .displayItems((context, output) -> {
                        output.accept(ModItems.BRONZE_SWORD);
                        output.accept(ModItems.BRONZE_BATTLEAXE);
                        output.accept(ModItems.BRONZE_AXE);
                        output.accept(ModItems.STYGIAN_IRON_SWORD);
                        output.accept(ModItems.STYGIAN_IRON_AXE);
                        output.accept(ModItems.STONE_BATTLEAXE);
                        output.accept(ModItems.IRON_BATTLEAXE);
                        output.accept(ModItems.DIAMOND_BATTLEAXE);
                        output.accept(ModItems.NETHERITE_BATTLEAXE);
                        output.accept(ModItems.STYGIAN_IRON_BATTLEAXE);
                        output.accept(ModItems.CELESTIAL_BRONZE_SWORD);
                        output.accept(ModItems.CELESTIAL_BRONZE_AXE);
                        output.accept(ModItems.CELESTIAL_BRONZE_BATTLEAXE);
                        output.accept(ModItems.IMPERIAL_GOLD_SWORD);
                        output.accept(ModItems.IMPERIAL_GOLD_AXE);
                        output.accept(ModItems.IMPERIAL_GOLD_BATTLEAXE);
                        output.accept(ModItems.HARDENED_MOONLIGHT_SWORD);
                        output.accept(ModItems.HARDENED_MOONLIGHT_AXE);
                        output.accept(ModItems.HARDENED_MOONLIGHT_BOW);
                    }
            ).build());
    public static final CreativeModeTab A_QUEENS_DECOR_TOOLS = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            ResourceLocation.fromNamespaceAndPath(AQueensDecor.MOD_ID, "aqueensdecor_tools"),
            FabricItemGroup.builder().title(Component.translatable("itemGroups.aqueensdecor_tools"))
            .icon(() -> new ItemStack(ModItems.BRONZE_PICKAXE))
            .displayItems((context, output) -> {
                        output.accept(ModItems.BRONZE_PICKAXE);
                        output.accept(ModItems.BRONZE_SHOVEL);
                        output.accept(ModItems.BRONZE_HOE);
                        output.accept(ModItems.BRONZE_AXE);
                        output.accept(ModItems.STYGIAN_IRON_AXE);
                        output.accept(ModItems.CELESTIAL_BRONZE_AXE);
                        output.accept(ModItems.IMPERIAL_GOLD_AXE);
                        output.accept(ModItems.HARDENED_MOONLIGHT_AXE);
                    }
            ).build());
    public static final CreativeModeTab A_QUEENS_DECOR_ITEMS = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            ResourceLocation.fromNamespaceAndPath(AQueensDecor.MOD_ID, "aqueensdecor_items"),
            FabricItemGroup.builder().title(Component.translatable("itemGroups.aqueensdecor_items"))
            .icon(() -> new ItemStack(ModItems.BRONZE_INGOT))
            .displayItems((context, output) -> {
                        output.accept(ModItems.BRONZE_INGOT);
                        output.accept(ModItems.RAW_BRONZE);
                        output.accept(ModItems.STYGIAN_IRON_INGOT);
                        output.accept(ModItems.RAW_STYGIAN_IRON);
                        output.accept(ModItems.IMPERIAL_GOLD);
                        output.accept(ModItems.CELESTIAL_BRONZE);
                        output.accept(ModItems.HARDENED_MOONLIGHT);
                        output.accept(ModItems.HARDENED_MOONLIGHT_ROD);
                    }
            ).build());
    public static final CreativeModeTab A_QUEENS_DECOR_BLOCKS = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            ResourceLocation.fromNamespaceAndPath(AQueensDecor.MOD_ID, "aqueensdecor_blocks"),
            FabricItemGroup.builder().title(Component.translatable("itemGroups.aqueensdecor_blocks"))
            .icon(() -> new ItemStack(ModBlocks.BRONZE_ORE))
            .displayItems((context, output) -> {
                        output.accept(ModBlocks.BRONZE_ORE);
                        output.accept(ModBlocks.DEEPSLATE_BRONZE_ORE);
                    }
            ).build());

    public static void initialize() {
        AQueensDecor.LOGGER.info(AQueensDecor.MOD_ID + ": Registering Creative Mode Tabs");
    }
}
