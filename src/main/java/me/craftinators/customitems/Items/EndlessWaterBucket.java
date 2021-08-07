package me.craftinators.customitems.Items;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public final class EndlessWaterBucket extends CustomItem {

    @Override
    public ItemStack getItem() {
        // Create a water bucket item
        final ItemStack item = new ItemStack(Material.WATER_BUCKET);

        // Add meta data
        final ItemMeta meta = item.getItemMeta();
        meta.setLocalizedName("Endless Water Bucket");
        meta.setCustomModelData(1);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);

        // Return finished item
        return item;
    }

    @EventHandler
    public void onBucketDrain(final PlayerBucketEmptyEvent event) {
        if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 1) return;

        getBucketEmptyLocation(event).setType(Material.WATER);
        event.setCancelled(true);
    }

    private Block getBucketEmptyLocation(final PlayerBucketEmptyEvent event) {
        final int x = event.getBlockClicked().getX() + event.getBlockFace().getModX();
        final int y = event.getBlockClicked().getY() + event.getBlockFace().getModY();
        final int z = event.getBlockClicked().getZ() + event.getBlockFace().getModZ();

        return event.getPlayer().getWorld().getBlockAt(x, y, z);
    }
}
