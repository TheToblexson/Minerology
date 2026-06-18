package net.toblexson.minerology.dataproviders;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.toblexson.minerology.Minerology;
import net.toblexson.minerology.registers.ModBlocks;
import net.toblexson.minerology.registers.ModItems;

public class ModModelProvider extends ModelProvider
{
    public ModModelProvider(PackOutput output)
    {
        super(output, Minerology.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels)
    {
        /* ITEMS */
        item(itemModels, ModItems.RAW_VOID_METAL);
        item(itemModels, ModItems.VOID_METAL_NUGGETS);
        item(itemModels, ModItems.VOID_METAL_INGOT);

        /* BLOCKS */
        block(blockModels, ModBlocks.VOID_METAL_ORE);
        block(blockModels, ModBlocks.VOID_METAL_DEEPSLATE_ORE);
        block(blockModels, ModBlocks.VOID_METAL_NETHER_ORE);
        block(blockModels, ModBlocks.VOID_METAL_END_ORE);
        block(blockModels, ModBlocks.RAW_VOID_METAL_BLOCK);
        block(blockModels, ModBlocks.VOID_METAL_BLOCK);
    }

    private static void block(BlockModelGenerators blockModels, DeferredBlock<Block> block)
    {
        blockModels.createTrivialCube(block.get());
    }

    private void item(ItemModelGenerators itemModels, DeferredItem<Item> item)
    {
        itemModels.generateFlatItem(item.get(), ModelTemplates.FLAT_ITEM);
    }
}
