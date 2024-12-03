package net.darkhax.gildedsherds.common.impl.config;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.darkhax.bookshelf.common.api.data.codecs.map.MapCodecs;
import net.darkhax.bookshelf.common.api.data.conditions.ConditionType;
import net.darkhax.bookshelf.common.api.data.conditions.ILoadCondition;
import net.darkhax.bookshelf.common.api.data.conditions.LoadConditions;
import net.darkhax.bookshelf.common.api.function.CachedSupplier;
import net.darkhax.gildedsherds.common.impl.GildedSherds;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ConfigProperty implements ILoadCondition {

    public static final ResourceLocation TYPE_ID = GildedSherds.id("config");
    public static final CachedSupplier<ConditionType> TYPE = CachedSupplier.cache(() -> LoadConditions.getType(TYPE_ID));
    public static final MapCodec<ConfigProperty> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(MapCodecs.STRING.get("property", ConfigProperty::getProperty)).apply(instance, ConfigProperty::new));

    private static final Supplier<Map<String, Boolean>> PROPERTIES = CachedSupplier.cache(() -> {
        final Map<String, Boolean> properties = new HashMap<>();
        properties.put("can_craft", GildedSherds.CONFIG.can_craft);
        properties.put("can_ungild", GildedSherds.CONFIG.can_ungild);
        properties.put("can_recycle", GildedSherds.CONFIG.can_recycle);
        properties.put("grant_advancement", GildedSherds.CONFIG.grant_advancement);
        return properties;
    });

    private final String property;

    private ConfigProperty(String property) {
        this.property = property;
    }

    public String getProperty() {
        return this.property;
    }

    @Override
    public boolean allowLoading() {
        return PROPERTIES.get().containsKey(this.property) && PROPERTIES.get().get(this.property);
    }

    @Override
    public ConditionType getType() {
        return TYPE.get();
    }
}