package com.server.controller;

import com.server.datatype.City;
import com.server.datatype.Event;
import com.server.datatype.Location;
import com.server.entities.CityEntity;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by jp on 02.11.15.
 */
@Stateless
public class CityController {

    @PersistenceContext
    private EntityManager entityManager;

    @EJB
    private UserController userController;

    @EJB
    private LocationController locationController;

    @EJB
    private EventController eventController;

    private Logger logger = Logger.getLogger( this.getClass().getName() );



    public City getCity( int cityId ) {
        TypedQuery<CityEntity> query = entityManager.createNamedQuery( CityEntity.GETCITYBYID, CityEntity.class );
        query.setParameter( "cityId", cityId );
        CityEntity city = query.getSingleResult();
        return new City( city );
    }



    public City[] getAll() {
        TypedQuery<CityEntity> query = entityManager.createNamedQuery( CityEntity.GETALL, CityEntity.class );
        if ( query.getResultList() == null ) {
            throw new NullPointerException();
        }
        List<CityEntity> cityEntityList = query.getResultList();
        List<City> cityList = new List<City>() {

            public int size() {
                return 0;
            }



            public boolean isEmpty() {
                return false;
            }



            public boolean contains( Object o ) {
                return false;
            }



            public Iterator<City> iterator() {
                return null;
            }



            public Object[] toArray() {
                return new Object[ 0 ];
            }



            public <T> T[] toArray( T[] a ) {
                return null;
            }



            public boolean add( City city ) {
                return false;
            }



            public boolean remove( Object o ) {
                return false;
            }



            public boolean containsAll( Collection<?> c ) {
                return false;
            }



            public boolean addAll( Collection<? extends City> c ) {
                return false;
            }



            public boolean addAll( int index, Collection<? extends City> c ) {
                return false;
            }



            public boolean removeAll( Collection<?> c ) {
                return false;
            }



            public boolean retainAll( Collection<?> c ) {
                return false;
            }



            public void clear() {

            }



            public City get( int index ) {
                return null;
            }



            public City set( int index, City element ) {
                return null;
            }



            public void add( int index, City element ) {

            }



            public City remove( int index ) {
                return null;
            }



            public int indexOf( Object o ) {
                return 0;
            }



            public int lastIndexOf( Object o ) {
                return 0;
            }



            public ListIterator<City> listIterator() {
                return null;
            }



            public ListIterator<City> listIterator( int index ) {
                return null;
            }



            public List<City> subList( int fromIndex, int toIndex ) {
                return null;
            }
        };

        for ( CityEntity c : cityEntityList ) {
            cityList.add( new City( c ) );
        }

        return ( City[] )cityList.toArray();
    }



    public Location[] getLocations( int cityId ) {
        return locationController.getLocations( cityId );
    }



    public Location[] getLocationBySearchText( String searchText ) {
        return locationController.getLocationBySearchText( searchText );
    }



    public com.server.datatype.Event[] getFirstPosts( int cityId, int userId, int postNum ) {
        return eventController.getFirstPosts( cityId, userId, postNum );
    }



    public com.server.datatype.Event[] getNextPosts( int cityId, int userId, int postNum, int lastPostId ) {
        return eventController.getNextPosts( cityId, userId, postNum, lastPostId );
    }



    public City createCity( String cityName ) {

        CityEntity cityEntity;

        try {
            TypedQuery<CityEntity> query = entityManager.createNamedQuery( CityEntity.GETCITYBYNAME, CityEntity.class );
            query.setParameter( "cityName", cityName  );

            cityEntity = query.getSingleResult();

        } catch ( NoResultException e ){

            cityEntity = new CityEntity();
            cityEntity.setName( cityName );
            entityManager.persist( cityEntity );

        }

            return new City (cityEntity);
    }



    public Event createEvent( int id, int userId, String text ) {
        return null;
    }
}