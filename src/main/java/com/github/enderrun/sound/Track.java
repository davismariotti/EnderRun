package com.github.enderrun.sound;

import java.util.HashMap;

/**
 * Represents a collection of notes played sequentially;
 */
public class Track {

	private HashMap<Integer,Note> notes = new HashMap<Integer,Note>();
	private int beginningOfNextNote = 0, currentBeat = 0, currentAddedNoteEnd = 0;
	private PlayerSoundManager soundManager;
	
	public boolean repeat = false, finished = false;
	
	public Track(PlayerSoundManager soundManager) {
		this.soundManager = soundManager;
	}
	
	public void addNote(Note note) {
		notes.put(currentAddedNoteEnd, note);
		currentAddedNoteEnd += note.getDuration();
	}
	
	public void tick() throws PlayerLeftException {
		soundManager.play(getCurrentNote());
		currentBeat++;
	}

	private Note getCurrentNote() {
		if (beginningOfNextNote >= currentAddedNoteEnd) {
			if (repeat) {
				currentBeat = 0;
				beginningOfNextNote = 0;
				return getCurrentNote();
			}
			else {
				finished = true;
				return null;
			}
		}
		else if (beginningOfNextNote == currentBeat) {
			Note currentNote = notes.get(currentBeat);
			beginningOfNextNote = currentBeat + currentNote.getDuration();
			return currentNote;
		}
		
		else return null;
	}
	
}
