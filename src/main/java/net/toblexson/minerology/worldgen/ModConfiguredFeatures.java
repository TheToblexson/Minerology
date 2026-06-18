package net.toblexson.minerology.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.neoforged.neoforge.common.Tags;
import net.toblexson.minerology.Minerology;
import net.toblexson.minerology.registers.ModBlocks;

public class ModConfiguredFeatures
{
    public static final ResourceKey<ConfiguredFeature<?,?>> VOID_METAL_ORE = registerKey("void_metal_ore");
    public static final ResourceKey<ConfiguredFeature<?,?>> VOID_METAL_DEEPSLATE_ORE = registerKey("void_metal_deepslate_ore");
    public static final ResourceKey<ConfiguredFeature<?,?>> VOID_METAL_NETHER_ORE = registerKey("void_metal_nether_ore");
    public static final ResourceKey<ConfiguredFeature<?,?>> VOID_METAL_END_ORE_SMALL = registerKey("void_metal_end_ore_small");
    public static final ResourceKey<ConfiguredFeature<?,?>> VOID_METAL_END_ORE_LARGE = registerKey("void_metal_end_ore_large");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?,?>> context)
    {
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest netherStoneReplaceables = new TagMatchTest(Tags.Blocks.NETHERRACKS);
        RuleTest endStoneReplaceables = new TagMatchTest(Tags.Blocks.END_STONES);

        /* OVERWORLD */
        register(context, VOID_METAL_ORE, Feature.ORE, new OreConfiguration(
                stoneReplaceables, ModBlocks.VOID_METAL_ORE.get().defaultBlockState(), 1));
        register(context, VOID_METAL_DEEPSLATE_ORE, Feature.ORE, new OreConfiguration(
                stoneReplaceables, ModBlocks.VOID_METAL_DEEPSLATE_ORE.get().defaultBlockState(), 3));

        /* NETHER */
        register(context, VOID_METAL_NETHER_ORE, Feature.ORE, new OreConfiguration(
                netherStoneReplaceables, ModBlocks.VOID_METAL_NETHER_ORE.get().defaultBlockState(), 4));

        /* END */
        register(context, VOID_METAL_END_ORE_SMALL, Feature.ORE, new OreConfiguration(
                endStoneReplaceables, ModBlocks.VOID_METAL_END_ORE.get().defaultBlockState(), 6));
        register(context, VOID_METAL_END_ORE_LARGE, Feature.ORE, new OreConfiguration(
                endStoneReplaceables, ModBlocks.VOID_METAL_END_ORE.get().defaultBlockState(), 12));
    }

    public static ResourceKey<ConfiguredFeature<?,?>> registerKey(String name)
    {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(Minerology.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?,?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?,?>> key, F feature, FC configuration)
    {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
