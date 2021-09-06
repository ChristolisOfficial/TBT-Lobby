package main.event;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import main.Main;

public class MoveListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location locP = player.getLocation();

        if (locP.getY() < -10 && Main.spawnpoint != null)
            player.teleport(Main.spawnpoint);

        Main.portals.forEach(portal -> {
            Location locA = portal.getLocationA();
            Location locB = portal.getLocationB();

            if (AABB(locP, locA, locB)) {
                player.chat(portal.getCmd());
            }
        });
    }

    /**
     * Checks to see if a point is between two locations.
     *
     * @param p The point that is being checked.
     * @param a Location A.
     * @param b Location B.
     * @return Will return true if point P is between the cube
     *  defined by the coordinates A and B.
     */
    private static boolean AABB(Location p, Location a, Location b) {
        // Location A
        double locAX = (a.getX() >= b.getX()) ? b.getX() : a.getX();
        double locAY = (a.getY() >= b.getY()) ? b.getY() : a.getY();
        double locAZ = (a.getZ() >= b.getZ()) ? b.getZ() : a.getZ();

        // Location B
        double locBX = (a.getX() <= b.getX()) ? b.getX() : a.getX();
        double locBY = (a.getY() <= b.getY()) ? b.getY() : a.getY();
        double locBZ = (a.getZ() <= b.getZ()) ? b.getZ() : a.getZ();

        // Point
        double locPX = Math.floor(p.getX());
        double locPY = Math.floor(p.getY());
        double locPZ = Math.floor(p.getZ());

        return ((locPX >= locAX && locPX <= locBX) &&
                (locPY >= locAY && locPY <= locBY) &&
                (locPZ >= locAZ && locPZ <= locBZ)); 
    }
}
