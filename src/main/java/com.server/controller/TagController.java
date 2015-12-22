package com.server.controller;

import com.server.entities.EventEntity;
import com.server.entities.LocationEntity;
import com.server.entities.TagEntity;

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

    public LocationEntity[] getNextLocations( int id, int userId, int locationNum ) {
        return new LocationEntity[ 0 ];
    }



    public LocationEntity[] getNextLocations( int id, int userId, int postNum, int lastPostId ) {
        return new LocationEntity[ 0 ];
    }



    public EventEntity[] getNextPosts( int tagid, int userId, int postNum, int lastPostId ) {
        return new EventEntity[0];
    }



    public EventEntity[] getNextPosts( int id, int userId, int postNum ) {
        return new EventEntity[ 0 ];
    }

    public List<TagEntity> allTags() {
        TypedQuery<TagEntity> query = entityManager.createNamedQuery( TagEntity.GETALL, TagEntity.class );
        List<TagEntity> tagList = query.getResultList();
        return tagList;
    }
}
