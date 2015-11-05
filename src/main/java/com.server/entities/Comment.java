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
        @NamedQuery( name = Comment.GET, query = "SELECT c FROM Comment c " )

} )
@Entity
public class Comment {


    public static final String GET = "Comment.get";

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;

    private String content;

    @ManyToOne
    private Post post;

    @Temporal( TemporalType.DATE )
    private Calendar date;

    @ManyToOne
    private User user;

    private int likes;



    public int getLikes() {
        return likes;
    }



    public void setLikes( int likes ) {
        this.likes = likes;
    }



    public Post getPostId() {
        return post;
    }



    public void setPostId( Post post ) {
        this.post = post;
    }



    public String getContent() {
        return content;
    }



    public void setContent( String content ) {
        this.content = content;
    }



    public int getId() {
        return id;
    }



    public void setId( int commentId ) {
        this.id = commentId;
    }



    public Calendar getDate() {
        return date;
    }



    public void setDate( Calendar date ) {
        this.date = date;
    }



    public User getUserId() {
        return user;
    }



    public void setUserId( User user ) {
        this.user = user;
    }




}
