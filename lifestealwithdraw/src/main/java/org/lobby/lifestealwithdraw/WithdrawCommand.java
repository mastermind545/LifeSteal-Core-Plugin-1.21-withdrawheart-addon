package org.lobby.lifestealwithdraw.commands;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WithdrawCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        if (args.length != 1) {
            player.sendMessage("§cUsage: /withdraw <amount>");
            return true;
        }

        int amount;
        try {
            amount = Integer.parseInt(args[0]);
            if (amount <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            player.sendMessage("§cPlease enter a valid positive number.");
            return true;
        }

        // Get hearts using PlaceholderAPI
        String placeholder = PlaceholderAPI.setPlaceholders(player, "%lifesteal_hearts%");
        int currentHearts;
        try {
            currentHearts = Integer.parseInt(placeholder);
        } catch (NumberFormatException e) {
            player.sendMessage("§c[Error] Failed to get your heart count. Try again later.");
            return true;
        }

        if (currentHearts < amount) {
            player.sendMessage("§c[!] You only have §4" + currentHearts + "§c hearts, cannot withdraw §4" + amount + "§c!");
            return true;
        }

        // Proceed to withdraw and give heart item
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lifestealcore remove " + player.getName() + " " + amount);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lifestealcore giveitem " + player.getName() + " heart_tier1 " + amount);
        player.sendMessage("§a[✓] Successfully withdrew §c" + amount + "§a hearts.");
        return true;
    }
}
