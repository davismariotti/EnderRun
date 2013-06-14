package com.github.enderrun.listeners;

import org.bukkit.Material;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;

import com.github.enderrun.EnderRun;

public class LaunchListener implements Listener {

    EnderRun plugin;

    public LaunchListener(EnderRun er) {
        this.plugin = er;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onLaunch(ProjectileLaunchEvent event) {
        if(event.getEntity() instanceof EnderPearl) {
            if(event.getEntity().getShooter() instanceof Player) {
                Player player = (Player) event.getEntity().getShooter();
                if(plugin.game.contains(player.getName())) {
                    player.getInventory().addItem(new ItemStack(Material.ENDER_PEARL, 1));
                }
            }
        }
    }
}
