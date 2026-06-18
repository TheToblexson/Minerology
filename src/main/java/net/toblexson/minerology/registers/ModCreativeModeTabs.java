package net.toblexson.minerology.registers;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.toblexson.minerology.Minerology;

import java.util.function.Supplier;

public class ModCreativeModeTabs
{
    public static final DeferredRegister<CreativeModeTab> REGISTER = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Minerology.MOD_ID);

    public static final Supplier<CreativeModeTab> MOD_TAB = REGISTER.register("example_mod_tab", () ->
            CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModBlocks.VOID_METAL_ORE))
                    .title(Component.translatable("creativetab.examplemod.mod_tab"))
                    .displayItems((itemDisplayParameters, output) ->
                                  {
                                      ModBlocks.REGISTER.getEntries().stream().map(DeferredHolder::get).forEach(output::accept);
                                      ModItems.REGISTER.getEntries().stream().map(DeferredHolder::get).forEach(output::accept);
                                  })
                    .build());

    public static void register(IEventBus bus)
    {
        REGISTER.register(bus);
    }
}
