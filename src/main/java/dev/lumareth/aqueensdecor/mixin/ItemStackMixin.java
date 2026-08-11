package dev.lumareth.aqueensdecor.mixin;

import dev.lumareth.aqueensdecor.AQueensDecor;
import dev.lumareth.aqueensdecor.Util.ModDataComponents;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Unit;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
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

        if (damage >= this.getMaxDamage() && aqueensdecor$hasRunicPreservation(self)) {
            self.set(DataComponents.DAMAGE, this.getMaxDamage() - 1);
            self.set(ModDataComponents.RUNIC_DEPLETED, Unit.INSTANCE);
            self.set(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.EMPTY);
            self.remove(DataComponents.BLOCKS_ATTACKS);
            ci.cancel();
        }
    }

    @Unique
    private boolean aqueensdecor$hasRunicPreservation(ItemStack stack) {
        ItemEnchantments enchants = stack.get(DataComponents.ENCHANTMENTS);
        if (enchants == null) {
            return false;
        }
        for (Holder<Enchantment> holder : enchants.keySet()) {
            if (holder.is(ResourceLocation.fromNamespaceAndPath(AQueensDecor.MOD_ID, "runic/runic_preservation"))) {
                return true;
            }
        }
        return false;
    }
}