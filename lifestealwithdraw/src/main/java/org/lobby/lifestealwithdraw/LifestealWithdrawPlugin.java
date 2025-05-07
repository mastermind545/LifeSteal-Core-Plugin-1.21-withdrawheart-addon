package org.lobby.lifestealwithdraw;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.lobby.lifestealwithdraw.commands.WithdrawCommand;

public class LifestealWithdrawPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Check if PlaceholderAPI is present
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) {
            getLogger().severe("PlaceholderAPI not found! Disabling plugin.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        // Register the /withdraw command
        getCommand("withdraw").setExecutor(new WithdrawCommand());

        getLogger().info("LifestealWithdraw plugin enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("LifestealWithdraw plugin disabled.");
    }
}
