package com.server.controller;

import com.server.Utils;
import com.server.datatype.Event;
import com.server.entities.AppUserEntity;
import com.server.entities.CityEntity;
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
public class CityController {

    @PersistenceContext
    private EntityManager entityManager;

    @EJB
    private UserController userController;

    private Logger logger = Logger.getLogger(this.getClass().getName());


    //Zurzeit achtet der Controller nicht wirklich auf die City sondern gibt einfach die Event aus der Tabelle wieder
    public com.server.datatype.Event[] getAllPost() {
        TypedQuery<EventEntity> query = entityManager.createNamedQuery(EventEntity.GETALL, EventEntity.class);
        if (query.getResultList() == null) {
            throw new NullPointerException();
        }
        List<EventEntity> eventEntityList = query.getResultList();

        AppUserEntity user = new AppUserEntity();

        return Utils.convertToDataEventArray(eventEntityList);

    }


    public void createPost(int cityId, int userId, String text) {

        //zurzeit nur Aachen deswegen ist cityId egal

        EventEntity eventEntity = new EventEntity();
        eventEntity.setContent(text);
        eventEntity.setDate(Calendar.getInstance());
        eventEntity.setAppUserEntity(userController.getUser(userId));

        entityManager.persist(eventEntity);


    }


    public com.server.datatype.Event[] getFirstPosts(int cityId, int userId, int postNum) {
        return getNextPosts(cityId, userId, postNum, Integer.MAX_VALUE);
    }


    public com.server.datatype.Event[] getNextPosts(int cityId, int userId, int postNum, int lastPostId) {
        TypedQuery<EventEntity> query = entityManager.createNamedQuery(EventEntity.GETCITY, EventEntity.class);
        query.setParameter("cityId", cityId);
        query.setParameter("lastId", lastPostId);
        query.setMaxResults(postNum);


        AppUserEntity user = userController.getUser(userId);
        List<EventEntity> eventEntityList = query.getResultList();

        return Utils.convertToDataEventArray(eventEntityList, user);
    }


    public void createCity(String cityName) {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setName(cityName);

        entityManager.persist(cityEntity);
    }


    public List<CityEntity> getAllCities() {

        TypedQuery<CityEntity> query = entityManager.createNamedQuery(CityEntity.GETALLCITIES, CityEntity.class);
        List<CityEntity> cityEntityList = query.getResultList();


        return cityEntityList;


    }


    public com.server.datatype.Location[] getLocations(int cityId) {
        TypedQuery<LocationEntity> query = entityManager
                .createNamedQuery(LocationEntity.GETCITYLOCATIONS, LocationEntity.class);
        query.setParameter("cityId", cityId);
        List<LocationEntity> locationEntityList = query.getResultList();

        return Utils.convertToDataLocationArray(locationEntityList);
    }


    public CityEntity getCity(int cityId) {
        TypedQuery<CityEntity> query = entityManager.createNamedQuery(CityEntity.GETCITY, CityEntity.class);
        query.setParameter("cityId", cityId);
        CityEntity city = query.getSingleResult();
        return city;
    }

}