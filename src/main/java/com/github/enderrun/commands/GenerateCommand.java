package com.github.enderrun.commands;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.enderrun.EnderRun;
import com.github.enderrun.terraingen.EnderWorldGenerator;

public class GenerateCommand implements CommandHandler{

	@Override
	public boolean handle(CommandSender sender, List<String> args) {
		EnderWorldGenerator gen = new EnderWorldGenerator(EnderRun.getInstance().gameWorld);
		gen.generateWorld();
		
		((Player) sender).teleport(new Location(EnderRun.getInstance().gameWorld, 0, 68, 0));
		
		return true;
	}

}
