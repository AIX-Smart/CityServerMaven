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

    @ManyToOne
    private User user;

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



    public Location getLocationId() {
        return location;
    }



    public void setLocationId( Location location ) {
        this.location = location;
    }



    public User getUserId() {
        return user;
    }



    public void setUserId( User user ) {
        this.user = user;
    }



    public int getLikes() {
        return likes;
    }



    public void setLikes( int likes ) {
        this.likes = likes;
    }






}
