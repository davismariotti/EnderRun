package com.github.enderrun;

import org.bukkit.plugin.java.JavaPlugin;

import com.github.enderrun.commands.EnderRunCommandExecutor;

public class EnderRun extends JavaPlugin {

    public void onEnable() {
        getCommand("enderrun").setExecutor(new EnderRunCommandExecutor());
    }
    
}
