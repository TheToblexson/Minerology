package net.toblexson.examplemod.dataproviders;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.DeferredItem;
import net.toblexson.examplemod.ExampleMod;
import net.toblexson.examplemod.registers.ModBlocks;
import net.toblexson.examplemod.registers.ModItems;
import net.toblexson.examplemod.tags.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider
{
    public ModRecipeProvider(HolderLookup.Provider registries, RecipeOutput output)
    {
        super(registries, output);
    }

    @Override
    protected void buildRecipes()
    {
        /* CRAFTING */
        packing(ModItems.VOID_METAL_INGOT, ModItems.VOID_METAL_NUGGETS);
        packing(ModBlocks.RAW_VOID_METAL_BLOCK, ModItems.RAW_VOID_METAL);
        packing(ModBlocks.VOID_METAL_BLOCK, ModItems.VOID_METAL_INGOT);

        /* SMELTING */
        smeltingAndBlasting(ModItems.RAW_VOID_METAL, ModItems.VOID_METAL_INGOT, 1.0f);
        smeltingAndBlasting(ModTags.Items.VOID_METAL_ORES, ModItems.RAW_VOID_METAL, 1.0f);
    }

    private void smeltingAndBlasting(TagKey<Item> input, DeferredItem<Item> result, float experience)
    {
        String hasName = "has_" + input;
        String inName = input.location().getPath();
        Ingredient ingredient = tag(input);
        Criterion<?> criterion = has(input);

        smeltingAndBlasting(ingredient, inName, result, experience, hasName, criterion);
    }

    private void smeltingAndBlasting(ItemLike input, ItemLike result, float experience)
    {
        String hasName = getHasName(input);
        String inName = getItemName(input);
        Ingredient ingredient = Ingredient.of(input);
        Criterion<?> criterion = has(input);

        smeltingAndBlasting(ingredient, inName, result, experience, hasName, criterion);
    }

    private void smeltingAndBlasting(Ingredient ingredient, String ingredientName, ItemLike result, float experience, String criterionName, Criterion<?> criterion)
    {
        SimpleCookingRecipeBuilder.smelting(ingredient, RecipeCategory.MISC, CookingBookCategory.MISC, result, experience, 200)
                .group(getSimpleRecipeName(result))
                .unlockedBy(criterionName, criterion)
                .save(output, ExampleMod.MOD_ID + ":" + getItemName(result) + "_from_smelting_" + ingredientName);
        SimpleCookingRecipeBuilder.blasting(ingredient, RecipeCategory.MISC, CookingBookCategory.MISC, result, experience, 200)
                .group(getSimpleRecipeName(result))
                .unlockedBy(criterionName, criterion)
                .save(output, ExampleMod.MOD_ID + ":" + getItemName(result) + "_from_blasting_" + ingredientName);
    }

    private <T extends ItemLike> void packing(ItemLike packed, ItemLike unpacked)
    {
        shaped(RecipeCategory.BUILDING_BLOCKS, packed)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', unpacked)
                .unlockedBy(getHasName(unpacked), has(unpacked))
                .group(getSimpleRecipeName(packed))
                .save(output, ExampleMod.MOD_ID + ":" + getConversionRecipeName(packed, unpacked));
        shapeless(RecipeCategory.MISC, unpacked, 9)
                .requires(packed)
                .unlockedBy(getHasName(packed), has(packed))
                .group(getSimpleRecipeName(unpacked))
                .save(output, ExampleMod.MOD_ID + ":" + getConversionRecipeName(unpacked, packed));
    }

    public static class Runner extends RecipeProvider.Runner
    {

        protected Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> registries)
        {
            super(output, registries);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output)
        {
            return new ModRecipeProvider(registries, output);
        }

        @Override
        public String getName()
        {
            return "Mod Recipes";
        }
    }
}
