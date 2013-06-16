package com.github.enderrun.sound;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import com.github.enderrun.EnderRun;


public class SoundTrack {
	
	private int bpm = 60;
	private String player;
	private int i = 0;
	
	public SoundTrack(String player, int bpm) {
		this.player = player;
		this.bpm = bpm;
	}

	//TODO make a beat-dispacher
	public void play() {
		
		Runnable task = new Runnable() {

			@Override
			public void run() {
				if (i < 0 || i > 24) i=0;
				playNote(Pitch.getPitchBySemitone(i), Instrument.BASS_GUITAR, 1.0f);
				playNote(Pitch.getPitchBySemitone(i + 6), Instrument.PIANO, 0.7f);
				playNote(Pitch.getPitchBySemitone(i + 10), Instrument.PIANO, 0.5f);
				playNote(Pitch.getPitchBySemitone(i + 12), Instrument.PIANO, 0.3f);
				i++;
			}
			
		};
		
		EnderRun.getInstance().getServer().getScheduler().runTaskTimer(EnderRun.getInstance(), task, 0, 5);
		
	}
	
	private void playNote(Pitch pitch, Instrument instrument, float volume) {
		Player player = Bukkit.getPlayer(this.player);
		player.playSound(player.getLocation(), instrument.getSound(), volume, pitch.getMinecraftPitch());
	}

}
