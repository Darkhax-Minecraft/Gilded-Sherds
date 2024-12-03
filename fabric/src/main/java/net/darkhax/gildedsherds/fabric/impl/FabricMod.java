package net.darkhax.gildedsherds.fabric.impl;

import net.darkhax.gildedsherds.common.impl.GildedSherds;
import net.fabricmc.api.ModInitializer;

public class FabricMod implements ModInitializer {

    @Override
    public void onInitialize() {
        GildedSherds.init();
    }
}