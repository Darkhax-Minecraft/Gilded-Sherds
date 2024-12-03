package net.darkhax.gildedsherds.forge.impl;

import net.darkhax.gildedsherds.common.impl.GildedSherds;
import net.minecraftforge.fml.common.Mod;

@Mod(GildedSherds.MOD_ID)
public class ForgeMod {

    public ForgeMod() {
        GildedSherds.init();
    }
}