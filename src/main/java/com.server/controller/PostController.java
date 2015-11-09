package com.server.controller;

import com.server.entities.Comment;
import com.server.entities.Post;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by jp on 02.11.15.
 */
@Stateless
public class PostController {

    @PersistenceContext
    private EntityManager entityManager;



    public Post[] allOwnPosts( int userId ) {
        return new Post[ 0 ];
    }



    public void createComment( int id, int userId, String text ) {
        
    }



    public Comment[] getNextComments( int id, int userId, int comNum ) {
        return new Comment[ 0 ];
    }



    public Comment[] getNextComments( int id, int userId, int comNum, int lastCommentId ) {
        return new Comment[ 0 ];
    }



    public void deletePost( int id, int userId ) {

    }



    public void likePost( int id, int userId ) {
    }
}
