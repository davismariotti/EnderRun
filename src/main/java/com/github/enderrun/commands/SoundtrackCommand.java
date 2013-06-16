package com.github.enderrun.commands;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.enderrun.sound.Instrument;
import com.github.enderrun.sound.Note;
import com.github.enderrun.sound.Pitch;
import com.github.enderrun.sound.PlayerSoundManager;
import com.github.enderrun.sound.Song;
import com.github.enderrun.sound.Track;

//Just a temp
public class SoundtrackCommand implements CommandHandler {

	@Override
	public boolean handle(CommandSender sender, List<String> args) {
		
		Song song = new Song();
		PlayerSoundManager man = new PlayerSoundManager(((Player) sender).getName());
		
		Track bassline = new Track(man);
		bassline.addNote(new Note(4, 1.0f, Pitch.B_FLAT_LOW, Instrument.BASS_GUITAR));
		bassline.addNote(new Note(2, 1.0f, Pitch.C_LOW, Instrument.BASS_GUITAR));
		bassline.addNote(new Note(2, 1.0f, Pitch.E_FLAT_LOW, Instrument.BASS_GUITAR));
		bassline.repeat = true;
		song.addTrack(bassline);
		
		Track melody = new Track(man);
		melody.addNote(new Note(4, 1.0f, Pitch.E_FLAT, Instrument.PIANO));
		melody.addNote(new Note(2, 1.0f, Pitch.D, Instrument.BASS_GUITAR));
		melody.addNote(new Note(2, 1.0f, Pitch.F, Instrument.BASS_GUITAR));
		melody.repeat = false;
		song.addTrack(melody);
		
		song.play();		
		return true;
	}

}
