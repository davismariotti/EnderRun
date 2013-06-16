package com.github.enderrun.commands;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.enderrun.sound.SongGenerator;

//Just a temp
public class SoundtrackCommand implements CommandHandler {

	@Override
	public boolean handle(CommandSender sender, List<String> args) {
		
		SongGenerator.generate(((Player)sender).getName(), 20).play();
		return true;
	}

}
