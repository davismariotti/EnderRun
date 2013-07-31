package com.github.enderrun;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.github.enderrun.language.LocalizedLiteral;
import com.github.enderrun.terraingen.EnderWorldGenerator;

public class Game {
	
	public volatile GameStates state = GameStates.GAME_WAITING_FOR_PLAYERS;	
	public volatile HashMap<String, GamePlayer> playersInGame = new HashMap<String, GamePlayer>();
	public volatile HashMap<String, GamePlayer> playersOutOfGame = new HashMap<String, GamePlayer>();
	private EnderWorldGenerator gen;
	public String winner = "nobody";
	
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
			playersInGame.put(playerName, new GamePlayer(playerName));
		
		else assert false : "Tried to add players to a game that wasn't accepting them";
	}

	public void finish() {
		state = GameStates.GAME_FINISHED;
		notifyPlayersOfFinish();
	}

	private void notifyPlayersOfFinish() {
		Bukkit.broadcastMessage(String.format(
					EnderRun.getInstance().languageLoader.getValue(LocalizedLiteral.GAME_FINISHED),
					winner));		
	}

	public void begin() {
		state = GameStates.GAME_RUNNING;
		for (String player : playersInGame.keySet()) {
			Player bukkitPlayer = Bukkit.getPlayer(player);
			GamePlayer gamePlayer = playersInGame.get(player);
			gamePlayer.previousLocation = bukkitPlayer.getLocation();
			
			bukkitPlayer.teleport(new Location(gen.world, 1.5, 68, 1.5));
			bukkitPlayer.sendMessage(EnderRun.getInstance().languageLoader.getValue(LocalizedLiteral.GAME_BEGUN));
		}
	}

	public void removePlayer(String name) {
		GamePlayer gamePlayer = playersInGame.get(name);
		playersOutOfGame.put(name, gamePlayer);
		playersInGame.remove(name);
		
		Bukkit.getPlayer(name).teleport(gamePlayer.previousLocation);
		if (playersInGame.isEmpty()) {
			finish();
		}
	}

}
