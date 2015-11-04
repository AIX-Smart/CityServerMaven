package com.server.entities;

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
        @NamedQuery( name = CommentBean.GET, query = "SELECT d FROM Comment d " ),

} )
@Entity
public class CommentBean {


    public static final String GET = "Comment.get";

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int commentId;

    private String content;

    private long postId;

    private Date date;

    private long userId;

    private int likes;



    public int getLikes() {
        return likes;
    }



    public void setLikes( int likes ) {
        this.likes = likes;
    }



    public long getPostId() {
        return postId;
    }



    public void setPostId( long postId ) {
        this.postId = postId;
    }



    public String getContent() {
        return content;
    }



    public void setContent( String content ) {
        this.content = content;
    }



    public int getCommentId() {
        return commentId;
    }



    public void setCommentId( int commentId ) {
        this.commentId = commentId;
    }



    public Date getDate() {
        return date;
    }



    public void setDate( Date date ) {
        this.date = date;
    }



    public long getUserId() {
        return userId;
    }



    public void setUserId( long userId ) {
        this.userId = userId;
    }




}
