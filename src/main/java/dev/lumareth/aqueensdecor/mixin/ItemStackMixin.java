package dev.lumareth.aqueensdecor.mixin;

import dev.lumareth.aqueensdecor.Util.ModDataComponents;
import dev.lumareth.aqueensdecor.Util.RunicUtils;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.Unit;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow
    public abstract int getMaxDamage();

    @Inject(method = "setDamageValue", at = @At("HEAD"), cancellable = true)
    private void aqueensdecor$preventRunicBreak(int damage, CallbackInfo ci) {
        ItemStack self = (ItemStack) (Object) this;

        if (damage >= this.getMaxDamage() && RunicUtils.hasRunicPreservation(self)) {
            self.set(DataComponents.DAMAGE, this.getMaxDamage() - 1);
            self.set(ModDataComponents.RUNIC_DEPLETED, Unit.INSTANCE);
            self.set(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.EMPTY);
            self.remove(DataComponents.BLOCKS_ATTACKS);
            ci.cancel();
        }
    }
}