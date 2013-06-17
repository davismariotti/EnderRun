package com.github.enderrun.sound;

public enum EFlatMinorScale {

	E_FLAT(0),
	F(1),
	G_FLAT(2),
	A_FLAT(3),
	B_FLAT(4),
	B(5),
	D_FLAT(6),
	REST(8);
	
	private final int tone;
	
	EFlatMinorScale(int tone) {
		this.tone = tone;
	}
	
	public int getPositionOnScale() {
		return tone;
	}
	
	public static EFlatMinorScale getByPositionOnScale(int tone) {
		for (EFlatMinorScale note : EFlatMinorScale.values()) {
			if (note.getPositionOnScale() == tone) return note;
		}
		return EFlatMinorScale.REST;
	}
	
	public Pitch getAsTone() {
		return Pitch.valueOf(this.name());
	}
	
}
