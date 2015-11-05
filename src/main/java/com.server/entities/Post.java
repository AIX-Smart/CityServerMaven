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
        @NamedQuery( name = Post.GET, query = "SELECT d FROM Post d " )
} )
@Entity
public class Post {

    public static final String GET = "Post.get";

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;

    private String content;

    @Temporal( TemporalType.DATE )
    private Calendar date;

    private int appuserId;

    private int locationId;

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



    public int getLocationId() {
        return locationId;
    }



    public void setLocationId( int location ) {
        this.locationId = location;
    }



    public int getUserId() {
        return appuserId;
    }



    public void setUserId( int appuser ) {
        this.appuserId = appuser;
    }



    public int getLikes() {
        return likes;
    }



    public void setLikes( int likes ) {
        this.likes = likes;
    }






}
