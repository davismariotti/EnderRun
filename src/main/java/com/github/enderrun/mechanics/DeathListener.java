package com.github.enderrun.mechanics;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.github.enderrun.EnderRun;
import com.github.enderrun.GameManager;

public class DeathListener implements Listener {

	// note that we can't use EnderRun.getInstance yet
	public DeathListener(EnderRun er) {
		er.getServer().getPluginManager().registerEvents(this, er);
	}

	@EventHandler
	public void onFallInVoid(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			if (GameManager.getInstance().currentGame.playersInGame.containsKey(player.getName())) {
				//if they fall in the void
				if (event.getCause() == DamageCause.VOID) {
					Location fallLocation = player.getLocation();
					player.teleport(new Location(fallLocation.getWorld(),fallLocation.getX(), 80, fallLocation.getZ()));
				}
				//if they will die
				else if (player.getHealth() - event.getDamage() <= 1){
					GameManager.getInstance().currentGame.removePlayer(player.getName());
					event.setCancelled(true);
				}
			}
		}
	}
}
