package net.darkhax.gildedsherds;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;

@Mod(GildedSherdsCommon.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class GildedSherdsForge {

    @SubscribeEvent
    public static void registerStuff(RegisterEvent event) {

        event.register(Registries.ITEM, helper -> GildedSherdsCommon.registerItems((id, item) -> helper.register(id, item)));
        event.register(Registries.CREATIVE_MODE_TAB, helper -> helper.register(GildedSherdsCommon.TAB_ID, GildedSherdsCommon.createTab(CreativeModeTab.builder())));
    }
}