package com.github.enderrun.mechanics;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.github.enderrun.EnderRun;
import com.github.enderrun.GameManager;

public class VoidListener implements Listener {

    EnderRun plugin;

    public VoidListener(EnderRun er) {
        this.plugin = er;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onFall(EntityDamageEvent event) {
        if(event.getEntity() instanceof Player) {
            if(event.getCause() == DamageCause.VOID) {
                Player player = (Player) event.getEntity();
                if(GameManager.getInstance().currentGame.players.containsKey(player.getName())) {
                    Location fallLocation = player.getLocation();
                	
                	player.teleport(new Location(fallLocation.getWorld(), fallLocation.getX(), 70, fallLocation.getZ()));
                }
            }
        }
    }
}
