package com.github.enderrun.sound;

public enum BFlatScale {

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
	
	BFlatScale(int tone) {
		this.tone = tone;
	}
	
	public int getPositionOnScale() {
		return tone;
	}
	
	public static BFlatScale getByPositionOnScale(int tone) {
		for (BFlatScale note : BFlatScale.values()) {
			if (note.getPositionOnScale() == tone) return note;
		}
		return BFlatScale.REST;
	}
	
	public Pitch getAsTone() {
		return Pitch.valueOf(this.name());
	}
	
}
