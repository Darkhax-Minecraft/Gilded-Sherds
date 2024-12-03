package net.darkhax.gildedsherds.neoforge.impl;

import net.darkhax.gildedsherds.common.impl.GildedSherds;
import net.neoforged.fml.common.Mod;

@Mod(GildedSherds.MOD_ID)
public class NeoForgeMod {

    public NeoForgeMod() {
        GildedSherds.init();
    }
}