package info.gomeow.enderrun;

import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

import com.hawkfalcon.enderrun.Hawky;

public class EnderRun extends JavaPlugin {

    public void onEnable() {
        new Hawky(this);
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
