package com.github.enderrun.sound;

import java.util.ArrayList;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

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
	
	public int bpm = 60;
	private ArrayList<Track> tracks = new ArrayList<Track>();
	private BukkitTask songLoopTask;
	
	private Runnable songLoop =  new Runnable() {
		
		@Override
		public void run() {
			for (int i = 0; i < tracks.size(); i++) {
				Track track = tracks.get(i);
				
				if (track.finished) {
					tracks.remove(track);
				}
				if (tracks.size() == 0) {
					Bukkit.broadcastMessage("Done playing song");
					songLoopTask.cancel();
				}
				try {
					track.tick();
				} catch (PlayerLeftException e) {
					EnderRun.getInstance().getLogger().log(Level.INFO, "Player left while playing song");
					tracks.clear();
					songLoopTask.cancel();
				}		
			}
		}		
	};
	
	public Song(int bpm) {
		this.bpm = bpm;
	}
	
	public void play() {
		EnderRun.getInstance().getLogger().log(Level.INFO, "Playing song");
		
		//TODO Compensate for connection lag and server slowdown...
		long ticksPerBeat = (long) ((float)TICKS_PER_MINUTE/(float)bpm);
		this.songLoopTask = EnderRun.getInstance().getServer().getScheduler()
				.runTaskTimer(EnderRun.getInstance(), songLoop, 0, ticksPerBeat);
	}
	
	public void addTrack(Track track) {
		tracks.add(track);
	}
}
