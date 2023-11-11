package net.darkhax.gildedsherds.mixin;

import net.darkhax.gildedsherds.GildedSherdsCommon;
import net.darkhax.gildedsherds.lib.CustomSherdItem;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.DecoratedPotPatterns;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DecoratedPotPatterns.class)
public class MixinDecoratedPotPatterns {

    @Inject(method = "getResourceKey", at = @At("TAIL"), cancellable = true)
    private static void getResourceKey(Item item, CallbackInfoReturnable<ResourceKey<String>> cbi) {

        if (item instanceof CustomSherdItem customSherd) {

            cbi.setReturnValue(customSherd.entry.sherdPattern);
        }
    }

    @Inject(method = "bootstrap", at = @At("TAIL"))
    private static void bootstrap(Registry<String> registry, CallbackInfoReturnable<String> cbi) {

        GildedSherdsCommon.registerPatterns((id, entry) -> Registry.register(registry, id, entry));
    }
}