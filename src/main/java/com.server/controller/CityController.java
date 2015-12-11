package com.server.controller;

import com.server.Utils;
import com.server.entities.AppUser;
import com.server.entities.City;
import com.server.entities.Event;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.*;

/**
 * Created by jp on 02.11.15.
 */
@Stateless
public class CityController {

    @PersistenceContext
    private EntityManager entityManager;

    @EJB
    private UserController userController;

    private Logger logger = Logger.getLogger( this.getClass().getName() );



    //Zurzeit achtet der Controller nicht wirklich auf die City sondern gibt einfach die Event aus der Tabelle wieder
    public com.server.datatype.Event[] getAllPost() {
        TypedQuery<Event> query = entityManager.createNamedQuery( Event.GETALL, Event.class );
        if ( query.getResultList() == null ) {
            throw new NullPointerException();
        }
        List<Event> eventList = query.getResultList();

        AppUser user = new AppUser();

        return Utils.convertToDataEventArray(eventList);

    }


    public void createPost(int cityId, int userId, String text ) {

        //zurzeit nur Aachen deswegen ist cityId egal

        Event event = new Event();
        event.setContent( text );
        event.setDate( Calendar.getInstance() );
        event.setAppuserid( userId );

        entityManager.persist(event);


    }



    public com.server.datatype.Event[] getFirstPosts(int cityId, int userId, int postNum ) {
        return getNextPosts( cityId, userId, postNum, Integer.MAX_VALUE );
    }

    public com.server.datatype.Event[] getNextPosts( int cityId, int userId, int postNum, int lastPostId ) {
        TypedQuery<Event> query = entityManager.createNamedQuery(Event.GETCITY, Event.class);
        query.setParameter( "cityId", cityId );
        query.setParameter( "lastId", lastPostId );
        query.setMaxResults(postNum);


        AppUser user = userController.getUser(userId);
        List<Event> eventList = query.getResultList();

        return Utils.convertToDataEventArray( eventList, user );
    }

    public void createCity(String cityName) {
        City city = new City();
        city.setName( cityName );

        entityManager.persist( city );
    }



    public List<City> getAllCities() {

        TypedQuery<City> query = entityManager.createNamedQuery(City.GETALLCITIES, City.class);
        List<City> cityList = query.getResultList();



        return cityList;


    }
}