package net.darkhax.gildedsherds.common.impl;

import net.darkhax.bookshelf.common.api.function.CachedSupplier;
import net.darkhax.bookshelf.common.api.service.Services;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class SherdType {

    private final String owner;
    private final String name;
    private final CachedSupplier<Item> sherdItem;

    public SherdType(String owner, String name) {
        this.owner = owner;
        this.name = name;
        this.sherdItem = CachedSupplier.cache(() -> new Item(new Item.Properties()));
    }

    public boolean canLoad() {
        return ResourceLocation.DEFAULT_NAMESPACE.equals(this.owner) || Services.PLATFORM.isModLoaded(this.owner);
    }

    public Item item() {
        return this.sherdItem.get();
    }

    public String name() {
        return this.name;
    }

    public String id() {
        return this.owner + "/" + this.name;
    }

    public String itemId() {
        return this.id() + "_pottery_sherd";
    }

    public String patternId() {
        return this.id() + "_pottery_pattern";
    }
}