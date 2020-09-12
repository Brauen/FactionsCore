package me.burnet.fcore.hooks;

public interface PluginHook<T> {

    T setup();

    String getName();


}
