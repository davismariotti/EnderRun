package com.github.enderrun.sound;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * TODO Move this into some sort of EnderrunPlayer class
 */
public class PlayerSoundManager {
	
	public Song song;
	public String player;
	
	public PlayerSoundManager(String player) {
		this.player = player;
	}
	
	public void play(Note note) {
		if (note == null) return;
		Player player = Bukkit.getPlayer(this.player);
		player.playSound(player.getLocation(), 
				note.getInstrument().getSound(), note.getVolume(), note.getPitch().getMinecraftPitch());
	}

}
