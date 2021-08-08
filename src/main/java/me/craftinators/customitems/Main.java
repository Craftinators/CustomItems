package me.craftinators.customitems;

import me.craftinators.customitems.Items.Coffee;
import me.craftinators.customitems.Items.EndlessWaterBucket;
import me.craftinators.customitems.Items.GlisteringMilk;
import me.craftinators.customitems.Items.SugarCookie;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        new Coffee(this);
        new EndlessWaterBucket(this);
        new GlisteringMilk(this);
        new SugarCookie(this);
    }

    @Override
    public void onDisable() {

    }
}
