package me.driftay.score.exempt;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class DragonEggAntiTP implements Listener {

    @EventHandler
    public void onDeggClick(PlayerInteractEvent e) {
        Block b = e.getClickedBlock();

        if (b == null)
            return;

        if (b.getType() != Material.DRAGON_EGG)
            return;

        if (e.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;

        e.setCancelled(true);
    }

    @EventHandler
    public void onBlockChange(BlockFromToEvent event){
        if(event.getBlock().getType() == Material.DRAGON_EGG){
            event.setCancelled(true);
        }
    }
}

