package com.github.enderrun.commands;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.enderrun.EnderRun;
import com.github.enderrun.Game;
import com.github.enderrun.GameManager;
import com.github.enderrun.language.LocalizedLiteral;

public class JoinCommand implements CommandHandler {

	public boolean handle(CommandSender sender, List<String> args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;

			if (GameManager.getInstance().currentGame.state == Game.GameStates.GAME_FINISHED) {
				GameManager.getInstance().startNewGame();
			}
			
			if (GameManager.getInstance().currentGame.state == Game.GameStates.GAME_RUNNING) {
				player.sendMessage(EnderRun.getInstance().languageLoader.getValue(LocalizedLiteral.GAME_IN_PROGRESS));
			}
			
			else if (GameManager.getInstance().currentGame.state == Game.GameStates.GAME_WAITING_FOR_PLAYERS) {
				GameManager.getInstance().currentGame.addPlayerToLobby(player.getName());
				player.sendMessage(EnderRun.getInstance().languageLoader.getValue(LocalizedLiteral.JOINED_GAME));
			}
		} 
		
		else {
			sender.sendMessage(EnderRun.getInstance().languageLoader.getValue(LocalizedLiteral.TITLE)
					+ " "
					+ EnderRun.getInstance().languageLoader.getValue(LocalizedLiteral.MUST_BE_PLAYER));
		}
		return true;
	}

}
