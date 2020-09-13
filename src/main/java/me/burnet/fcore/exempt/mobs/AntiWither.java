package me.burnet.fcore.exempt.mobs;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class AntiWither implements Listener {

    @EventHandler
    public void onSpawn(final CreatureSpawnEvent e){
        if (e.getEntityType() == EntityType.WITHER){
            e.setCancelled(true);
        }
    }
}
