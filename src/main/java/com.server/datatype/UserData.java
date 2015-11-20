package com.server.datatype;

import com.server.entities.AppUser;

import java.util.List;

/**
 * Created by jp on 15.11.2015.
 */
public class UserData {
    private User user;
    private int permission;
    private List<Location> ownLocations;

    public UserData(AppUser user){
        this.user = new User(user.getId());
        this.permission = user.getPermission();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Location> getOwnLocations() {
        return ownLocations;
    }

    public void setOwnLocations(List<Location> ownLocations) {
        this.ownLocations = ownLocations;
    }
}
