package info.gomeow.enderrun;

import org.bukkit.plugin.java.JavaPlugin;

import com.hawkfalcon.enderrun.Hawky;

public class EnderRun extends JavaPlugin {

    public void onEnable() {
        new Hawky(this);
    }
    
}
