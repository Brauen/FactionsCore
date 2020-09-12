package me.burnet.fcore.exempt;

import me.burnet.fcore.utils.Message;
import me.burnet.fcore.utils.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.List;

public class DisabledCommands implements Listener {

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        String message = e.getMessage();
        List<String> list = Util.config.getStringList("DisabledCommands.Commands");

        for (String s : list) {
            if (message.equalsIgnoreCase(s) && !p.hasPermission("sabercore.disabledcommands.bypass")) {
                e.setCancelled(true);
                p.sendMessage(Message.DISABLED_COMMAND_MESSAGE.getMessage().replace("%command%", message.toLowerCase()));
            }
        }
    }
}
