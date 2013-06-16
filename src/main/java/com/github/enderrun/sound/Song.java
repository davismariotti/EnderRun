package com.github.enderrun.sound;

import java.util.ArrayList;

import com.github.enderrun.EnderRun;

/**
 * Represents a collection of {@code tracks} that are played
 * simultaneously
 * 
 * We drive each track using a repeating task, that calls
 * {@code Track.tick()) once a beat
 */
public class Song {
	
	public static final int TICKS_PER_MINUTE = 20*60;
	
	private int bpm = 60;
	private ArrayList<Track> tracks = new ArrayList<Track>();
	
	private Runnable songLoop =  new Runnable() {
		
		@Override
		public void run() {
			for (Track track : tracks) track.tick();			
		}		
	};
	
	public void play() {
		//TODO Compensate for connection lag and server slowdown...
		long ticksPerBeat = (long) ((float)TICKS_PER_MINUTE/(float)bpm);
		EnderRun.getInstance().getServer().getScheduler().runTaskTimer(EnderRun.getInstance(), songLoop, 0, ticksPerBeat);
	}
	
	public void addTrack(Track track) {
		tracks.add(track);
	}
}
