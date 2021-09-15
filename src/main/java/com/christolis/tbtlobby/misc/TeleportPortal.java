package com.christolis.tbtlobby.misc;

import org.bukkit.Location;

/**
 * TeleportPortal.java
 * An entity that holds all in-game interactive portals.
 */
public class TeleportPortal {
    private String cmd;
    private Location la;
    private Location lb;
    
    public TeleportPortal(String cmd, Location la, Location lb) {
        this.cmd = cmd;
        this.la = la;
        this.lb = lb;
    }

    public String getCmd() {
        return cmd;
    }

    public Location getLocationA() {
        return la;
    }

    public Location getLocationB() {
        return lb;
    }
}
