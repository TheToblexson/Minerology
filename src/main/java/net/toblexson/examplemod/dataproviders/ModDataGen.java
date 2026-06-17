package net.toblexson.examplemod.dataproviders;

import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.toblexson.examplemod.ExampleMod;

import java.util.Collections;
import java.util.List;

@EventBusSubscriber(modid = ExampleMod.MOD_ID)
public class ModDataGen
{
    @SubscribeEvent
    public static void gatherClientData(GatherDataEvent.Client event)
    {
        var generator = event.getGenerator();
        var output = generator.getPackOutput();
        var lookup = event.getLookupProvider();

        generator.addProvider(true, new ModModelProvider(output));
        generator.addProvider(true, new ModBlockTagProvider(output, lookup));
        generator.addProvider(true, new ModItemTagProvider(output, lookup));
        generator.addProvider(true, new LootTableProvider(output, Collections.emptySet(), List.of(
                new LootTableProvider.SubProviderEntry(ModBlockLootTableProvider::new, LootContextParamSets.BLOCK)), lookup));
        generator.addProvider(true, new ModRecipeProvider.Runner(output, lookup));
        generator.addProvider(true, new ModDatapackProvider(output, lookup));
    }
}
