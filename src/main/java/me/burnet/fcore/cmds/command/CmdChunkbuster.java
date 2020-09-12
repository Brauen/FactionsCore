package me.burnet.fcore.cmds.command;

import me.burnet.fcore.utils.ItemCreation;
import me.burnet.fcore.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CmdChunkbuster implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 3) {
            if (args[0].equalsIgnoreCase("give")) {
                if (!sender.hasPermission("sabercore.chunkbusters.give")) {
                    sender.sendMessage(Message.NO_PERMISSION.getMessage());
                    return true;
                }
                if (Bukkit.getPlayer(args[1]) == null || !Bukkit.getPlayer(args[1]).isOnline()) {
                    sender.sendMessage(Message.PLAYER_NOT_FOUND.getMessage().replace("%player%", args[1]));
                    return true;
                }
                int amount = Integer.parseInt(args[2]);
                if (amount >= 1) {
                    if (Bukkit.getServer().getPlayer(args[1]).isOnline()) {
                        Bukkit.getServer().getPlayer(args[1]).getInventory().addItem(ItemCreation.giveChunkBuster(amount));
                        Bukkit.getServer().getPlayer(args[1]).sendMessage(Message.CHUNKBUSTER_RECEIVED_MESSAGE.getMessage());
                        return true;
                    }
                }
            }
        } else if (sender.hasPermission("sabercore.chunkbusters.give")) {
            sender.sendMessage(Message.CHUNKBUSTER_COMMAND_USAGE.getMessage());
        }
        return false;
    }
}