package com.github.enderrun.commands;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.enderrun.sound.SoundTrack;

//Just a temp
public class SoundtrackCommand implements CommandHandler {

	@Override
	public boolean handle(CommandSender sender, List<String> args) {
		
		new SoundTrack(((Player) sender).getName(), 60).play();
		
		return true;
	}

}
