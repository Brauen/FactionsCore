package me.burnet.fcore.hooks;

import me.burnet.fcore.FactionCore;
import me.burnet.fcore.utils.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HookManager {

    private static Map<String, PluginHook<?>> pluginMap = new HashMap<>();

    public HookManager(List<PluginHook<?>> list) {
        for (PluginHook<?> hook : list) {
            pluginMap.put(hook.getName(), (PluginHook<?>) hook.setup());
            if (FactionCore.instance.getServer().getPluginManager().getPlugin(hook.getName()) != null) {
                Logger.print("successfully hooked " + hook.getName(), Logger.PrefixType.DEFAULT);
            } else {
                Logger.print("could not hook " + hook.getName(), Logger.PrefixType.WARNING);
            }
        }
    }

    public static Map<String, PluginHook<?>> getPluginMap() {
        return pluginMap;
    }

}

