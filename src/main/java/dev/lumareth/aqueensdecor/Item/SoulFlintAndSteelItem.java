package dev.lumareth.aqueensdecor.Item;

import dev.lumareth.aqueensdecor.world.dimension.UnderworldPortalShape;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;

public class SoulFlintAndSteelItem extends Item {
    public SoulFlintAndSteelItem(Item.Properties properties) {
        super(properties.durability(7));
    }

    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {
        Player player = useOnContext.getPlayer();
        Level level = useOnContext.getLevel();
        BlockPos blockPos = useOnContext.getClickedPos();
        BlockPos portalPos = blockPos.relative(useOnContext.getClickedFace());
        BlockState blockState = level.getBlockState(blockPos);

        if (!level.isClientSide()
                && blockState.is(Blocks.REINFORCED_DEEPSLATE)
                && (UnderworldPortalShape.tryLight(level, portalPos) || UnderworldPortalShape.tryLight(level, blockPos))) {
            level.playSound(player, blockPos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
            if (player != null) {
                useOnContext.getItemInHand().hurtAndBreak(1, player, useOnContext.getHand().asEquipmentSlot());
            }
            return InteractionResult.SUCCESS;
        }

        if (!CampfireBlock.canLight(blockState) && !CandleBlock.canLight(blockState) && !CandleCakeBlock.canLight(blockState)) {
            BlockPos blockPos2 = blockPos.relative(useOnContext.getClickedFace());
            if (BaseFireBlock.canBePlacedAt(level, blockPos2, useOnContext.getHorizontalDirection())) {
                level.playSound(player, blockPos2, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
                level.setBlock(blockPos2, Blocks.SOUL_FIRE.defaultBlockState(), 11);
                level.gameEvent(player, GameEvent.BLOCK_PLACE, blockPos);
                ItemStack itemStack = useOnContext.getItemInHand();
                if (player instanceof ServerPlayer) {
                    CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer) player, blockPos2, itemStack);
                    itemStack.hurtAndBreak(1, player, useOnContext.getHand().asEquipmentSlot());
                }

                return InteractionResult.SUCCESS;
            } else {
                return InteractionResult.FAIL;
            }
        } else {
            level.playSound(player, blockPos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
            level.setBlock(blockPos, blockState.setValue(BlockStateProperties.LIT, true), 11);
            level.gameEvent(player, GameEvent.BLOCK_CHANGE, blockPos);
            if (player != null) {
                useOnContext.getItemInHand().hurtAndBreak(1, player, useOnContext.getHand().asEquipmentSlot());
            }

            return InteractionResult.SUCCESS;
        }
    }
}