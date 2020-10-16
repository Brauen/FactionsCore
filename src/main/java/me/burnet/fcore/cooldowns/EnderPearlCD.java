package me.burnet.fcore.cooldowns;

import com.massivecraft.factions.util.CC;
import me.burnet.fcore.FactionCore;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.*;

public class EnderPearlCD implements Listener {

    private final Map<String, Long> lastThrow;
    private static final Set<Material> interactables;

    public EnderPearlCD() {
        this.lastThrow = new HashMap<String, Long>();
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerUse(final PlayerInteractEvent e){
        if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK || e.getItem() == null || e.getItem().getType() != Material.ENDER_PEARL) {
            return;
        }
        if (e.getClickedBlock() != null && !e.isCancelled() && !e.getPlayer().isSneaking()) {
            final Material clickedMat = e.getClickedBlock().getType();
            if (this.interactables.contains(clickedMat)) {
                return;
            }
        }
        final Player player = e.getPlayer();
        final Long now = System.currentTimeMillis();
        if (this.validthrow(player, now)) {
                this.lastThrow.put(player.getName(), now);
        } else {
            e.setCancelled(true);
        }
    }

    private boolean validthrow(final Player player, final long throwTime) {
        if (!player.hasPermission("enderpearl.cooldown")) {
            return true;
        }
        final Long lastPlayerPearl = this.lastThrow.get(player.getName());
        if (lastPlayerPearl == null || throwTime - lastPlayerPearl >= FactionCore.getInstance().cooldown) {
            return true;
        }
        player.sendMessage(CC.translate("&cEnderpearl cooldown remaining:" + this.remainingCooldown() + "seconds."));
        return false;
    }

    private String remainingCooldown() {
        return remainingCooldown();
    }

    private double remainingCooldown(final Player player, final long throwTime) {
        final Long lastPlayerPearl = this.lastThrow.get(player.getName());
        return (FactionCore.getInstance().cooldown - (throwTime - lastPlayerPearl)) / 1000.0;
    }

    static {
        interactables = new HashSet<Material>(Arrays.asList(Material.ANVIL, Material.COMMAND, Material.BED, Material.BEACON, Material.BED_BLOCK, Material.BREWING_STAND, Material.BURNING_FURNACE, Material.CAKE_BLOCK, Material.CHEST, Material.DIODE, Material.DIODE_BLOCK_OFF, Material.DIODE_BLOCK_ON, Material.DISPENSER, Material.DROPPER, Material.ENCHANTMENT_TABLE, Material.ENDER_CHEST, Material.FENCE_GATE, Material.FENCE_GATE, Material.FURNACE, Material.HOPPER, Material.IRON_DOOR, Material.IRON_DOOR_BLOCK, Material.ITEM_FRAME, Material.LEVER, Material.REDSTONE_COMPARATOR, Material.REDSTONE_COMPARATOR_OFF, Material.REDSTONE_COMPARATOR_ON, Material.STONE_BUTTON, Material.TRAP_DOOR, Material.TRAPPED_CHEST, Material.WOODEN_DOOR, Material.WOOD_BUTTON, Material.WOOD_DOOR, Material.WORKBENCH));
    }

}
