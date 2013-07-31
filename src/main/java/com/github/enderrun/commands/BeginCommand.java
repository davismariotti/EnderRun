package com.github.enderrun.commands;

import java.util.List;

import org.bukkit.command.CommandSender;

import com.github.enderrun.Game;
import com.github.enderrun.GameManager;

public class BeginCommand implements CommandHandler {

	@Override
	public boolean handle(CommandSender sender, List<String> args) {
		if (GameManager.getInstance().currentGame.state == Game.GameStates.GAME_WAITING_FOR_PLAYERS) {
			GameManager.getInstance().currentGame.begin();
			return true;
		}
		
		return false;
	}	
}
