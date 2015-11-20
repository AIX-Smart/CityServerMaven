package com.server.entities;

import com.server.datatype.*;

import javax.persistence.*;
import java.util.List;

/**
 * Created by jp on 02.11.15.
 */
@NamedQueries( {
        @NamedQuery( name = AppUser.GET, query = "SELECT u FROM AppUser u WHERE u.id = :id" ),
        @NamedQuery( name = AppUser.GETBYDEVICEID, query = "SELECT u FROM AppUser u WHERE u.deviceId = :deviceId" ),
        @NamedQuery( name = AppUser.GETALL, query = "SELECT u FROM AppUser u")
} )
@Entity
public class AppUser {

    public static final String GET    = "AppUser.get";
    public static final String GETBYDEVICEID = "AppUser.getByDeviceId";
    public static final String GETALL = "AppUser.getAll";


    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;

    private String deviceId;

    private int permission;

    private int businessId;

    @OneToMany
    private List<Event> likedEvents;

    @OneToMany
    private List<Comment> likedComments;

    @OneToMany
    private List<Location> likedLocations;

    public int getId() {
        return id;
    }



    public void setId( int userId ) {
        this.id = userId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public int getPermission() {
        return permission;
    }



    public void setPermission( int permission ) {
        this.permission = permission;
    }



    public long getBusinessId() {
        return businessId;
    }



    public void setBusinessId( int businessId ) {
        this.businessId = businessId;
    }

    public List<Event> getLikedEvents() {
        return likedEvents;
    }

    public void setLikedEvents(List<Event> likedEvents) {
        this.likedEvents = likedEvents;
    }

    public List<Comment> getLikedComments() {
        return likedComments;
    }

    public void setLikedComments(List<Comment> likedComments) {
        this.likedComments = likedComments;
    }

    public List<Location> getLikedLocations() {
        return likedLocations;
    }

    public void setLikedLocations(List<Location> likedLocations) {
        this.likedLocations = likedLocations;
    }
}
