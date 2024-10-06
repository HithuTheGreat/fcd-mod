package net.upsidesatisfied.fcdmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.upsidesatisfied.fcdmod.FcdMod;
import net.upsidesatisfied.fcdmod.item.custom.ChiselItem;

import java.util.List;

public class ModItems {
        public static final Item PINK_GARNET = RegisterItem("pink_garnet", new Item(new Item.Settings()));
    public static final Item RAW_PINK_GARNET = RegisterItem("raw_pink_garnet", new Item(new Item.Settings()));
    public static final Item CHISEL = RegisterItem("chisel",new ChiselItem(new Item.Settings().maxDamage(32)));
    private static Item RegisterItem(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(FcdMod.MOD_ID, name), item);
    }
    public static final Item CAULIFLOWER = RegisterItem("cauliflower", new Item(new Item.Settings().food(ModFoodComponents.CAULIFLOWER.build())){
        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(Text.translatable("item.fcdmod.cauliflower.tooltip"));
            super.appendTooltip(stack, context, tooltip, type);
        }
    });

    public static final Item STARLIGHT_ASHES = RegisterItem("starlight_ashes", new Item(new Item.Settings()));

    public static void registerModItems(){
        FcdMod.LOGGER.info("Registering Mod Items for " + FcdMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(PINK_GARNET);
            entries.add(RAW_PINK_GARNET);
        });
    }

}
