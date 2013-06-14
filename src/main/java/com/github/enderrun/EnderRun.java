package com.github.enderrun;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.enderrun.commands.ERCommandExecutor;
import com.github.enderrun.listeners.LaunchListener;
import com.github.enderrun.listeners.TeleportListener;
import com.github.enderrun.listeners.VoidListener;
import com.github.enderrun.util.GameManager;
import com.github.enderrun.util.Lang;

public class EnderRun extends JavaPlugin {

    private static EnderRun instance;
    private World gameWorld;
    public static YamlConfiguration LANG;
    public static File LANG_FILE;
    public static Logger log;
    
    public GameManager manager;

    public void onEnable() {
        instance = this;
        log = getLogger();
        loadLang();
        new VoidListener(this);
        new LaunchListener(this);
        new TeleportListener(this);
        getCommand("enderrun").setExecutor(new ERCommandExecutor());
        manager = new GameManager(this);
        // WorldCreator creator = new WorldCreator("enderrun");
        // log.info(creator.name());
        // creator.environment(Environment.THE_END);
        // creator.type(WorldType.NORMAL);
        // creator.generateStructures(false);
        // creator.generator(new EnderChunkGenerator());
        // gameWorld = creator.createWorld();
        // log.info("Created world.");
    }

    public static EnderRun getInstance() {
        return instance;
    }

    public World getGameWorld() {
        return gameWorld;
    }

    /**
    * Load the lang.yml file.
    */
    public void loadLang() {
        File lang = new File(getDataFolder(), "lang.yml");
        OutputStream out = null;
        InputStream defLangStream = this.getResource("lang.yml");
        if(!lang.exists()) {
            try {
                getDataFolder().mkdir();
                lang.createNewFile();
                if(defLangStream != null) {
                    out = new FileOutputStream(lang);
                    int read = 0;
                    byte[] bytes = new byte[1024];
                    while((read = defLangStream.read(bytes)) != -1) {
                        out.write(bytes, 0, read);
                    }
                    YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defLangStream);
                    Lang.setFile(defConfig);
                    return;
                }
            } catch(IOException e) {
                e.printStackTrace();
                log.severe("Couldn't create language file.");
                log.severe("This is a fatal error. Now disabling");
                this.setEnabled(false);
            } finally {
                if(defLangStream != null) {
                    try {
                        defLangStream.close();
                    } catch(IOException e) {
                        e.printStackTrace();
                    }
                }
                if(out != null) {
                    try {
                        out.close();
                    } catch(IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(lang);
        for(Lang item:Lang.values()) {
            if(conf.getString(item.getPath()) == null) {
                conf.set(item.getPath(), item.getDefault());
            }
        }
        Lang.setFile(conf);
        EnderRun.LANG = conf;
        EnderRun.LANG_FILE = lang;
        try {
            conf.save(getLangFile());
        } catch(IOException e) {
            log.log(Level.WARNING, "PlayerVaults: Failed to save lang.yml.");
            log.log(Level.WARNING, "PlayerVaults: Report this stack trace to drtshock and gomeow.");
            e.printStackTrace();
        }
    }

    /**
     * Gets the lang.yml config.
     * @return The lang.yml config.
     */
    public YamlConfiguration getLang() {
        return LANG;
    }

    /**
     * Get the lang.yml file.
     * @return The lang.yml file.
     */
    public File getLangFile() {
        return LANG_FILE;
    }
    
    public GameManager getGameManager() {
        return manager;
    }
}
