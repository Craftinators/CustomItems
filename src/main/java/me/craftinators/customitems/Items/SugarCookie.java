package me.craftinators.customitems.Items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SugarCookie extends CustomItem {
    public SugarCookie(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        addRecipe(plugin);
    }

    @Override
    public ItemStack getItem() {
        final ItemStack item = new ItemStack(Material.COOKIE);

        final ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("Sugar Cookie").decoration(TextDecoration.ITALIC, false));
        meta.setCustomModelData(1);
        item.setItemMeta(meta);

        return item;
    }

    @Override
    public boolean isItem(ItemStack item) {
        if (item.getType() != Material.COOKIE) return false;

        ItemMeta meta = item.getItemMeta();
        return meta.hasCustomModelData() && meta.getCustomModelData() == 1;
    }

    @EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
        if(!isItem(event.getItem())) return;

        Player player = event.getPlayer();

        player.setFoodLevel(player.getFoodLevel() + 1);
        if (player.getFoodLevel() > 20) player.setFoodLevel(20);

        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 0));
    }

    private void addRecipe(JavaPlugin plugin) {
        NamespacedKey key = new NamespacedKey(plugin, "sugar_cookie");

        ItemStack item = getItem();
        item.setAmount(4);

        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("   ", "WSW", "   ");
        recipe.setIngredient('W', Material.WHEAT);
        recipe.setIngredient('S', Material.SUGAR);

        plugin.getServer().addRecipe(recipe);
    }
}
