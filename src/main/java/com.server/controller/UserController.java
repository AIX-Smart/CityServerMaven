package com.server.controller;

import com.server.Utils;
import com.server.datatype.User;
import com.server.entities.AppUserEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by jp on 05.11.15.
 */
@Stateless
public class UserController {

    @PersistenceContext
    private EntityManager entityManager;



    public AppUserEntity createUser( String deviceId ) {
        AppUserEntity newUser = new AppUserEntity();
        newUser.setDeviceId( deviceId );

        entityManager.persist( newUser );

        return newUser;
    }



    public AppUserEntity getUser( int userId ) {

        TypedQuery<AppUserEntity> query = entityManager.createNamedQuery( AppUserEntity.GET, AppUserEntity.class );
        query.setParameter( "id", userId );
        AppUserEntity appUserEntity = query.getSingleResult();
        return appUserEntity;

    }



    public List<AppUserEntity> getAllUser() {
        TypedQuery<AppUserEntity> query = entityManager.createNamedQuery( AppUserEntity.GETALL, AppUserEntity.class );
        List<AppUserEntity> userList = query.getResultList();

        return userList;
    }



    public User getUserByDeviceId( String deviceId ) {

        TypedQuery<AppUserEntity> query = entityManager
                .createNamedQuery( AppUserEntity.GETBYDEVICEID, AppUserEntity.class );
        query.setParameter( "deviceId", deviceId );
        query.setMaxResults( 1 );
        List<AppUserEntity> appUserEntityList = query.getResultList();
        if ( appUserEntityList.isEmpty() ) {
            appUserEntityList.add( createUser( deviceId ) );
        }
        AppUserEntity userEntity = appUserEntityList.get( 0 );

        return Utils.convertToDataUser( userEntity );
    }
}
