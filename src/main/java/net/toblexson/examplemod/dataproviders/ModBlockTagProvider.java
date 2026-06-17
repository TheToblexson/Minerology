package net.toblexson.examplemod.dataproviders;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.toblexson.examplemod.ExampleMod;
import net.toblexson.examplemod.registers.ModBlocks;
import net.toblexson.examplemod.tags.ModTags;

import java.util.concurrent.CompletableFuture;

import static net.toblexson.examplemod.registers.ModBlocks.*;

public class ModBlockTagProvider extends BlockTagsProvider
{
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup)
    {
        super(output, lookup, ExampleMod.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider lookup)
    {
        /* MINING */
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(VOID_METAL_ORE.get())
                .add(VOID_METAL_DEEPSLATE_ORE.get())
                .add(VOID_METAL_NETHER_ORE.get())
                .add(VOID_METAL_END_ORE.get())
                .add(RAW_VOID_METAL_BLOCK.get())
                .add(VOID_METAL_BLOCK.get());

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(VOID_METAL_ORE.get())
                .add(VOID_METAL_DEEPSLATE_ORE.get())
                .add(VOID_METAL_NETHER_ORE.get())
                .add(VOID_METAL_END_ORE.get())
                .add(RAW_VOID_METAL_BLOCK.get())
                .add(VOID_METAL_BLOCK.get());

        /* VANILLA/FORGE */
        tag(Tags.Blocks.STORAGE_BLOCKS)
                .add(RAW_VOID_METAL_BLOCK.get())
                .add(VOID_METAL_BLOCK.get());

        tag(Tags.Blocks.ORES)
                .addTag(ModTags.Blocks.VOID_METAL_ORES);

        /* MOD */
        tag(ModTags.Blocks.VOID_METAL_ORES)
                .add(VOID_METAL_ORE.get())
                .add(VOID_METAL_DEEPSLATE_ORE.get())
                .add(VOID_METAL_NETHER_ORE.get())
                .add(VOID_METAL_END_ORE.get());
    }
}
