package dev.lumareth.aqueensdecor.Item;

import dev.lumareth.aqueensdecor.AQueensDecor;
import dev.lumareth.aqueensdecor.Tags.ModItemTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ToolMaterial;

import javax.tools.Tool;

public class ModToolMaterials {
    public static void initialize() {
        AQueensDecor.LOGGER.info(AQueensDecor.MOD_ID + ": Registering Tool Materials");
    }

    public static final ToolMaterial BRONZE_TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 1800, 6.0F, 1.7F, 20, ModItemTags.BRONZE);
    public static final ToolMaterial STYGIAN_IRON_TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2496, 7.0F, 3.0F, 10, ModItemTags.STYGIAN_IRON);
    public static final ToolMaterial CELESTIAL_BRONZE_TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 3000, 7.0F, 3.5F, 15, ModItemTags.CELESTIAL_BRONZE);
    public static final ToolMaterial IMPERIAL_GOLD_TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_NETHERITE_TOOL,1896, 8.0F, 4.0F, 12, ModItemTags.IMPERIAL_GOLD);
    public static final ToolMaterial HARDENED_MOONLIGHT_TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 3096, 9.0F, 3.0F, 10, ModItemTags.HARDENED_MOONLIGHT);
}