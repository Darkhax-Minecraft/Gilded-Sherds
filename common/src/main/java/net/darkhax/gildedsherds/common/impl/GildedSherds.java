package net.darkhax.gildedsherds.common.impl;

import net.darkhax.gildedsherds.common.impl.config.Config;
import net.darkhax.pricklemc.common.api.config.ConfigManager;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GildedSherds {

    public static final String MOD_ID = "gildedsherds";
    public static final String MOD_NAME = "GildedSherds";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);
    public static final Config CONFIG = ConfigManager.load(MOD_ID, new Config());

    public static void init() {
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}