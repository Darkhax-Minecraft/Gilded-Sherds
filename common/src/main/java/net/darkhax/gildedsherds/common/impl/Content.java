package net.darkhax.gildedsherds.common.impl;

import com.mojang.serialization.MapCodec;
import net.darkhax.bookshelf.common.api.data.conditions.ILoadCondition;
import net.darkhax.bookshelf.common.api.function.CachedSupplier;
import net.darkhax.bookshelf.common.api.registry.ContentProvider;
import net.darkhax.bookshelf.common.api.registry.adapters.GameRegistryAdapter;
import net.darkhax.bookshelf.common.api.registry.adapters.GenericRegistryAdapter;
import net.darkhax.bookshelf.common.impl.registry.adapter.PotPatternAdapter;
import net.darkhax.gildedsherds.common.impl.config.ConfigProperty;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

public class Content implements ContentProvider {

    private static final String[] VANILLA_SHERDS = new String[]{"angler", "archer", "arms_up", "blade", "brewer", "burn", "danger", "explorer", "friend", "heart", "heartbreak", "howl", "miner", "mourner", "plenty", "prize", "sheaf", "shelter", "skull", "snort", "flow", "guster", "scrape"};
    private static final Supplier<List<SherdType>> SHERD_TYPES = CachedSupplier.cache(() -> {
        final List<SherdType> types = new LinkedList<>();
        for (String vanillaType : VANILLA_SHERDS) {
            types.add(new SherdType(ResourceLocation.DEFAULT_NAMESPACE, vanillaType));
        }
        return types;
    });

    @Override
    public String namespace() {
        return GildedSherds.MOD_ID;
    }

    @Override
    public void defineItems(GameRegistryAdapter<Item> registry) {
        for (SherdType type : SHERD_TYPES.get()) {
            if (type.canLoad()) {
                registry.add(type.itemId(), type.item());
            }
        }
    }

    @Override
    public void definePotPatterns(PotPatternAdapter registry) {
        for (SherdType type : SHERD_TYPES.get()) {
            if (type.canLoad()) {
                registry.addWithItem(type.patternId(), type.item());
            }
        }
    }

    @Override
    public void defineLoadConditions(GenericRegistryAdapter<MapCodec<? extends ILoadCondition>> registry) {
        registry.add("config", ConfigProperty.CODEC);
    }
}