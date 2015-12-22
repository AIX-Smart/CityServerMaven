package com.server.controller;

import com.server.datatype.Event;
import com.server.datatype.Location;
import com.server.datatype.Tag;
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

    public Location[] getNextLocations( int id, int userId, int locationNum ) {
        return new Location[ 0 ];
    }



    public Location[] getNextLocations( int id, int userId, int postNum, int lastPostId ) {
        return new Location[ 0 ];
    }



    public Event[] getNextPosts( int tagid, int userId, int postNum, int lastPostId ) {
        return new Event[0];
    }



    public Event[] getNextPosts( int id, int userId, int postNum ) {
        return new Event[ 0 ];
    }

    public List<TagEntity> allTags() {
        TypedQuery<TagEntity> query = entityManager.createNamedQuery( TagEntity.GETALL, TagEntity.class );
        List<TagEntity> tagList = query.getResultList();
        return tagList;
    }



    public Tag getTag( long id ) {
        return null;
    }



    public Tag[] getAllTags() {
        return new Tag[ 0 ];
    }
}
