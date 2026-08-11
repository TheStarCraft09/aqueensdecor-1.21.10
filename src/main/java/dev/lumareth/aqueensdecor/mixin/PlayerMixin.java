package dev.lumareth.aqueensdecor.mixin;

import dev.lumareth.aqueensdecor.Util.KeepItemsHolder;
import dev.lumareth.aqueensdecor.Util.RunicUtils;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;


@Mixin(Player.class)
public abstract class PlayerMixin implements KeepItemsHolder {
    @Shadow
    @Final
    private Inventory inventory;

    @Unique
    private List<ItemStack> keptItems = new ArrayList<>();

    @Override
    public List<ItemStack> getKeptItems() {
        return keptItems;
    }

    @Override
    public void setKeptItems(List<ItemStack> items) {
        this.keptItems = items;
    }

    @Inject(method = "dropEquipment", at = @At("HEAD"))
    private void aqueensdecor$preventEnchantedEquipmentDrop(CallbackInfo ci){
        if ((Object) this instanceof ServerPlayer serverPlayer) {
            for (int i = 0; i < this.inventory.getContainerSize(); i++) {
                ItemStack stack = this.inventory.getItem(i);
                if (!stack.isEmpty() && RunicUtils.hasRunicTotem(stack)){
                    this.keptItems.add(stack.copy());
                    this.inventory.removeItemNoUpdate(i);
                }
            }
        }
    }
}
