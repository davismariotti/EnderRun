package com.github.enderrun;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import com.github.enderrun.language.LocalizedLiteral;
import com.github.enderrun.terraingen.EnderWorldGenerator;

public class Game {
	
	public volatile GameStates state = GameStates.GAME_WAITING_FOR_PLAYERS;	
	public volatile HashMap<String, GamePlayer> players = new HashMap<String, GamePlayer>();
	private EnderWorldGenerator gen;
	
	public Game() {
		gen = new EnderWorldGenerator(EnderRun.getInstance().gameWorld);
		gen.generateWorld();
	}
	
	public enum GameStates {
		GAME_WAITING_FOR_PLAYERS,
		GAME_RUNNING,
		GAME_FINISHED;
	}
	
	public void addPlayerToLobby(String playerName) {
		if (state == GameStates.GAME_WAITING_FOR_PLAYERS)
			players.put(playerName, new GamePlayer(playerName));
		
		else assert false : "Tried to add players to a game that wasn't accepting them";
	}

	public void finish() {
		state = GameStates.GAME_FINISHED;
		notifyPlayersOfFinish();
	}

	private void notifyPlayersOfFinish() {
		for (String player : players.keySet()) {
			Bukkit.getPlayer(player).sendMessage(EnderRun.getInstance().languageLoader.getValue(LocalizedLiteral.GAME_FINISHED));
		}		
	}

	public void begin() {
		state = GameStates.GAME_RUNNING;
		for (String player : players.keySet()) {
			Bukkit.getPlayer(player).teleport(new Location(gen.world, 1.5, 68, 1.5));
			Bukkit.getPlayer(player).sendMessage(EnderRun.getInstance().languageLoader.getValue(LocalizedLiteral.GAME_BEGUN));
		}
	}

}
