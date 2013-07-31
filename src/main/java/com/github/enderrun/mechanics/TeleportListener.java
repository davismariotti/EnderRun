package com.github.enderrun.mechanics;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import com.github.enderrun.EnderRun;
import com.github.enderrun.GameManager;

public class TeleportListener implements Listener {

    EnderRun plugin;

    public TeleportListener(EnderRun er) {
        this.plugin = er;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onTp(PlayerTeleportEvent event) {
        if(event.getCause() == TeleportCause.ENDER_PEARL) {
            Player player = event.getPlayer();
            if(GameManager.getInstance().currentGame.players.containsKey(player.getName())) {
                event.setCancelled(true);
                player.teleport(event.getTo());
                player.setHealth(player.getHealth() - 1);
            }
        }
    }
}
