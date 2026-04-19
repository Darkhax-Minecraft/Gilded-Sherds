package net.darkhax.gildedsherds.fabric;

import net.darkhax.gildedsherds.common.impl.GildedSherds;
import net.fabricmc.api.ModInitializer;

public class GildedSherdsFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        GildedSherds.init();
    }
}