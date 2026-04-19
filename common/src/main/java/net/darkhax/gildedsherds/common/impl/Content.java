package net.darkhax.gildedsherds.common.impl;

import com.mojang.serialization.MapCodec;
import net.darkhax.bookshelf.common.api.data.conditions.ILoadCondition;
import net.darkhax.bookshelf.common.api.function.CachedSupplier;
import net.darkhax.bookshelf.common.api.registry.ContentProvider;
import net.darkhax.bookshelf.common.api.registry.adapters.GenericRegistryAdapter;
import net.darkhax.bookshelf.common.impl.registry.adapter.ItemRegistryAdapter;
import net.darkhax.bookshelf.common.impl.registry.adapter.PotPatternAdapter;
import net.darkhax.gildedsherds.common.impl.config.ConfigProperty;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

import java.io.File;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Content implements ContentProvider {

    private static final String[] VANILLA_SHERDS = new String[]{"angler", "archer", "arms_up", "blade", "brewer", "burn", "danger", "explorer", "friend", "heart", "heartbreak", "howl", "miner", "mourner", "plenty", "prize", "sheaf", "shelter", "skull", "snort", "flow", "guster", "scrape"};
    private static final Supplier<List<SherdType>> SHERD_TYPES = CachedSupplier.cache(() -> {
        final List<SherdType> types = new LinkedList<>();
        for (String vanillaType : VANILLA_SHERDS) {
            types.add(new SherdType(Identifier.DEFAULT_NAMESPACE, vanillaType));
            File out = new File("out");
            out.mkdirs();
            try (FileWriter writer = new FileWriter(new File(out, vanillaType + "_pottery_sherd.json"))) {
                writer.write("""
                        {
                          "model": {
                            "type": "minecraft:model",
                            "model": "gildedsherds:item/minecraft/%s_pottery_sherd"
                          }
                        }""".formatted(vanillaType));
            }
            catch (Exception e) {

            }
        }
        return types;
    });

    @Override
    public String namespace() {
        return GildedSherds.MOD_ID;
    }

    @Override
    public void defineItems(ItemRegistryAdapter registry) {
        for (SherdType type : SHERD_TYPES.get()) {
            if (type.canLoad()) {
                registry.addSimple(type.itemId(), UnaryOperator.identity());
            }
        }
    }

    @Override
    public void definePotPatterns(PotPatternAdapter registry) {
        for (SherdType type : SHERD_TYPES.get()) {
            if (type.canLoad()) {
                registry.addWithItem(type.patternId(), BuiltInRegistries.ITEM.getValue(GildedSherds.id(type.itemId())));
            }
        }
    }

    @Override
    public void defineLoadConditions(GenericRegistryAdapter<MapCodec<? extends ILoadCondition>> registry) {
        registry.add("config", ConfigProperty.CODEC);
    }
}