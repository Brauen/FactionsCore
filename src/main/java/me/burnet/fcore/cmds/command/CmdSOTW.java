package me.burnet.fcore.cmds.command;

import me.burnet.fcore.utils.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CmdSOTW implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {

        if (!(sender.hasPermission("factioncore.sotw"))){
            sender.sendMessage(Message.NO_PERMISSION.getMessage());
            return true;
        }

        return true;
    }
}
