package com.server.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;

/**
 * Created by jp on 02.11.15.
 */

@NamedQueries( {
        @NamedQuery( name = Post.GETALL, query = "SELECT d FROM Post d ORDER BY d.id DESC" ),
        @NamedQuery( name = Post.GETCITY, query =
                "SELECT d " +
                "FROM Post d JOIN Location l ON d.locationid = l.id " +
                "WHERE d.id < :lastId AND l.stadt = :cityId " +
                "ORDER BY d.id DESC"),
        @NamedQuery( name = Post.GET, query = "SELECT d FROM Post d WHERE d.id = :id" ),
        @NamedQuery( name = Post.GETUSER, query = "SELECT d FROM Post d WHERE d.appuserid = :appuserid ORDER BY d.id DESC" ),
        @NamedQuery( name = Post.GETLOCATION, query = "SELECT d FROM Post d WHERE d.locationid = :locationid ORDER BY d.id DESC" )
} )
@Entity
public class Post {

    public static final String GETALL      = "Post.getAll";
    public static final String GETCITY     = "Post.getCity";
    public static final String GET         = "Post.get";
    public static final String GETUSER     = "Post.getUser";
    public static final String GETLOCATION = "Post.getLocation";


    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;


    @Temporal( TemporalType.DATE )
    private Calendar date;


    private int appuserid;

    private int locationid;

    private int likes;

    private String content;




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







}
