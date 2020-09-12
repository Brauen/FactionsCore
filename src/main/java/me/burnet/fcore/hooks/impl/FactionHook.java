package me.burnet.fcore.hooks.impl;


import me.burnet.fcore.FactionCore;
import me.burnet.fcore.exception.NotImplementedException;
import me.burnet.fcore.hooks.PluginHook;
import me.burnet.fcore.hooks.impl.factions.FactionMCHook;
import me.burnet.fcore.hooks.impl.factions.FactionUUIDHook;
import me.burnet.fcore.utils.Logger;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.List;

public class FactionHook implements PluginHook<FactionHook> {

    @Override
    public FactionHook setup() {
        if (FactionCore.instance.getServer().getPluginManager().getPlugin(getName()) == null) {
            Logger.print("Factions could not be found", Logger.PrefixType.WARNING);
            return null;
        }
        List<String> authors = FactionCore.instance.getServer().getPluginManager().getPlugin(getName()).getDescription().getAuthors();
        if (!authors.contains("drtshock") && !authors.contains("Benzimmer")) {
            Logger.print("Server Factions type has been set to (MassiveCore)", Logger.PrefixType.DEFAULT);
            return new FactionMCHook();
        } else {
            Logger.print("Server Factions type has been set to (FactionsUUID/SavageFactions/FactionsUltimate)", Logger.PrefixType.DEFAULT);
            return new FactionUUIDHook();
        }
    }

    public boolean canBuild(Block block, Player player) {
        try {
            throw new NotImplementedException("Factions does not exist!");
        } catch (NotImplementedException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getName() {
        return "Factions";
    }

}
