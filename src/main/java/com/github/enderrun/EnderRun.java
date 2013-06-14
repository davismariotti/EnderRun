package com.github.enderrun;

import java.io.File;

import org.bukkit.World.Environment;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.github.enderrun.terraingen.EnderChunkGenerator;

public class EnderRun extends JavaPlugin {

    public void onEnable() {
        getCommand("enderrun").setExecutor(new EnderRunCommand());
        new BukkitRunnable() {

            public void run() {
                if(!new File("enderrun").exists()) {
                    WorldCreator creator = new WorldCreator("enderrun");
                    creator.environment(Environment.THE_END);
                    creator.type(WorldType.NORMAL);
                    creator.generateStructures(false);
                    creator.generator(new EnderChunkGenerator());
                }
            }
        }.runTaskAsynchronously(this);
    }
}
