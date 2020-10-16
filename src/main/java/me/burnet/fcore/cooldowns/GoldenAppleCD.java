package me.burnet.fcore.cooldowns;

import me.burnet.fcore.FactionCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;

public class GoldenAppleCD implements Listener {

    HashMap<String, Boolean> player_cooldown;

    public static FactionCore plugin;

    @EventHandler
    public void GappleCooldown(final PlayerItemConsumeEvent e) {
        final Player p = e.getPlayer();
        final ItemStack apple = new ItemStack(Material.GOLDEN_APPLE, 1, (short)1);
        if (e.getItem().getType().equals(Material.GOLDEN_APPLE) && e.getItem().getData().equals(apple.getData())) {
            if (!this.player_cooldown.containsKey(p.getName())) {
                this.player_cooldown.put(p.getName(), false);
            }
            if (!this.player_cooldown.get(p.getName())) {
                Bukkit.getScheduler().runTaskLater((Plugin)this, () -> {
                    this.player_cooldown.put(p.getName(), false);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("expireMessage")));
                }, (long)(plugin.getConfig().getInt("goldenAppleCooldown") * 1200));
                this.player_cooldown.put(p.getName(), true);
            }
            else {
                e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("cdMessage")));
                e.setCancelled(true);
            }
        }
    }

}
