package main.event;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import main.Main;

public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (Main.spawnpoint != null) {
            player.teleport(Main.spawnpoint);
        }

        player.setGameMode(GameMode.ADVENTURE);
        player.getInventory().clear();
        player.setHealth(20D);
        player.setFoodLevel(20);
        player.getInventory().setHeldItemSlot(0);
    }
}
