package dev.lumareth.aqueensdecor.mixin;

import dev.lumareth.aqueensdecor.Util.RunicUtils;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AxeItem.class)
public abstract class AxeItemMixin {
    @Inject(method = "useOn", at = @At("HEAD"), cancellable = true)
    private void aqueensdecor$blockDepletedAxeUse(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
        if (RunicUtils.isDepleted(context.getItemInHand())) {
            cir.setReturnValue(InteractionResult.FAIL);
        }
    }
}
