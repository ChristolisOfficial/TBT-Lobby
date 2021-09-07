package main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import main.event.*;
import main.misc.TeleportPortal;

/**
 * Main.java
 */
public class Main extends JavaPlugin implements Listener {

    public static FileConfiguration config;
    public static Main instance;
    public static List<TeleportPortal> portals = new ArrayList<>();
    public static Location spawnpoint;

    private final Set<Listener> listeners = new HashSet<>();
    {
        listeners.add(new MoveListener());
        listeners.add(new JoinListener());
        listeners.add(new LeaveListener());
        listeners.add(new DamageListener());
        listeners.add(new HungerListener());
        listeners.add(new InteractListener());
        listeners.add(new ItemDropListener());
        listeners.add(new ChangeWorldListener());
    }
    
    /**
     * Called when the plugin gets enabled by the server.
     */
    @Override
    public void onEnable() {
        listeners.forEach(l -> {
            Bukkit.getPluginManager().registerEvents(l, this);
        });

        instance = this;

        portals.clear();
        if (config == null) {
            initConfig();
        }
    }

    /**
     * Should be called to initialize the main plugin config.
     */
    private void initConfig() {
        this.saveDefaultConfig();
        config = this.getConfig();

        // Read portals
        try {
            ConfigurationSection portals = config.getConfigurationSection("portals");
            if (portals != null) { 
                for (String it : portals.getKeys(false)) {
                    String cmd = config.getString("portals." + it + ".cmd");
                    Location locA = parseLocation(
                            config.getString("portals." + it + ".la"), false);
                    Location locB = parseLocation(
                            config.getString("portals." + it + ".lb"), false);

                    Main.portals.add(new TeleportPortal(cmd, locA, locB));
                }
            } else {
               throw new Exception("Failed to read portals!"); 
            }
        } catch (Exception e) {
            instance.getLogger().log(Level.SEVERE, e.getMessage());
            portals.clear();
        }

        // Read spawnpoint
        try {
            Main.spawnpoint = parseLocation(config.getString("spawnpoint"), true)
                .add(0.5D, 0D, 0.5D);
        } catch (Exception e) {
            instance.getLogger().log(Level.SEVERE, "Failed to read spawnpoint!");
        }


        instance.getLogger().log(Level.INFO, "Config initialized! (" +
                Main.portals.size() + " portals)");
    }

    /**
     * Parses any given location in string notation and
     * returns a new Location instance from it.
     *
     * @param str The input location.
     * @param rot Should be set to true if head rotation fields are to be written.
     */
    public static Location parseLocation(String str, boolean rot) {
        String[] locRaw = str.split(" ", 5);
        double[] locCoords = new double[5];

        for (int i = 0; i < ((rot) ? 5 : 3); i++) {
            locCoords[i] = Double.parseDouble(locRaw[i]);
        } 

        if (rot) {
            return new Location(Bukkit.getWorld("lobby"),
                    locCoords[0], locCoords[1], locCoords[2],
                    (float) locCoords[3], (float) locCoords[4]);
        } else {
            return new Location(Bukkit.getWorld("lobby"),
                    locCoords[0], locCoords[1], locCoords[2]);
        }
    }

    public static boolean isPlayerInLobby(Player player) {
        return player.getWorld().getName().equals("lobby");
    }

    public static void spawnPlayer(Player player) {
        if (Main.spawnpoint != null)
            player.teleport(Main.spawnpoint);

        player.setGameMode(GameMode.ADVENTURE);
        player.getInventory().clear();
        player.setHealth(20D);
        player.setFoodLevel(20);
        player.getInventory().setHeldItemSlot(0);
    }
}
