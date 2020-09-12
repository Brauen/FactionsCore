package me.burnet.fcore.utils;

import me.burnet.fcore.FactionCore;
import org.bukkit.ChatColor;

public class Logger {

    public static void print(String message, PrefixType type) {
        FactionCore.instance.getServer().getConsoleSender().sendMessage(type.getPrefix() + message);
    }

    public enum PrefixType {

        WARNING(ChatColor.RED + "WARNING: "), NONE(""), DEFAULT(ChatColor.GOLD + "[FactionCore] "), FAILED(ChatColor.RED + "FAILED: ");

        private String prefix;

        PrefixType(String prefix) {
            this.prefix = prefix;
        }

        public String getPrefix() {
            return this.prefix;
        }

    }

}
