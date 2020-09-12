package me.driftay.score.exempt.mobs;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class AntiBabyZombie implements Listener {
	@EventHandler
	public void onEntitySpawn(CreatureSpawnEvent event){
		if(event.getEntity().getType() == EntityType.ZOMBIE){
			Zombie zombie = (Zombie) event.getEntity();
			if(zombie.isBaby())
				event.setCancelled(true);
			if(zombie.isInsideVehicle())
				event.setCancelled(true);
		}
	}
}
