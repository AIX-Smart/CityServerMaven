package com.server.controller;

import com.server.Utils;
import com.server.datatype.Location;
import com.server.entities.AppUserEntity;
import com.server.entities.EventEntity;
import com.server.entities.LocationEntity;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Calendar;
import java.util.List;

/**
 * Created by jp on 02.11.15.
 */
@Stateless
public class LocationController {

    @PersistenceContext
    private EntityManager entityManager;

    @EJB
    private UserController userController;

    @EJB
    private CityController cityController;

    private Logger logger = Logger.getLogger( this.getClass().getName() );



    //Zurzeit achtet der Controller nicht wirklich auf die City sondern gibt einfach die Event aus der Tabelle wieder
    public com.server.datatype.Location[] getAllLocation() {
        TypedQuery<LocationEntity> query = entityManager.createNamedQuery( LocationEntity.GETALL, LocationEntity.class );
        if ( query.getResultList() == null ) {
            throw new NullPointerException(  );
        }
        List<LocationEntity> locationEntityList = query.getResultList();



        return Utils.convertToDataLocationArray( locationEntityList );

    }



    public void createPost(int id, int userId, String text ) {

        EventEntity eventEntity = new EventEntity();
        eventEntity.setContent( text );
        eventEntity.setDate( Calendar.getInstance() );
        eventEntity.setAppUserEntity( userController.getUser( userId ) );
        eventEntity.setLocationEntity( getLocationEntityById( id ) );

        entityManager.persist( eventEntity );


    }

    public Location getLocationById(int id) {
        TypedQuery<LocationEntity> query = entityManager.createNamedQuery( LocationEntity.GET, LocationEntity.class );
        query.setParameter( "id", id );

        LocationEntity locationEntity;

        try{
            locationEntity = query.getSingleResult();
            return new Location( locationEntity, null, false );
        }catch(Exception e){

        }

        return null;

    }
    private LocationEntity getLocationEntityById(int id) {
        TypedQuery<LocationEntity> query = entityManager.createNamedQuery( LocationEntity.GET, LocationEntity.class );
        query.setParameter( "id", id );

        LocationEntity locationEntity;

        try{
            locationEntity = query.getSingleResult();
        }catch(Exception e){
            locationEntity = null;
        }

        return locationEntity;

    }



    public com.server.datatype.Event[] getFirstPosts( int id, int userId, int postNum ) {
        return getNextPosts( id, userId, postNum, Integer.MAX_VALUE );
    }



    public com.server.datatype.Event[] getNextPosts( int id, int userId, int postNum, int lastPostId ) {

        TypedQuery<EventEntity> query = entityManager.createNamedQuery( EventEntity.GETLOCATION, EventEntity.class );
        query.setParameter( "lastId", lastPostId );
        query.setParameter( "locationId", id );
        query.setMaxResults( postNum );
        if(query.getResultList() == null){
            throw new NullPointerException(  );
        }
        List<EventEntity> eventEntityList = query.getResultList();

        AppUserEntity user = userController.getUser(userId);

        return Utils.convertToDataEventArray( eventEntityList, user );
    }


    public void createLocation(Location location) {

        LocationEntity entity = new LocationEntity();
        entity.setCityEntity( cityController.getCity( location.getCityId() ) );
        entity.setDescription( location.getDescription() );
        entity.setGPS( location.getGps() );
        entity.setName(location.getName());
        entity.setHouseNumber(location.getHouseNumber());
        entity.setStreet(location.getStreet());
        entity.setLikes(0);
        entity.setPhoneNumber(location.getPhoneNumber());

        entityManager.persist( entity );

    }



    public void createLocation( String name, int cityId, String street, String houseNumber, String phoneNumber, String description, String gPS ) {


        logger.trace("XXXXXXXXXXXXXXXXXXXXXXXXX controller rein XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setCityEntity( cityController.getCity( cityId ) );
        locationEntity.setDescription( description );
        locationEntity.setGPS( gPS );
        locationEntity.setName( name );
        locationEntity.setHouseNumber( houseNumber );
        locationEntity.setStreet( street );
        locationEntity.setLikes( 0 );
        locationEntity.setPhoneNumber( phoneNumber );

        logger.trace(("XXXXXXXXXXXXXXXXXXXX controller raus XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"));

        entityManager.persist( locationEntity );
    }



    public Location[] getLocationBySearchText( String searchText ) {
        TypedQuery<LocationEntity> locationEntityTypedQuery = entityManager.createQuery( LocationEntity.GETBYNAME, LocationEntity.class );
        locationEntityTypedQuery.setParameter( "name", searchText );
        List<LocationEntity> locationEntityList = locationEntityTypedQuery.getResultList();

        return Utils.convertToDataLocationArray( locationEntityList );
    }
}
