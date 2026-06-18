package net.toblexson.minerology.worldgen;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.toblexson.minerology.Minerology;

public class ModBiomeModifiers
{
    public static final ResourceKey<BiomeModifier> VOID_METAL_ORE = registerKey("void_metal_ore");
    public static final ResourceKey<BiomeModifier> VOID_METAL_NETHER_ORE = registerKey("void_metal_nether_ore");
    public static final ResourceKey<BiomeModifier> VOID_METAL_END_ORE = registerKey("void_metal_end_ore");

    public static void bootstrap(BootstrapContext<BiomeModifier> context)
    {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(VOID_METAL_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.VOID_METAL_ORE),
                                 placedFeatures.getOrThrow(ModPlacedFeatures.VOID_METAL_DEEPSLATE_ORE)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(VOID_METAL_NETHER_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_NETHER),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.VOID_METAL_NETHER_ORE)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(VOID_METAL_END_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_END),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.VOID_METAL_END_ORE_SMALL),
                                 placedFeatures.getOrThrow(ModPlacedFeatures.VOID_METAL_END_ORE_LARGE)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name)
    {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, Identifier.fromNamespaceAndPath(Minerology.MOD_ID, name));
    }
}
