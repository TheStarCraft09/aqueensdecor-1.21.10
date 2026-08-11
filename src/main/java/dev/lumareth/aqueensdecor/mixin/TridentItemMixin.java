package dev.lumareth.aqueensdecor.mixin;

import dev.lumareth.aqueensdecor.Util.RunicUtils;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TridentItem.class)
public class TridentItemMixin {

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void aqueensdecor$blockDepletedTrident(Level level, Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        ItemStack stack = player.getItemInHand(hand);
        if (RunicUtils.isDepleted(stack)){
            cir.setReturnValue(InteractionResult.FAIL);
        }
    }
}
