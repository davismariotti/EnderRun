package com.github.enderrun.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * Represents an object that is responsible for executing
 * Ender Run sub-commands (i.e. /enderrun generate)
 *
 */
public interface CommandHandler {

	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args);
	
}
