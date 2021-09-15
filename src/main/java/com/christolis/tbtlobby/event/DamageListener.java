package com.christolis.tbtlobby.event;

import com.christolis.tbtlobby.Main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            if (Main.isPlayerInLobby(player)) {
                event.setCancelled(true);
            }
        }
    }
}
