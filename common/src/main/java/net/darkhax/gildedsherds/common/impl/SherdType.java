package net.darkhax.gildedsherds.common.impl;

import net.darkhax.bookshelf.common.api.service.Services;
import net.minecraft.resources.Identifier;

public class SherdType {

    private final String owner;
    private final String name;

    public SherdType(String owner, String name) {
        this.owner = owner;
        this.name = name;
    }

    public boolean canLoad() {
        return Identifier.DEFAULT_NAMESPACE.equals(this.owner) || Services.PLATFORM.isModLoaded(this.owner);
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