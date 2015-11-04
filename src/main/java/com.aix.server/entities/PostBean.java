package com.aix.server.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.sql.Date;

/**
 * Created by jp on 02.11.15.
 */

@NamedQueries( {
        @NamedQuery( name = PostBean.GET, query = "SELECT d FROM Post d " ),
} )
@Entity
public class PostBean {

    public static final String GET = "Post.get";

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int postId;

    private String content;

    private Date date;

    private long userId;

    private long locationId;

    private int likes;




    public int getPostId() {
        return postId;
    }



    public void setPostId( int postId ) {
        this.postId = postId;
    }



    public String getContent() {
        return content;
    }



    public void setContent( String content ) {
        this.content = content;
    }



    public Date getDate() {
        return date;
    }



    public void setDate( Date date ) {
        this.date = date;
    }



    public long getLocationId() {
        return locationId;
    }



    public void setLocationId( long locationId ) {
        this.locationId = locationId;
    }



    public long getUserId() {
        return userId;
    }



    public void setUserId( long userId ) {
        this.userId = userId;
    }



    public int getLikes() {
        return likes;
    }



    public void setLikes( int likes ) {
        this.likes = likes;
    }






}
