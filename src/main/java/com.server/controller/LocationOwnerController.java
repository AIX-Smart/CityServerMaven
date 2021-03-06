package com.server.controller;

import com.server.entities.AppUserEntity;
import com.server.entities.LocationEntity;
import com.server.entities.LocationOwnerEntity;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

/**
 * Created by jp on 13.01.16.
 */
@Stateless
public class LocationOwnerController {

    @PersistenceContext
    private EntityManager entityManager;

    @EJB
    private LocationController locationController;

    @EJB
    private UserController userController;


    private Logger logger = Logger.getLogger(this.getClass().getName());


    public Boolean authenticateUser( int locationId, int userId, String userMail, String password ) {

        LocationOwnerEntity locationOwnerEntity = locationController.getLocationOwner( locationId );
        AppUserEntity user = userController.getUserEntity( userId );

        if ( verifyPassword( locationOwnerEntity, userMail, password )){

            if (!locationOwnerEntity.getAppUserEntityList().contains( user )) {

                locationOwnerEntity.addAppUser( user );
                userController.addLocationOwnerEntityToUser(locationOwnerEntity, userId);
                entityManager.merge( locationOwnerEntity );
            }

            logger.info(true);

            return true;



        }else{
            return false;
        }

    }



    private boolean verifyPassword( LocationOwnerEntity locationOwnerEntity,String userMail, String password ) {

        if( locationOwnerEntity.getEmail().equals( userMail ) && locationOwnerEntity.getPassword().equals( password )){
            return true;
        }else{
            return false;
        }


    }



    public LocationOwnerEntity getLocationOwnerEntityById ( int id ){
        return new LocationOwnerEntity();
    }



    public LocationOwnerEntity createNewLocationOwner( String userMail, String password ) {

            LocationOwnerEntity locationOwnerEntity = new LocationOwnerEntity();
            locationOwnerEntity.setEmail( userMail );
            locationOwnerEntity.setPassword( password );
            locationOwnerEntity.setAppUserEntityList( new ArrayList<AppUserEntity>() );
            locationOwnerEntity.setLocationEntities( new ArrayList<LocationEntity>() );

            entityManager.persist( locationOwnerEntity );

            return  locationOwnerEntity;
    }



    public void addLocationToLocationOwner( LocationEntity locationEntity, LocationOwnerEntity locationOwnerEntity ) {

        locationOwnerEntity.appendLocation( locationEntity );

        entityManager.merge(locationEntity);

    }

}
