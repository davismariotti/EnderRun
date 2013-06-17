package com.github.enderrun.sound;

public enum BFlatMajorScale {

	B_FLAT_LOW(0),
	C(1),
	D(2),
	E_FLAT(3),
	F(4),
	G(5),
	A(6),
	B_FLAT(7),
	REST(8);
	
	private final int tone;
	
	BFlatMajorScale(int tone) {
		this.tone = tone;
	}
	
	public int getPositionOnScale() {
		return tone;
	}
	
	public static BFlatMajorScale getByPositionOnScale(int tone) {
		for (BFlatMajorScale note : BFlatMajorScale.values()) {
			if (note.getPositionOnScale() == tone) return note;
		}
		return BFlatMajorScale.REST;
	}
	
	public Pitch getAsTone() {
		return Pitch.valueOf(this.name());
	}
	
}
