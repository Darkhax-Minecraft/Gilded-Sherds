package net.darkhax.gildedsherds;

import net.darkhax.gildedsherds.lib.SherdEntry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import java.util.Arrays;
import java.util.function.BiConsumer;

public class GildedSherdsCommon {

    public static final String MOD_ID = "gildedsherds";
    public static final ResourceLocation TAB_ID = location("tab");

    public static final String[] VANILLA_SHERDS = new String[]{"angler", "archer", "arms_up", "blade", "brewer", "burn", "danger", "explorer", "friend", "heart", "heartbreak", "howl", "miner", "mourner", "plenty", "prize", "sheaf", "shelter", "skull", "snort"};

    static {
        Arrays.stream(VANILLA_SHERDS).forEach(SherdEntry::of);
    }

    public static ResourceLocation location(String path) {

        return new ResourceLocation(MOD_ID, path);
    }

    public static void registerItems(BiConsumer<ResourceLocation, Item> registry) {

        SherdEntry.SHERDS.values().forEach(entry -> registry.accept(entry.itemId, entry.item.get()));
    }

    public static void registerPatterns(BiConsumer<ResourceLocation, String> registry) {

        SherdEntry.SHERDS.values().forEach(entry -> registry.accept(entry.sherdPattern.location(), entry.sherdPattern.location().getPath()));
    }

    public static CreativeModeTab createTab(CreativeModeTab.Builder builder) {

        builder.title(Component.translatable("itemgroup." + MOD_ID + ".tab"));
        builder.icon(() -> SherdEntry.SHERDS.get("howl").asItem().getDefaultInstance());
        builder.displayItems((params, output) -> SherdEntry.SHERDS.values().forEach(output::accept));
        return builder.build();
    }
}