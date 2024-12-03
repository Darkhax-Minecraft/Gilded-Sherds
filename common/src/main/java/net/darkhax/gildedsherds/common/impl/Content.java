package net.darkhax.gildedsherds.common.impl;

import com.mojang.serialization.MapCodec;
import net.darkhax.bookshelf.common.api.data.conditions.ILoadCondition;
import net.darkhax.bookshelf.common.api.function.CachedSupplier;
import net.darkhax.bookshelf.common.api.registry.IContentProvider;
import net.darkhax.bookshelf.common.api.registry.register.Register;
import net.darkhax.bookshelf.common.api.registry.register.RegisterPotPatterns;
import net.darkhax.gildedsherds.common.impl.config.ConfigProperty;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

public class Content implements IContentProvider {

    private static final String[] VANILLA_SHERDS = new String[]{"angler", "archer", "arms_up", "blade", "brewer", "burn", "danger", "explorer", "friend", "heart", "heartbreak", "howl", "miner", "mourner", "plenty", "prize", "sheaf", "shelter", "skull", "snort", "flow", "guster", "scrape"};
    private static final Supplier<List<SherdType>> SHERD_TYPES = CachedSupplier.cache(() -> {
        final List<SherdType> types = new LinkedList<>();
        for (String vanillaType : VANILLA_SHERDS) {
            types.add(new SherdType(ResourceLocation.DEFAULT_NAMESPACE, vanillaType));
        }
        return types;
    });

    @Override
    public String contentNamespace() {
        return GildedSherds.MOD_ID;
    }

    @Override
    public void registerItems(Register<Item> registry) {
        for (SherdType type : SHERD_TYPES.get()) {
            if (type.canLoad()) {
                registry.add(type.itemId(), type.item());
            }
        }
    }

    @Override
    public void registerPotPatterns(RegisterPotPatterns registry) {
        for (SherdType type : SHERD_TYPES.get()) {
            if (type.canLoad()) {
                registry.add(type.item(), type.patternId());
            }
        }
    }

    @Override
    public void registerLoadConditions(Register<MapCodec<? extends ILoadCondition>> registry) {
        registry.add("config", ConfigProperty.CODEC);
    }
}