package main.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractListener implements Listener {

    @EventHandler
    public void onInteractEvent(PlayerInteractEvent event) {
        if (event.getAction() == Action.PHYSICAL)
            event.setCancelled(true);
    }
}
