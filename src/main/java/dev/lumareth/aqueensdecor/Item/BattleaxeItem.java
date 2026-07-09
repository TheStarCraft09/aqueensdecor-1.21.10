package dev.lumareth.aqueensdecor.Item;

import dev.lumareth.aqueensdecor.Tags.ModBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class BattleaxeItem extends Item {

    public BattleaxeItem(ToolMaterial material, float attackDamage, float attackSpeed, Properties properties) {
        super(applyBattleaxeProperties(properties, material, attackDamage, attackSpeed));
    }


    private static Properties applyBattleaxeProperties(Properties properties, ToolMaterial material, float attackDamage, float attackSpeed) {
        return material.applyToolProperties(
                properties,
                ModBlockTags.BATTLEAXE_MINEABLE,
                attackDamage,
                attackSpeed,
                5.0F // shield-disable duration, same as vanilla axes
        );
    }

    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        return state.is(ModBlockTags.BATTLEAXE_MINEABLE);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity miningEntity) {
        if (state.is(ModBlockTags.BATTLEAXE_MINEABLE)) {
            if (!level.isClientSide() && level instanceof ServerLevel serverLevel && miningEntity instanceof ServerPlayer serverPlayer) {
                stack.hurtAndBreak(1, serverLevel, serverPlayer, item ->
                        miningEntity.onEquippedItemBroken(item, EquipmentSlot.MAINHAND));
            }
            return true;
        }
        return false;
    }
}