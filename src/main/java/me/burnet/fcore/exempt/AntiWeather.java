package me.burnet.fcore.exempt;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class AntiWeather implements Listener {

    @EventHandler
    public void change(WeatherChangeEvent e){
        if (e.toWeatherState()){
            e.getWorld().setWeatherDuration(0);
            e.getWorld().setTime(0);
            e.setCancelled(true);
        }
    }
}
