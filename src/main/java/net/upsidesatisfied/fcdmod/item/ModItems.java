package net.upsidesatisfied.fcdmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.upsidesatisfied.fcdmod.FcdMod;

public class ModItems {

    public static final Item PINK_GARNET = RegisterItem("pink_garnet", new Item(new Item.Settings()));
    public static final Item RAW_PINK_GARNET = RegisterItem("raw_pink_garnet", new Item(new Item.Settings()));


    private static Item RegisterItem(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(FcdMod.MOD_ID, name), item);
    }

    public static void registerModItems(){
        FcdMod.LOGGER.info("Registering Mod Items for " + FcdMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(PINK_GARNET);
            entries.add(RAW_PINK_GARNET);
        });
    }

}
