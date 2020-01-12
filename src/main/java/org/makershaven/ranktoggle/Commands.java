package org.makershaven.ranktoggle;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Commands implements CommandExecutor {

    private RankToggle plugin;

    Commands(RankToggle plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You must be a player to use this command.");
            return true;

        }

        if (!sender.hasPermission("ranktoggle.use")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            return true;
        }

        if (!(args.length >= 1)) {
            return false;
        }

        String rankName = args[0].toLowerCase();

        if (cmd.getName().equalsIgnoreCase("rankon")) {

            if (sender.hasPermission("ranktoggle." + rankName)) {
                plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(),
                        plugin.PERMS_PLUGIN + " user " + sender.getName() + " " + plugin.PARENT_GROUP + " add " + rankName);
                sender.sendMessage(ChatColor.GREEN + rankName + " rank added");

            }
            else {
                sender.sendMessage(ChatColor.RED + "No such rank! Or you don't have permission for that rank!");
            }

        }
        else if (cmd.getName().equalsIgnoreCase("rankoff")) {

            if (sender.hasPermission("ranktoggle." + rankName)) {
                plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(),
                        plugin.PERMS_PLUGIN + " user " + sender.getName() + " " + plugin.PARENT_GROUP + " remove " + rankName);
                sender.sendMessage(ChatColor.GREEN + rankName + " rank removed");

            }
            else {

                sender.sendMessage(ChatColor.RED + "No such rank! Or you don't have permission for that rank!");
            }


        }
        return true;
    }

}

