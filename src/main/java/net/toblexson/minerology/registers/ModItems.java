package net.toblexson.minerology.registers;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.toblexson.minerology.Minerology;

public class ModItems
{
    public static final DeferredRegister.Items REGISTER = DeferredRegister.createItems(Minerology.MOD_ID);

    public static final DeferredItem<Item> RAW_VOID_METAL = REGISTER.registerSimpleItem("raw_void_metal");
    public static final DeferredItem<Item> VOID_METAL_NUGGETS = REGISTER.registerSimpleItem("void_metal_nugget");
    public static final DeferredItem<Item> VOID_METAL_INGOT = REGISTER.registerSimpleItem("void_metal_ingot");

    public static void register(IEventBus bus)
    {
        REGISTER.register(bus);
    }
}
