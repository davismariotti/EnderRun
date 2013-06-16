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
	
	public void play(Note note) throws PlayerLeftException {
		if (note == null || note.getPitch() == Pitch.REST) return;
		Player player = Bukkit.getPlayer(this.player);
		if (player == null) throw new PlayerLeftException();
		player.playSound(player.getLocation(), 
				note.getInstrument().getSound(), note.getVolume(), note.getPitch().getMinecraftPitch());
	}

}
