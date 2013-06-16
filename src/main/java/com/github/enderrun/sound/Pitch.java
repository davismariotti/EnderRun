package com.github.enderrun.sound;

import org.bukkit.Bukkit;

public enum Pitch {
	
	G_FLAT_LOW(0,0),
	G_LOW(1,0.53f),
	A_FLAT_LOW(2,0.56f),
	A_LOW(3,0.6f),
	B_FLAT_LOW(4, 0.63f),
	B_LOW(5, 0.67f),
	C_LOW(6, 0.7f),
	D_FLAT_LOW(7, 0.76f),
	D_LOW(8, 0.8f),
	E_FLAT_LOW(9, 0.83f),
	E_LOW(10, 0.9f),
	F_LOW(11, 0.95f),
	G_FLAT(12, 1.0f),
	
	G(13,1.05f),
	A_FLAT(14,1.1f),
	A(15,1.2f),
	B_FLAT(16, 1.25f),
	B(17, 1.34f),
	C(18, 1.4f),
	D_FLAT(19, 1.5f),
	D(20, 1.6f),
	E_FLAT(21, 1.67f),
	E(22, 1.78f),
	F(23, 1.88f),
	G_FLAT_HIGH(24, 2.0f);
	
	private int semitone;
	private float minecraftPitch;
	
	Pitch(int semitone, float minecraftPitch) {
		this.semitone = semitone;
		this.minecraftPitch = minecraftPitch;
	}

	public float getMinecraftPitch() {
		return this.minecraftPitch;
	}
	
	public int getSemitone() {
		return this.semitone;
	}
	
	public static Pitch getPitchBySemitone(int semitone) {
		for (Pitch p : Pitch.values()) {
			if (p.getSemitone() == semitone) return p;
		}
		return Pitch.G_FLAT_LOW;
	}

}
