package dev.lumareth.aqueensdecor.Item;

import dev.lumareth.aqueensdecor.AQueensDecor;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
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

    public static void initialize() {
        AQueensDecor.LOGGER.info(AQueensDecor.MOD_ID + ": Registering Creative Mode Tabs");
    }
}
