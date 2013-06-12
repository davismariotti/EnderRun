package com.hawkfalcon.enderrun;

import org.bukkit.generator.ChunkGenerator;

import info.gomeow.enderrun.EnderRun;

public class Hawky {

    public Hawky(EnderRun er) {
    }
        
        /**
         * 
         * @param worldName
         * The name of the world the generator is being applied to
         * @param GenId
         * The id (if any) specified by the user. It can be used if the plugin
         * wants to have multiple generators in one plugin. More on this later.
         * @return
         * The ChunkGenerator that this plugin provides
         */
        public ChunkGenerator getDefaultWorldGenerator(String worldName, String GenId) {
            return new com.hawkfalcon.enderrun.BasicChunkGenerator();
        }
        
    
}
