package dev.lumareth.aqueensdecor.world;

import dev.lumareth.aqueensdecor.AQueensDecor;
import dev.lumareth.aqueensdecor.Block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    public static void initialize() {
        AQueensDecor.LOGGER.info(AQueensDecor.MOD_ID + "Registering ModConfiguredFeatures");
    }
    public static final ResourceKey<ConfiguredFeature<?, ?>> BRONZE_ORE_KEY = registerKey("bronze_ore");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplacables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplacables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> overworldBronzeOres =
                List.of(OreConfiguration.target(stoneReplacables, ModBlocks.BRONZE_ORE.defaultBlockState()),
                        OreConfiguration.target(deepslateReplacables, ModBlocks.DEEPSLATE_BRONZE_ORE.defaultBlockState())
                );

        register(context, BRONZE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldBronzeOres, 9));

    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(
                Registries.CONFIGURED_FEATURE,
                ResourceLocation.fromNamespaceAndPath(AQueensDecor.MOD_ID, name)
        );
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(
            BootstrapContext<ConfiguredFeature<?, ?>> context,
            ResourceKey<ConfiguredFeature<?, ?>> key,
            F feature,
            FC configuration
    ) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}