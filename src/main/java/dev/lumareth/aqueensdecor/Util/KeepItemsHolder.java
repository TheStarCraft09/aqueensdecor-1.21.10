package dev.lumareth.aqueensdecor.Util;

import net.minecraft.world.item.ItemStack;
import java.util.List;

public interface KeepItemsHolder {
    List<ItemStack> getKeptItems();
    void setKeptItems(List<ItemStack> items);
}
