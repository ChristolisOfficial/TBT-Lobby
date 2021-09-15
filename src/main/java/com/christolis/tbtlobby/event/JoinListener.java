package com.christolis.tbtlobby.event;

import com.christolis.tbtlobby.Main;
import com.google.gson.JsonObject;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.labymod.serverapi.bukkit.LabyModPlugin;

public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Main.spawnPlayer(player);
        event.setJoinMessage(null);

        JsonObject obj = new JsonObject();

        obj.addProperty("hasGame", true);
        obj.addProperty("game_mode", "Christolis' Private TB Training");
        obj.addProperty("game_startTime", System.currentTimeMillis());
        obj.addProperty("game_endTime", 0);

        LabyModPlugin.getInstance().sendServerMessage(player, "discord_rpc", obj);
    }
}
