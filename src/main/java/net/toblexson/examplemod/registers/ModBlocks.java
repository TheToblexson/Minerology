package net.toblexson.examplemod.registers;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.toblexson.examplemod.ExampleMod;

import java.util.function.Function;

public class ModBlocks
{
    public static final DeferredRegister.Blocks REGISTER = DeferredRegister.createBlocks(ExampleMod.MOD_ID);

    public static final DeferredBlock<Block> VOID_METAL_ORE = block("void_metal_ore", BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE));
    public static final DeferredBlock<Block> VOID_METAL_DEEPSLATE_ORE = block("void_metal_deepslate_ore", BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE));
    public static final DeferredBlock<Block> VOID_METAL_NETHER_ORE = block("void_metal_nether_ore", BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE));
    public static final DeferredBlock<Block> VOID_METAL_END_ORE = block("void_metal_end_ore", BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE));

    public static final DeferredBlock<Block> RAW_VOID_METAL_BLOCK = block("raw_void_metal_block", BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK));
    public static final DeferredBlock<Block> VOID_METAL_BLOCK = block("void_metal_block", BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK));

    private static DeferredBlock<Block> block(String name, BlockBehaviour.Properties properties)
    {
        return registerBlockWithItem(name, properties, Block::new);
    }

    private static <T extends Block> DeferredBlock<T> registerBlockWithItem(String name, BlockBehaviour.Properties properties, Function<BlockBehaviour.Properties, T> function)
    {
        DeferredBlock<T> registeredBlock = REGISTER.registerBlock(name, function, () -> properties);
        registerBlockItem(name, registeredBlock);
        return registeredBlock;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block)
    {
        ModItems.REGISTER.registerSimpleBlockItem(name, block);
    }

    public static void register(IEventBus bus)
    {
        REGISTER.register(bus);
    }
}
