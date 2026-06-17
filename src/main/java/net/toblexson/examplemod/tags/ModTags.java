package net.toblexson.examplemod.tags;

import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.toblexson.examplemod.ExampleMod;

public class ModTags
{
    public static class Items
    {
        public static TagKey<Item> VOID_METAL_ORES = createTag("void_metal_ores");

        private static TagKey<Item> createTag(String name)
        {
            return ItemTags.create(Identifier.fromNamespaceAndPath(ExampleMod.MOD_ID, name));
        }
    }

    public static class Blocks
    {
        public static TagKey<Block> VOID_METAL_ORES = createTag("void_metal_ores");

        private static TagKey<Block> createTag(String name)
        {
            return BlockTags.create(Identifier.fromNamespaceAndPath(ExampleMod.MOD_ID, name));
        }
    }
}
