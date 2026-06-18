package net.toblexson.minerology.dataproviders;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ItemTagsProvider;
import net.toblexson.minerology.Minerology;
import net.toblexson.minerology.tags.ModTags;

import java.util.concurrent.CompletableFuture;

import static net.toblexson.minerology.registers.ModBlocks.*;
import static net.toblexson.minerology.registers.ModItems.*;

public class ModItemTagProvider extends ItemTagsProvider
{
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup)
    {
        super(output, lookup, Minerology.MOD_ID);
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
                .addTag(ModTags.Items.VOID_METAL_ORES);

        /* MOD */
        tag(ModTags.Items.VOID_METAL_ORES)
                .add(VOID_METAL_DEEPSLATE_ORE.asItem())
                .add(VOID_METAL_ORE.asItem())
                .add(VOID_METAL_NETHER_ORE.asItem())
                .add(VOID_METAL_END_ORE.asItem());
    }
}
