package com.github.enderrun;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.enderrun.commands.ERCommandExecutor;
import com.github.enderrun.listeners.LaunchListener;
import com.github.enderrun.listeners.TeleportListener;
import com.github.enderrun.listeners.VoidListener;
import com.github.enderrun.util.GameManager;
import com.github.enderrun.util.LanguageLoader;

public class EnderRun extends JavaPlugin {

    public static EnderRun instance;
    private World gameWorld;
    public LanguageLoader languageLoader;
    
    public GameManager gameManager;

    public void onEnable() {
    	
        try {
			loadLanguageFromFile();
		} catch (IOException e) {
			getLogger().log(Level.SEVERE, "Could not load config: Could not read the file from the filesystem.");
		}
        
        getCommand("enderrun").setExecutor(new ERCommandExecutor());
        
        new VoidListener(this);
        new LaunchListener(this);
        new TeleportListener(this);
        
        this.gameManager = new GameManager(this);
        EnderRun.instance = this;
    }
    
    public void onDisable() {
    	try {
			languageLoader.saveConfig();
		} catch (IOException e) {
			getLogger().log(Level.SEVERE, "Could not save config: Could not write the file to the filesystem.");
		}
    }

    public static EnderRun getInstance() {
        return instance;
    }

    public World getGameWorld() {
        return gameWorld;
    }

    public void loadLanguageFromFile() throws IOException {
        File lang = new File(getDataFolder(), "lang.yml");
        getDataFolder().mkdir();
        lang.createNewFile();
        
        this.languageLoader = new LanguageLoader(lang);
    }
    
    public GameManager getGameManager() {
        return gameManager;
    }
}
