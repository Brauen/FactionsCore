package me.burnet.fcore.cmds.command;

import me.burnet.fcore.FactionCore;
import me.burnet.fcore.utils.Message;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class CmdSOTW implements CommandExecutor, Listener {

    @EventHandler
    public void onExplode(final BlockExplodeEvent e){
        if (FactionCore.getInstance().getConfig().getBoolean("allow-explosions")){
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBreak(final EntityExplodeEvent e){
        if (FactionCore.getInstance().getConfig().getBoolean("allow-explosions")){
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {

        if (!(sender.hasPermission("factioncore.sotw"))){
            sender.sendMessage(Message.NO_PERMISSION.getMessage());
            return true;
        }

        Player p = (Player) sender;

        if (args.length != 1){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m----------------------------------------"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&l(!) &7/sotw &a<start, end> &7Optional » &a(-disable wither)"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&l(!) &7/sotw &astatus"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m----------------------------------------"));
            return true;
        }

        if (args.length == 1){
            if (args[0].equalsIgnoreCase("start")){
                FactionCore.getInstance().getConfig().set("allow-explosions", (Object)false);
                FactionCore.getInstance().saveConfig();
                FactionCore.getInstance().reloadConfig();
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&l(!) &aSOTW has now been &a&nEnabled!"));
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&l(!) &7TNT Explosions &c&nDisabled"));
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&l(!) &7All Explosions &c&nDisabled"));
                return true;
            }
            if (args[0].equalsIgnoreCase("end")){
                FactionCore.getInstance().getConfig().set("allow-explosions", (Object)true);
                FactionCore.getInstance().saveConfig();
                FactionCore.getInstance().reloadConfig();
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&l(!) &aSOTW has now been &c&nDisabled!"));
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&l(!) &7TNT Explosions &a&nEnabled"));
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&l(!) &7All Explosions &a&nEnabled"));
                return true;
            }
            if (args[0].equalsIgnoreCase("status")){
                if (FactionCore.getInstance().getConfig().getBoolean("allow-explosions") == false){
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&l(!) SOTW » &a&nStarted!"));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&l(!) Explosions » &cDisabled"));
                    return true;
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&l(!) SOTW » &c&nEnded!"));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&l(!) Explosions » &aEnabled"));
                    return true;
                }
            }
            return true;
        }
        return true;
    }
}
