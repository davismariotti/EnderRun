package com.github.enderrun.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.github.enderrun.terraingen.EnderChunkGenerator;

public class GenerateWorldCommandExecutor implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		
		EnderChunkGenerator.createNewEnderRunWorld();		
		return true;
	}

}
