package dev.lumareth.aqueensdecor.mixin;

import dev.lumareth.aqueensdecor.Item.SoulFlintAndSteelItem;
import dev.lumareth.aqueensdecor.Util.RunicUtils;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SoulFlintAndSteelItem.class)
public class SoulFlintAndSteelItemMixin {
    @Inject(method = "useOn", at = @At("HEAD"), cancellable = true)
    private void aqueensdecor$blockSoulFlintAndSteelUseOn(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
        if (RunicUtils.isDepleted(context.getItemInHand())) {
            cir.setReturnValue(InteractionResult.FAIL);
        }
    }
}
