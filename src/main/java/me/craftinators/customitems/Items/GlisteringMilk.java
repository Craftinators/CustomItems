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

public class GlisteringMilk extends CustomItem {
    public GlisteringMilk(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        addRecipe(plugin);
    }

    @Override
    public ItemStack getItem() {
        final ItemStack item = new ItemStack(Material.MILK_BUCKET);

        final ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("Glistering Milk").decoration(TextDecoration.ITALIC, false));
        meta.setCustomModelData(1);
        item.setItemMeta(meta);

        return item;
    }

    @Override
    public boolean isItem(ItemStack item) {
        if (item.getType() != Material.MILK_BUCKET) return false;

        ItemMeta meta = item.getItemMeta();
        return meta.hasCustomModelData() && meta.getCustomModelData() == 1;
    }

    @EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
        if(!isItem(event.getItem())) return;

        Player player = event.getPlayer();

        for (PotionEffect effect : player.getActivePotionEffects())
            player.removePotionEffect(effect.getType());
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20, 4));

        event.setCancelled(true);
    }

    private void addRecipe(JavaPlugin plugin) {
        NamespacedKey key = new NamespacedKey(plugin, "glistering_milk");

        ShapedRecipe recipe = new ShapedRecipe(key, getItem());
        recipe.shape("GGG", "GMG", "GGG");
        recipe.setIngredient('G', Material.GOLD_NUGGET);
        recipe.setIngredient('M', Material.MILK_BUCKET);

        plugin.getServer().addRecipe(recipe);
    }
}
