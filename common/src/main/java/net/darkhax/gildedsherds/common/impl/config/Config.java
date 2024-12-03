package net.darkhax.gildedsherds.common.impl.config;

import net.darkhax.pricklemc.common.api.annotations.Value;

public class Config {

    @Value(comment = "Allows players to craft gilded sherds using a crafting table if enabled.")
    public boolean can_craft = true;

    @Value(comment = "Allows players to remove gilding from a sherd by putting it in the furnace. The gold will be lost.")
    public boolean can_ungild = true;

    @Value(comment = "Allows players to recover the gold from a gilded sherd by putting it in a blast furnace. The sherd will be lost.")
    public boolean can_recycle = true;

    @Value(comment = "Enables the touch of gold advancement which is granted when the player collects any gilded sherd.")
    public boolean grant_advancement = true;
}