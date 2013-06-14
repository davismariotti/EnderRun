package com.github.enderrun.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.github.enderrun.terraingen.EnderChunkGenerator;

public class GenerateWorldCommandHandler implements CommandHandler {
	
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		
		EnderChunkGenerator.createNewEnderRunWorld();		
		return true;
	}

}
