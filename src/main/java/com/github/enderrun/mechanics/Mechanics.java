package com.github.enderrun.mechanics;

import com.github.enderrun.EnderRun;

public class Mechanics {

	public static void loadMechanics(EnderRun enderRun) {
        new VoidListener(enderRun);
        new LaunchListener(enderRun);
        new TeleportListener(enderRun);		
	}

}
