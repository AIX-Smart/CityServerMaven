package com.server.controller;

import com.server.entities.Comment;

import javax.ejb.Stateless;

/**
 * Created by jp on 02.11.15.
 */
@Stateless
public class CommentController {

    public Comment[] allOwnComments( int userId ) {
        return new Comment[ 0 ];
    }



    public void deleteComments( int id, int userId ) {

    }
}
