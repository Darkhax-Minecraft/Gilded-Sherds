package net.darkhax.gildedsherds;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.RegisterEvent;

@Mod(GildedSherdsCommon.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class GildedSherdsNeoforge {

    @SubscribeEvent
    public static void registerStuff(RegisterEvent event) {

        event.register(Registries.ITEM, helper -> GildedSherdsCommon.registerItems(helper::register));
        event.register(Registries.CREATIVE_MODE_TAB, helper -> helper.register(GildedSherdsCommon.TAB_ID, GildedSherdsCommon.createTab(CreativeModeTab.builder())));
    }
}