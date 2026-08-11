package dev.lumareth.aqueensdecor.mixin;

import dev.lumareth.aqueensdecor.Util.KeepItemsHolder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin {
    @Inject(method = "restoreFrom", at = @At("HEAD"))
    private void aqueensdecor$restoreKeptItems(ServerPlayer serverPlayer, boolean alive, CallbackInfo ci) {
        if (!alive){
            ServerPlayer newPlayer = (ServerPlayer) (Object) this;
            List<ItemStack> keptItems = ((KeepItemsHolder) serverPlayer).getKeptItems();
            for (ItemStack itemStack : keptItems) {
                newPlayer.getInventory().add(itemStack);
            }
            keptItems.clear();
        }
    }

}
