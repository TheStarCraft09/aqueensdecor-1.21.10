package dev.lumareth.aqueensdecor.Block;

import dev.lumareth.aqueensdecor.AQueensDecor;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import java.util.function.Function;

public class ModBlocks {
    //Block Declarations:
    public static final Block BRONZE_ORE = registerBlock("bronze_ore",
            Block::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()
                    .strength(3.0F, 3.0F)
    );

    public static final Block DEEPSLATE_BRONZE_ORE = registerBlock("deepslate_bronze_ore",
            Block::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.DEEPSLATE)
                    .sound(SoundType.DEEPSLATE)
                    .requiresCorrectToolForDrops()
                    .strength(4.5F, 3.0F)
    );

    // Initializer:
    public static void initialize() {
        AQueensDecor.LOGGER.info(AQueensDecor.MOD_ID + ": Registering Blocks");
    }

    // Block Factory:
    private static <T extends Block> T registerBlock(String name, Function<BlockBehaviour.Properties, T> blockFactory, BlockBehaviour.Properties settings) {
        ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(AQueensDecor.MOD_ID, name));
        T block = blockFactory.apply(settings.setId(blockKey));
        Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
        registerBlockItem(name, block);
        return block;
    }

    private static Item registerBlockItem(String name, Block block) {
        ResourceKey<Item> blockItemKey = ResourceKey.create( Registries.ITEM, ResourceLocation.fromNamespaceAndPath(AQueensDecor.MOD_ID, name));
        BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(blockItemKey));
        return Registry.register(BuiltInRegistries.ITEM, blockItemKey, blockItem);
    }

}
