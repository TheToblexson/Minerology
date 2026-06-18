package net.toblexson.minerology.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.toblexson.minerology.Minerology;

import java.util.List;

public class ModPlacedFeatures
{
    public static final HeightRangePlacement OVERWORLD_RANGE = HeightRangePlacement.uniform(VerticalAnchor.absolute(5), VerticalAnchor.absolute(-5));
    public static final HeightRangePlacement DEEPSLATE_RANGE = HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(-60));
    public static final HeightRangePlacement NETHER_RANGE = (HeightRangePlacement) PlacementUtils.FULL_RANGE;
    public static final HeightRangePlacement END_RANGE = (HeightRangePlacement) PlacementUtils.FULL_RANGE;

    public static final List<PlacementModifier> VOID_METAL_PLACEMENT = ModOrePlacement.rareOrePlacement(1, OVERWORLD_RANGE);
    public static final List<PlacementModifier> VOID_METAL_DEEPSLATE_PLACEMENT = ModOrePlacement.rareOrePlacement(1, DEEPSLATE_RANGE);
    public static final List<PlacementModifier> VOID_METAL_NETHER_PLACEMENT = ModOrePlacement.rareOrePlacement(3, NETHER_RANGE);
    public static final List<PlacementModifier> VOID_METAL_END_PLACEMENT_SMALL = ModOrePlacement.commonOrePlacement(50, END_RANGE);
    public static final List<PlacementModifier> VOID_METAL_END_PLACEMENT_LARGE = ModOrePlacement.commonOrePlacement(10, END_RANGE);

    public static final ResourceKey<PlacedFeature> VOID_METAL_ORE = registerKey("void_metal_ore");
    public static final ResourceKey<PlacedFeature> VOID_METAL_DEEPSLATE_ORE = registerKey("void_metal_deepslate_ore");
    public static final ResourceKey<PlacedFeature> VOID_METAL_NETHER_ORE = registerKey("void_metal_nether_ore");
    public static final ResourceKey<PlacedFeature> VOID_METAL_END_ORE_SMALL = registerKey("void_metal_end_ore_small");
    public static final ResourceKey<PlacedFeature> VOID_METAL_END_ORE_LARGE = registerKey("void_metal_end_ore_large");

    public static void bootstrap(BootstrapContext<PlacedFeature> context)
    {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, VOID_METAL_ORE, configuredFeatures.getOrThrow(ModConfiguredFeatures.VOID_METAL_ORE), VOID_METAL_PLACEMENT);
        register(context, VOID_METAL_DEEPSLATE_ORE, configuredFeatures.getOrThrow(ModConfiguredFeatures.VOID_METAL_DEEPSLATE_ORE), VOID_METAL_DEEPSLATE_PLACEMENT);

        register(context, VOID_METAL_NETHER_ORE, configuredFeatures.getOrThrow(ModConfiguredFeatures.VOID_METAL_NETHER_ORE), VOID_METAL_NETHER_PLACEMENT);

        register(context, VOID_METAL_END_ORE_SMALL, configuredFeatures.getOrThrow(ModConfiguredFeatures.VOID_METAL_END_ORE_SMALL), VOID_METAL_END_PLACEMENT_SMALL);
        register(context, VOID_METAL_END_ORE_LARGE, configuredFeatures.getOrThrow(ModConfiguredFeatures.VOID_METAL_END_ORE_LARGE), VOID_METAL_END_PLACEMENT_LARGE);
    }

    public static ResourceKey<PlacedFeature> registerKey(String name)
    {
        return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(Minerology.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?,?>> configuration, List<PlacementModifier> modifiers)
    {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
