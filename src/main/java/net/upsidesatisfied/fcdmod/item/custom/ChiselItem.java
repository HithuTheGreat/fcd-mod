package net.upsidesatisfied.fcdmod.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.VaultBlockEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientCommandSource;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;
import net.upsidesatisfied.fcdmod.block.ModBlocks;

import java.util.List;
import java.util.Map;

public class ChiselItem extends Item {
    public static final Map<Block, Block> Chiesel_Map =
            Map.of(
                    Blocks.STONE, Blocks.STONE_BRICKS,
                    Blocks.END_STONE, Blocks.END_STONE_BRICKS,
                    Blocks.OAK_LOG, ModBlocks.PINK_GARNET_BLOCK,
                    Blocks.GOLD_BLOCK, Blocks.NETHERITE_BLOCK
            );


    public ChiselItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if(Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("item.fcdmod.chisel.shift_tooltip"));
        } else {
            tooltip.add(Text.translatable("item.fcdmod.chisel.tooltip"));
        }
        super.appendTooltip(stack, context, tooltip, type);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        Block clickedBlock = world.getBlockState(context.getBlockPos()).getBlock();

        if(Chiesel_Map.containsKey(clickedBlock)){
            if(!world.isClient){
                world.setBlockState(context.getBlockPos(), Chiesel_Map.get(clickedBlock).getDefaultState());

                context.getStack().damage(1, ((ServerWorld) world), ((ServerPlayerEntity) context.getPlayer()),
                        item -> context.getPlayer().sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND));

                world.playSound(null, context.getBlockPos(), SoundEvents.BLOCK_GRINDSTONE_USE, SoundCategory.BLOCKS);

            }

        }



        return ActionResult.SUCCESS;
    }
}
