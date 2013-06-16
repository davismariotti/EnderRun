package com.github.enderrun.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.WorldCreator;
import org.bukkit.command.CommandSender;

import com.github.enderrun.EnderRun;
import com.github.enderrun.language.LocalizedLiteral;
import com.github.enderrun.terraingen.EnderWorldGenerator;

/**
 * Temporary until lobby is set up
 */
public class GenerateCommand implements CommandHandler {

	@Override
	public boolean handle(CommandSender sender, List<String> args) {

		String worldName = EnderRun.getInstance().languageLoader.getValue(LocalizedLiteral.WORLD_NAME);
		WorldCreator worldOptions = new WorldCreator(worldName);
		
		//TODO worldOptions.generator(new VoidChunkGenerator());
		worldOptions.environment(Environment.THE_END);
		worldOptions.generateStructures(false);
		Bukkit.createWorld(worldOptions);
		
		World world = Bukkit.getWorld(worldName);
		EnderWorldGenerator gen = new EnderWorldGenerator(world);
		gen.generateWorld();

		return true;
	}

}
