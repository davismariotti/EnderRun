package com.github.enderrun.sound;

import java.util.ArrayList;

public class SongGenerator {

	public static Song generate(String playername, int length) {
		Song song = new Song();
		PlayerSoundManager man = new PlayerSoundManager(playername);
		
		Track[] tracks = new Track[30];
		
		CellularAutomataGenerator gen = new CellularAutomataGenerator(System.currentTimeMillis(), 19, 19, 40);
		
		for (int x = 0; x < length; x++) {
			ArrayList<Integer> notes = gen.get();
			
			for (int i=0; i < tracks.length; i++){
				Pitch pitch;
				Instrument instrument = i > 1? Instrument.BASS_GUITAR : Instrument.PIANO;
				if (i >= notes.size()) {
					pitch = Pitch.REST;
				}
				else {
					pitch = BFlatScale.getByPositionOnScale(notes.get(i)).getAsTone();
				}
				if (tracks[i] == null) tracks[i] = new Track(man);				
				tracks[i].addNote(new Note(i, 1.0f, pitch, instrument));
			}
		}
		
		for (Track track : tracks) {
			if (track != null) song.addTrack(track);
		}
		
		song.bpm = 150;
		return song;
	}
	
}
