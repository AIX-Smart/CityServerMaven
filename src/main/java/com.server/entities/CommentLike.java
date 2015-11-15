package com.server.entities;

import javax.persistence.*;

/**
 * Created by Thomas on 15.11.2015.
 */
@NamedQueries( {
        @NamedQuery( name = CommentLike.GETCOMMENTLIKE, query = "SELECT cl FROM CommentLike cl WHERE cl.commentId = :commentId AND cl.appUserId = :userId" )
} )

@Entity
public class CommentLike {

    public static final String GETCOMMENTLIKE = "getCommentLike";

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;
    private int commentId;
    private int appUserId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(int appUserId) {
        this.appUserId = appUserId;
    }
}