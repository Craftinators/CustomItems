package me.craftinators.customitems.Items;

import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public abstract class CustomItem implements Listener {
    public abstract ItemStack getItem();
    public abstract boolean isItem(final ItemStack item);
}
