package com.server.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;

/**
 * Created by jp on 02.11.15.
 */

@NamedQueries( {
        @NamedQuery( name = Post.GETALL, query = "SELECT d FROM Post d " ),
        @NamedQuery( name = Post.GET, query = "SELECT d FROM Post d WHERE d.id = :id" ),
        @NamedQuery( name = Post.GETUSER, query = "SELECT d FROM Post d WHERE d.appuser = :appuserId" ),
        @NamedQuery( name = Post.GETLOCATION, query = "SELECT d FROM Post d WHERE d.location = :locationId" )
} )
@Entity
public class Post {

    public static final String GETALL      = "Post.getAll";
    public static final String GET         = "Post.get";
    public static final String GETUSER     = "Post.getUser";
    public static final String GETLOCATION = "Post.getLocation";


    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;

    private String content;

    @Temporal( TemporalType.DATE )
    private Calendar date;

    private int appuser;

    @ManyToOne
    private Location location;

    private int likes;



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



    public Location getLocation() {
        return location;
    }



    public void setLocation( Location location ) {
        this.location = location;
    }



    public int getUser() {
        return appuser;
    }



    public void setUser(int appuser ) {
        this.appuser = appuser;
    }



    public int getLikes() {
        return likes;
    }



    public void setLikes( int likes ) {
        this.likes = likes;
    }






}
