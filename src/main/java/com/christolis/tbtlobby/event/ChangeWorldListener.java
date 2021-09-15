package com.christolis.tbtlobby.event;

import com.christolis.tbtlobby.Main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class ChangeWorldListener implements Listener {

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();

        if (Main.isPlayerInLobby(player)) {
            Main.spawnPlayer(player);
        }
    }
}
