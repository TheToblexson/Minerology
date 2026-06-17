package net.toblexson.examplemod.dataproviders;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.toblexson.examplemod.registers.ModBlocks;
import net.toblexson.examplemod.registers.ModItems;

import java.util.Arrays;
import java.util.Set;

import static net.toblexson.examplemod.registers.ModBlocks.*;
import static net.toblexson.examplemod.registers.ModItems.*;

public class ModBlockLootTableProvider extends BlockLootSubProvider
{
    public ModBlockLootTableProvider(HolderLookup.Provider registries)
    {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate()
    {
        dropOre(RAW_VOID_METAL, VOID_METAL_ORE, VOID_METAL_DEEPSLATE_ORE, VOID_METAL_NETHER_ORE, VOID_METAL_END_ORE);

        dropSelf(RAW_VOID_METAL_BLOCK);
        dropSelf(VOID_METAL_BLOCK);
    }

    private void dropOre(DeferredItem<?> droppedItem, DeferredBlock<?>... blocks)
    {
        Arrays.stream(blocks).forEach(block -> createOreDrop(block.get(), droppedItem.get()));
    }

    private void dropSelf(DeferredBlock<?> block)
    {
        dropSelf(block.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks()
    {
        return ModBlocks.REGISTER.getEntries().stream().map(Holder::value)::iterator;
    }
}
