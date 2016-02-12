package com.server.controller;

import com.server.datatype.User;
import com.server.entities.AppUserEntity;
import com.server.entities.LocationEntity;
import com.server.entities.LocationOwnerEntity;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jp on 05.11.15.
 */
@Stateless
public class UserController {

    @PersistenceContext
    private EntityManager entityManager;

    @EJB
    private LocationController locationController;

    private Logger logger = Logger.getLogger(this.getClass().getName());




    public User[] getAllUser() {
        TypedQuery<AppUserEntity> query = entityManager.createNamedQuery( AppUserEntity.GETALL, AppUserEntity.class );
        List<AppUserEntity> userList = query.getResultList();



        return Utils.convertToDataUserArray( userList );
    }



    public User getUserByDeviceId( String deviceId ) {

        TypedQuery<AppUserEntity> query = entityManager.createNamedQuery( AppUserEntity.GETBYDEVICEID, AppUserEntity.class );
        query.setParameter( "deviceId", deviceId );

        AppUserEntity appUserEntity;

        try {
            appUserEntity = query.getSingleResult();
        }catch ( NoResultException e ){
            appUserEntity = createUser( deviceId );

        }

        return new User( appUserEntity );
    }



    public AppUserEntity createUser( String deviceId ) {
        AppUserEntity newUser = new AppUserEntity();
        newUser.setDeviceId( deviceId );

        entityManager.persist( newUser );

        return newUser;
    }



    public AppUserEntity getUserEntity(int userId ) {



        TypedQuery<AppUserEntity> query = entityManager.createNamedQuery( AppUserEntity.GET, AppUserEntity.class );
        query.setParameter( "id", userId );
        AppUserEntity appUserEntity = query.getSingleResult();
        return appUserEntity;

    }


    public void addLocationOwnerEntityToUser(LocationOwnerEntity locationOwnerEntity, int userId) {

        AppUserEntity appUser = getUserEntity(userId);

        if (appUser.getOwnerEntityList() == null){
            appUser.setOwnerEntityList(new ArrayList<LocationOwnerEntity>());
        }

        appUser.getOwnerEntityList().add(locationOwnerEntity);

        entityManager.merge(appUser);

    }
}
