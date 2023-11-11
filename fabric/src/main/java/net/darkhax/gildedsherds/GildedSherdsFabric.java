package net.darkhax.gildedsherds;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

public class GildedSherdsFabric implements ModInitializer {

    @Override
    public void onInitialize() {

        GildedSherdsCommon.registerItems((id, item) -> Registry.register(BuiltInRegistries.ITEM, id, item));
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, GildedSherdsCommon.TAB_ID, GildedSherdsCommon.createTab(FabricItemGroup.builder()));
    }
}