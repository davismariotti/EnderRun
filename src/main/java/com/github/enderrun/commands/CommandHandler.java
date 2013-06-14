package com.github.enderrun.commands;

import java.util.List;

import org.bukkit.command.CommandSender;

/**
 * Represents an object that is responsible for executing
 * Ender Run sub-commands (i.e. /enderrun generate)
 *
 */
public interface CommandHandler {

    public boolean handle(CommandSender sender, List<String> args);

}
