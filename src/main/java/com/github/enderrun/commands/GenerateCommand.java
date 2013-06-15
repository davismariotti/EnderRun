package com.github.enderrun.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.WorldCreator;
import org.bukkit.command.CommandSender;

import com.github.enderrun.terraingen.EnderWorldGenerator;
import com.github.enderrun.terraingen.VoidChunkGenerator;
import com.github.enderrun.util.Lang;

public class GenerateCommand implements CommandHandler {

	@Override
	public boolean handle(CommandSender sender, List<String> args) {

		WorldCreator worldOptions = new WorldCreator(Lang.WORLD_NAME.getDefault());
		//TODO worldOptions.generator(new VoidChunkGenerator());
		worldOptions.environment(Environment.THE_END);
		Bukkit.createWorld(worldOptions);
		
		World world = Bukkit.getWorld(Lang.WORLD_NAME.getDefault());
		EnderWorldGenerator gen = new EnderWorldGenerator(world);
		gen.startAsyncWorldGeneration();

		return true;
	}

}
