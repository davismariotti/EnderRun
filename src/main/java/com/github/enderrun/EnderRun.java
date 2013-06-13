package com.github.enderrun;

import org.bukkit.plugin.java.JavaPlugin;

import com.github.enderrun.commands.*;

public class EnderRun extends JavaPlugin {

    public void onEnable() {
        getCommand("endergen").setExecutor(new GenerateWorldCommandExecutor());
        getCommand("joinender").setExecutor(new JoinWorldCommandExecutor());
    }
    
}
