package com.github.enderrun;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.enderrun.commands.ERCommandExecutor;
import com.github.enderrun.language.LanguageLoader;
import com.github.enderrun.language.LocalizedLiteral;
import com.github.enderrun.mechanics.Mechanics;
import com.github.enderrun.terraingen.VoidChunkGenerator;

public class EnderRun extends JavaPlugin {

    private static EnderRun instance;
    public LanguageLoader languageLoader;
    public World gameWorld;
    
    public void onEnable() {
    	
        try {
			loadLanguageFromFile();
		} catch (IOException e) {
			getLogger().log(Level.SEVERE, "Could not load config: Could not read the file from the filesystem.");
		}
        
    	WorldCreator wc = new WorldCreator(languageLoader.getValue(LocalizedLiteral.WORLD_NAME));
    	wc.environment(Environment.THE_END);
    	wc.generator(new VoidChunkGenerator());
        this.gameWorld = Bukkit.createWorld(wc);
        
        getCommand("enderrun").setExecutor(new ERCommandExecutor());        
        Mechanics.loadMechanics(this);        
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

    public void loadLanguageFromFile() throws IOException {
        File lang = new File(getDataFolder(), "lang.yml");
        getDataFolder().mkdir();
        lang.createNewFile();
        
        this.languageLoader = new LanguageLoader(lang);
    }
}
