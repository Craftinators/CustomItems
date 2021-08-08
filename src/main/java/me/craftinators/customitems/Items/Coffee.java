package me.craftinators.customitems.Items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Coffee extends CustomItem {
    public Coffee(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public ItemStack getItem() {
        final ItemStack item = new ItemStack(Material.POTION);

        final ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("Coffee").decoration(TextDecoration.ITALIC, false));
        meta.setCustomModelData(1);
        item.setItemMeta(meta);

        return item;
    }

    @Override
    public boolean isItem(ItemStack item) {
        if (item.getType() != Material.POTION) return false;

        ItemMeta meta = item.getItemMeta();
        return meta.hasCustomModelData() && meta.getCustomModelData() == 1;
    }

    @EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
        if(!isItem(event.getItem())) return;

        Player player = event.getPlayer();

        player.setFoodLevel(player.getFoodLevel() + 3);
        if (player.getFoodLevel() > 20) player.setFoodLevel(20);

        player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 2400, 0));
    }
}
