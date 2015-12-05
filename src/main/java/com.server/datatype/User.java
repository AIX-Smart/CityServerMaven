package com.server.datatype;

import com.server.entities.AppUser;

import java.util.List;

/**
 * Created by jp on 15.11.2015.
 */
public class User {
    private int id;
    private int permission;
    private List<Location> ownLocations;

    public User(AppUser user){
        this.id = user.getId();
        this.permission = user.getPermission();
    }

    public User(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public List<Location> getOwnLocations() {
        return ownLocations;
    }

    public void setOwnLocations(List<Location> ownLocations) {
        this.ownLocations = ownLocations;
    }
}
