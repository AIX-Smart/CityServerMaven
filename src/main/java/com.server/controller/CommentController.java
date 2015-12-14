package com.server.controller;

import com.server.Utils;
import com.server.entities.AppUserEntity;
import com.server.entities.CommentEntity;

import javax.ejb.EJB;
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

    @EJB
    private UserController userController;



    public com.server.datatype.Comment[] allOwnComments( int userId ) {

        TypedQuery<CommentEntity> query = entityManager.createNamedQuery( CommentEntity.GETOWN, CommentEntity.class );
        query.setParameter( "appuserId", userId );

        List<CommentEntity> commentEntityList = query.getResultList();

        AppUserEntity user = userController.getUser( userId );
        return Utils.convertToDataCommentArray( commentEntityList, user );
    }



    public void deleteComments( int id, int userId ) {

        TypedQuery<CommentEntity> query = entityManager.createNamedQuery( CommentEntity.GET, CommentEntity.class );
        query.setParameter( "id", id );
        CommentEntity commentEntity = query.getSingleResult();

        TypedQuery<AppUserEntity> queryUser = entityManager.createNamedQuery( AppUserEntity.GET, AppUserEntity.class );
        query.setParameter( "id", userId );
        AppUserEntity user = queryUser.getSingleResult();

        if ( user.equals( commentEntity.getAppUserEntity().getId() ) ) {
            entityManager.remove( commentEntity );
        }

    }



    public void likeComment( int id, int userId ) {

        // es muss noch eine Abfrage hinzukommen ob der User den Comment schonmal geliked hat

        TypedQuery<CommentEntity> query = entityManager.createNamedQuery( CommentEntity.GET, CommentEntity.class );
        query.setParameter( "id", id );
        CommentEntity commentEntity = query.getSingleResult();

        TypedQuery<AppUserEntity> queryUser = entityManager.createNamedQuery( AppUserEntity.GET, AppUserEntity.class );
        query.setParameter( "id", userId );
        AppUserEntity user = queryUser.getSingleResult();

        int likes = commentEntity.getLikes() + 1;
        commentEntity.setLikes( likes );

        entityManager.persist( commentEntity );

    }
}
