package com.github.enderrun;

import org.bukkit.Location;

public class GamePlayer {

	public Location previousLocation = null;
	public final String name;

	public GamePlayer(String playerName) {
		name = playerName;
	}

}
