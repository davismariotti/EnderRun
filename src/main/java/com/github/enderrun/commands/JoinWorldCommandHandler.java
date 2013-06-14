package com.github.enderrun.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.enderrun.LocalizedStrings;
import com.github.enderrun.terraingen.EnderChunkGenerator;

public class JoinWorldCommandHandler implements CommandHandler {

	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
				
		if (!(sender instanceof Player)) {
			sender.sendMessage(LocalizedStrings.MUST_BE_PLAYER_MESSAGE);
			return true;
		}
		
		World world = Bukkit.getWorld(LocalizedStrings.GENERATED_WORLD_NAME);
		if (world==null) EnderChunkGenerator.createNewEnderRunWorld();
		
		((Player)sender).teleport(new Location(world,0,255,0));
		return true;
	}

}
