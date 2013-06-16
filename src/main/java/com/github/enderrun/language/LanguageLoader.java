package com.github.enderrun.language;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

/**
 * An enum for requesting strings from the language file.
 */
public class LanguageLoader {
	
    private YamlConfiguration storedConfig;
    private File configFile;
    
    public LanguageLoader(File file) {
    	this.configFile = file;
    	this.storedConfig = YamlConfiguration.loadConfiguration(this.configFile);
    }
    
    public String getValue(LocalizedLiteral literal) {
    	String valueFromConfig = storedConfig.getString(literal.getPath());
    	
    	if (valueFromConfig != null && !valueFromConfig.equalsIgnoreCase(" ")) return valueFromConfig;
    	else {
    		storedConfig.set(literal.getPath(), literal.getDefaultValue());
    		return literal.getDefaultValue();
    	}
    }

	public void saveConfig() throws IOException {
		this.storedConfig.save(this.configFile);		
	}
}
