package me.craftinators.customitems.Items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.World.Environment;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.CauldronLevelChangeEvent;
import org.bukkit.event.block.CauldronLevelChangeEvent.ChangeReason;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public final class EndlessWaterBucket extends CustomItem {

    @Override
    public ItemStack getItem() {
        final ItemStack item = new ItemStack(Material.WATER_BUCKET);

        final ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("Endless Water Bucket").decoration(TextDecoration.ITALIC, false));
        meta.setCustomModelData(1);
        item.setItemMeta(meta);

        return item;
    }

    @Override
    public boolean isItem(final ItemStack item) {
        if (item.getType() != Material.WATER_BUCKET) return false;

        ItemMeta meta = item.getItemMeta();
        if (!meta.hasCustomModelData() || meta.getCustomModelData() != 1) return false;
        return true;
    }

    @EventHandler
    public void onPlayerBucketEmpty(final PlayerBucketEmptyEvent event) {
        if (!isItem(event.getPlayer().getInventory().getItemInMainHand())) return;

        if (event.getPlayer().getWorld().getEnvironment() != Environment.NETHER) {
            getBucketEmptyLocation(event).setType(Material.WATER);
        }

        event.setCancelled(true);
    }

    @EventHandler
    public void onCauldronLevelChange(final CauldronLevelChangeEvent event) {
        if(event.getReason() != ChangeReason.BUCKET_EMPTY) return;

        Player player = (Player) event.getEntity();
        if (!isItem(Objects.requireNonNull(player).getInventory().getItemInMainHand())) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerBucketEntity(final PlayerBucketEntityEvent event) {
        if (!isItem(event.getPlayer().getInventory().getItemInMainHand())) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockDispense(final BlockDispenseEvent event) {
        if (!isItem(event.getItem())) return;
        event.setCancelled(true);
    }

    private Block getBucketEmptyLocation(final PlayerBucketEmptyEvent event) {
        final Block block = event.getBlockClicked();
        final BlockFace face = event.getBlockFace();

        final int x = block.getX() + face.getModX();
        final int y = block.getY() + face.getModY();
        final int z = block.getZ() + face.getModZ();

        return event.getPlayer().getWorld().getBlockAt(x, y, z);
    }
}
