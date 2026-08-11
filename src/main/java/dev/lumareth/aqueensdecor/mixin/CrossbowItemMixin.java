package dev.lumareth.aqueensdecor.mixin;

import dev.lumareth.aqueensdecor.Util.RunicUtils;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CrossbowItem.class)
public class CrossbowItemMixin {

    @Inject(method = "use", at = @At ("HEAD"), cancellable = true)
    private void aqueensdecor$blockDepletedCrossbow(Level level, Player player, InteractionHand hand,
                                                    CallbackInfoReturnable<InteractionResult> cir) {
        ItemStack stack = player.getItemInHand(hand);
        ChargedProjectiles chargedProjectiles = stack.get(DataComponents.CHARGED_PROJECTILES);

        if ((chargedProjectiles != null && !chargedProjectiles.isEmpty() && RunicUtils.isDepleted(stack)) || chargedProjectiles == null && RunicUtils.isDepleted(stack)) {
            cir.setReturnValue(InteractionResult.FAIL);
        }
    }
}
