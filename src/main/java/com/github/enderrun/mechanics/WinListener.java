package com.github.enderrun.mechanics;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.github.enderrun.EnderRun;
import com.github.enderrun.Game;
import com.github.enderrun.GameManager;

public class WinListener implements Listener {

	// note that we can't use EnderRun.getInstance yet
	public WinListener(EnderRun er) {
		er.getServer().getPluginManager().registerEvents(this, er);
	}

	@EventHandler
	public void onWin(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if (GameManager.getInstance().currentGame.state == Game.GameStates.GAME_RUNNING &&
				GameManager.getInstance().currentGame.playersInGame.containsKey(player.getName()) &&
				isNearBeacon(player)) {
			
			GameManager.getInstance().currentGame.winner = player.getName();
			GameManager.getInstance().currentGame.removePlayer(player.getName());
			GameManager.getInstance().currentGame.finish();
		}
	}

	private boolean isNearBeacon(Player player) {
		int x = player.getLocation().getBlockX();
		int y = player.getLocation().getBlockY();
		int z = player.getLocation().getBlockZ();
		World world = player.getLocation().getWorld();
		
		if (world == EnderRun.getInstance().gameWorld) {
			for (int cx = x-1; cx < x+1; cx++) {
				for (int cy = y-1; cy < y+1; cy++) {
					for (int cz = z-1; cz < z+1; cz++) {
						if (world.getBlockAt(cx, cy, cz).getType() == Material.BEACON) return true;
					}
				}
			}
		}
		
		return false;
	}
}
