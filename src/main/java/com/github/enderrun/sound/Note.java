package com.github.enderrun.sound;

public class Note {

	private final int duration;
	private final float volume;
	private final Pitch pitch;
	private final Instrument instrument;
	
	public Note(int duration, float volume, Pitch pitch, Instrument instrument) {
		this.duration = duration;
		this.volume = volume;
		this.pitch = pitch;
		this.instrument = instrument;
	}

	public int getDuration() {
		return duration;
	}

	public float getVolume() {
		return volume;
	}

	public Pitch getPitch() {
		return pitch;
	}

	public Instrument getInstrument() {
		return instrument;
	}
	
}
