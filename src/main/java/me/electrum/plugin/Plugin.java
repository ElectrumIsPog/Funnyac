package me.electrum.plugin;

import me.electrum.plugin.listener.MoveListener;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new MoveListener(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("niggers suck");
    }

}
