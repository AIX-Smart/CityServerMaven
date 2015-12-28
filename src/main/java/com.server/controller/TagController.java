package com.server.controller;

import com.server.datatype.Event;
import com.server.datatype.Location;
import com.server.datatype.Tag;
import com.server.entities.TagEntity;

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
public class TagController {

    @PersistenceContext
    private EntityManager entityManager;

    @EJB
    private LocationController locationController;

    @EJB
    private EventController eventController;


    public Location[] getFirstLocations(int tagId, int cityId, int locationNum) {
        return getNextLocations( tagId, cityId, locationNum, Integer.MAX_VALUE);
    }


    public Location[] getNextLocations(int tagId, int cityId, int postNum, int lastLocationId) {
       return locationController.getNextLocationsWithTagId( tagId, cityId, postNum, lastLocationId);
    }


    public Event[] getNextEvents(int tagId, int cityId, int userId, int postNum, int lastPostId) {
        return eventController.getNextEventsWithTagId( tagId, cityId, userId, postNum, lastPostId);
    }


    public Event[] getFirstEvents(int tagId, int cityId, int userId, int postNum) {
        return getNextEvents(tagId, cityId, userId, postNum, Integer.MAX_VALUE);
    }

    public List<TagEntity> getAllTagEntities() {

        TypedQuery<TagEntity> query = entityManager.createNamedQuery(TagEntity.GETALL, TagEntity.class);
        List<TagEntity> tagList = query.getResultList();

        return tagList;
    }


    public Tag getTag(long id) {
        TypedQuery<TagEntity> query = entityManager.createNamedQuery(TagEntity.GET, TagEntity.class);
        query.setParameter("id", id);

        TagEntity tagEntity = query.getSingleResult();

        return new Tag(tagEntity);
    }


    public Tag[] getAllTags() {

        List<TagEntity> tagEntities = getAllTagEntities();

        return Utils.convertToDataTagArray(tagEntities);
    }

    public Tag createTag(String name) {

        TagEntity tagEntity = new TagEntity();
        tagEntity.setName(name);

        entityManager.persist(tagEntity);

        return new Tag(tagEntity);
    }

}
