package com.github.enderrun.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.github.enderrun.EnderRun;


public class VoidListener implements Listener {
    EnderRun plugin;

    public VoidListener(EnderRun er) {
        this.plugin = er;
    }
    @EventHandler
    public void onFall(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
        if (event.getCause() == DamageCause.VOID) {
            Player player = (Player)event.getEntity();
                if (plugin.game.contains(player.getName())) {
                    player.teleport(plugin.getServer().getWorlds().get(0).getSpawnLocation());
                }    
            }
        }
    }
}
