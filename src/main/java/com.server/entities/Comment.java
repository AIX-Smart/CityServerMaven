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
        @NamedQuery( name = Comment.GETALL, query = "SELECT c FROM Comment c " ),
        @NamedQuery( name = Comment.GET, query = "SELECT c FROM Comment c WHERE c.id = :id" ),
        @NamedQuery( name = Comment.GETOWN, query = "SELECT c FROM Comment c WHERE c.appuser = :userId " ),
        @NamedQuery( name = Comment.GETPOSTCOMMENTS, query = "SELECT c FROM Comment c WHERE c.postId = :postId " )

} )
@Entity
public class Comment {

    public static final String GET             = "Comment.get";
    public static final String GETALL          = "Comment.getAll";
    public static final String GETOWN          = "Comment.getOwn";
    public static final String GETPOSTCOMMENTS = "Comment.getPostComments";

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;

    private String content;

    private int postId;

    @Temporal( TemporalType.DATE )
    private Calendar date;


    @ManyToOne
    private AppUser appuser;

    private int likes;



    public int getLikes() {
        return likes;
    }



    public void setLikes( int likes ) {
        this.likes = likes;
    }



    public int getPostId() {
        return postId;
    }



    public void setPostId( int post ) {
        this.postId = post;
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



    public AppUser getUser() {
        return appuser;
    }



    public void setUser( AppUser appuser ) {
        this.appuser = appuser;
    }


}
