package com.server.controller;

import com.server.entities.AppUser;
import com.server.entities.Comment;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by jp on 02.11.15.
 */
@Stateless
public class CommentController {


    @PersistenceContext
    private EntityManager entityManager;



    public Comment[] allOwnComments( int userId ) {

        TypedQuery<Comment> query = entityManager.createNamedQuery( Comment.GETOWN, Comment.class );
        query.setParameter( "appuserId", userId );

        List<Comment> commentList = query.getResultList();
        return commentList.toArray(new Comment[ commentList.size()]);

    }



    public void deleteComments( int id, int userId ) {

        TypedQuery<Comment> query = entityManager.createNamedQuery( Comment.GET, Comment.class);
        query.setParameter( "id", id );
        Comment comment = query.getSingleResult();

        TypedQuery<AppUser> queryUser = entityManager.createNamedQuery( AppUser.GET, AppUser.class );
        query.setParameter( "id", userId );
        AppUser user = queryUser.getSingleResult();

        if (user.equals( comment.getUser() )){
            entityManager.remove( comment );
        }

    }



    public void likeComment( int id, int userId ) {

        // es muss noch eine Abfrage hinzukommen ob der User den Comment schonmal geliked hat

        TypedQuery<Comment> query = entityManager.createNamedQuery( Comment.GET, Comment.class );
        query.setParameter( "id", id );
        Comment comment = query.getSingleResult();

        TypedQuery<AppUser> queryUser = entityManager.createNamedQuery( AppUser.GET, AppUser.class );
        query.setParameter( "id", userId );
        AppUser user = queryUser.getSingleResult();

        int likes = comment.getLikes() + 1;
        comment.setLikes( likes );

        entityManager.persist( comment );

    }
}
