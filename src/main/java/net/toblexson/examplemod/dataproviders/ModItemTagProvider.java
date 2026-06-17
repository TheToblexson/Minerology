package net.toblexson.examplemod.dataproviders;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ItemTagsProvider;
import net.toblexson.examplemod.ExampleMod;
import net.toblexson.examplemod.registers.ModItems;
import net.toblexson.examplemod.tags.ModTags;

import java.util.concurrent.CompletableFuture;

import static net.toblexson.examplemod.registers.ModBlocks.*;
import static net.toblexson.examplemod.registers.ModItems.*;

public class ModItemTagProvider extends ItemTagsProvider
{
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup)
    {
        super(output, lookup, ExampleMod.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider lookup)
    {
        /* VANILLA/FORGE */
        tag(Tags.Items.RAW_MATERIALS)
                .add(RAW_VOID_METAL.get());

        tag(Tags.Items.INGOTS)
                .add(VOID_METAL_INGOT.get());

        tag(Tags.Items.NUGGETS)
                .add(VOID_METAL_NUGGETS.get());

        tag(Tags.Items.STORAGE_BLOCKS)
                .add(RAW_VOID_METAL_BLOCK.asItem())
                .add(VOID_METAL_BLOCK.asItem());

        tag(Tags.Items.ORES)
                .add(VOID_METAL_ORE.asItem())
                .add(VOID_METAL_DEEPSLATE_ORE.asItem())
                .add(VOID_METAL_NETHER_ORE.asItem())
                .add(VOID_METAL_END_ORE.asItem());

        /* MOD */
        tag(ModTags.Items.VOID_METAL_ORES)
                .add(VOID_METAL_DEEPSLATE_ORE.asItem())
                .add(VOID_METAL_ORE.asItem())
                .add(VOID_METAL_NETHER_ORE.asItem())
                .add(VOID_METAL_END_ORE.asItem());
    }
}
