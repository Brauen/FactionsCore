package me.burnet.fcore.cmds.chat;

import me.burnet.fcore.utils.Message;
import me.burnet.fcore.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CmdSlowChat implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("sabercore.staff")) {
            if (args.length == 1) {
                try {
                    ChatHandler.delayTime = Integer.parseInt(args[0]);
                    Bukkit.broadcastMessage(Message.SLOW_CHAT_BROADCAST.getMessage());
                } catch (NumberFormatException e) {
                    sender.sendMessage(Util.color(args[0] + "&7is not a valid number!"));
                }
            } else {
                sender.sendMessage(Util.color("&c&l[!] &7Try /slowchat <time>"));
            }
        } else {
            sender.sendMessage(Message.NO_PERMISSION.getMessage());
        }
        return false;
    }
}
