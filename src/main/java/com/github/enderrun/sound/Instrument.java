package com.github.enderrun.sound;

import org.bukkit.Sound;

public enum Instrument {
	
	PIANO(Sound.NOTE_PIANO),
	BASS(Sound.NOTE_BASS),
	BASS_DRUM(Sound.NOTE_BASS_DRUM),
	BASS_GUITAR(Sound.NOTE_BASS_GUITAR),
	PLING(Sound.NOTE_PLING),
	SNARE_DRUM(Sound.NOTE_SNARE_DRUM),
	STICKS(Sound.NOTE_STICKS);
	
	private Sound sound;
	
	Instrument(Sound sound) {
		this.sound = sound;
	}

	public Sound getSound() {
		return this.sound;
	}

}
