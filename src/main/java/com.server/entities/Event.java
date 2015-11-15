package com.server.entities;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Created by jp on 02.11.15.
 */

@NamedQueries( {
        @NamedQuery( name = Event.GETALL, query = "SELECT d FROM Event d ORDER BY d.id DESC" ),
        @NamedQuery( name = Event.GETCITY, query =
                "SELECT d " +
                "FROM Event d JOIN d.location lc " +
                "WHERE d.id < :lastId AND lc.cityId = :cityId " +
                "ORDER BY d.id DESC"),
        @NamedQuery( name = Event.GET, query = "SELECT d FROM Event d WHERE d.id = :id" ),
        @NamedQuery( name = Event.GETUSER, query = "SELECT d FROM Event d WHERE d.appuserid = :appuserid ORDER BY d.id DESC" ),
        @NamedQuery( name = Event.GETLOCATION, query = "SELECT d FROM Event d WHERE d.locationid = :locationid ORDER BY d.id DESC" )
} )
@Entity
public class Event {

    public static final String GETALL      = "Event.getAll";
    public static final String GETCITY     = "Event.getCity";
    public static final String GET         = "Event.get";
    public static final String GETUSER     = "Event.getUser";
    public static final String GETLOCATION = "Event.getLocation";


    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;


    @Temporal( TemporalType.DATE )
    private Calendar date;


    private int appuserid;

    private int locationid;

    private int likes;

    private String content;

    @ManyToOne(optional=false)
    private Location location;




    public int getId() {
        return id;
    }



    public void setId( int postId ) {
        this.id = postId;
    }



    public String getContent() {
        return content;
    }



    public void setContent( String content ) {
        this.content = content;
    }



    public Calendar getDate() {
        return date;
    }



    public void setDate( Calendar date ) {
        this.date = date;
    }










    public int getLikes() {
        return likes;
    }



    public void setLikes( int likes ) {
        this.likes = likes;
    }

    public int getAppuserid() {
        return appuserid;
    }

    public void setAppuserid(int appuserid) {
        this.appuserid = appuserid;
    }

    public int getLocationid() {
        return locationid;
    }

    public void setLocationid(int locationid) {
        this.locationid = locationid;
    }


    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
