package com.server.controller;

import com.server.datatype.*;
import com.server.entities.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jp on 20.11.2015.
 */
public class Utils {


    public static com.server.datatype.Event[] convertToDataEventArray(List<EventEntity> eventEntityList, AppUserEntity user) {
        com.server.datatype.Event[] datatypeEvents = new com.server.datatype.Event[eventEntityList.size()];
        for (int i = 0; i < eventEntityList.size(); i++) {
            EventEntity eventEntity = eventEntityList.get(i);
            datatypeEvents[i] = new com.server.datatype.Event(
                    eventEntity, user.getLikedEventEntities().contains(eventEntity));
        }
        return datatypeEvents;
    }

    public static com.server.datatype.Event[] convertToDataEventArray(List<EventEntity> eventEntityList) {
        com.server.datatype.Event[] datatypeEvents = new com.server.datatype.Event[eventEntityList.size()];
        for (int i = 0; i < eventEntityList.size(); i++) {
            EventEntity eventEntity = eventEntityList.get(i);
            datatypeEvents[i] = new com.server.datatype.Event(eventEntity, false);
        }
        return datatypeEvents;
    }

    public static com.server.datatype.Comment[] convertToDataCommentArray(List<CommentEntity> commentEntityList, AppUserEntity user) {
        com.server.datatype.Comment[] datatypeComment = new com.server.datatype.Comment[commentEntityList.size()];
        for (int i = 0; i < commentEntityList.size(); i++) {
            CommentEntity commentEntity = commentEntityList.get(i);
            datatypeComment[i] = new com.server.datatype.Comment(
                    commentEntity, user.getLikedCommentEntities().contains(commentEntity));
        }
        return datatypeComment;
    }

    public static Comment[] convertToDataCommentArray(List<CommentEntity> commentEntityList) {
        Comment[] datatypeComment = new Comment[commentEntityList.size()];
        for (int i = 0; i < commentEntityList.size(); i++) {
            CommentEntity commentEntity = commentEntityList.get(i);
            datatypeComment[i] = new Comment(
                    commentEntity);
        }
        return datatypeComment;
    }

    public static Location[] convertToDataLocationArray(List<LocationEntity> locationEntityList) {
        Location[] datatypeLocations = new Location[locationEntityList.size()];
        for (int i = 0; i < locationEntityList.size(); i++) {
            LocationEntity locationEntity = locationEntityList.get(i);
            datatypeLocations[i] = new Location(locationEntity);
        }
        return datatypeLocations;
    }

    public static com.server.datatype.User[] convertToDataUserArray(List<AppUserEntity> appUserEntityList) {
        User[] users = new User[appUserEntityList.size()];
        for (int i = 0; i < appUserEntityList.size(); i++) {
            AppUserEntity appUserEntity = appUserEntityList.get(i);
            users[i] = new User(appUserEntity);
        }
        return users;
    }

    public static Tag[] convertToDataTagArray(List<TagEntity> tagEntityList) {
        Tag[] tags = new Tag[tagEntityList.size()];
        for (int i = 0; i < tagEntityList.size(); i++) {
            TagEntity tagEntity = tagEntityList.get(i);
            tags[i] = new Tag(tagEntity);
        }
        return tags;
    }

    public static City[] convertToDataCityArray(List<CityEntity> cityEntityList) {
        City[] cities = new City[cityEntityList.size()];
        for (int i = 0; i < cityEntityList.size(); i++) {
            CityEntity cityEntity = cityEntityList.get(i);
            cities[i] = new City(cityEntity);
        }
        return cities;
    }


}
