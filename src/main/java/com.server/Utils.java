package com.server;

import com.server.datatype.Tag;
import com.server.datatype.User;
import com.server.entities.AppUserEntity;
import com.server.entities.CommentEntity;
import com.server.entities.EventEntity;
import com.server.entities.LocationEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jp on 20.11.2015.
 */
public class Utils {


    public static com.server.datatype.Event[] convertToDataEventArray(List<EventEntity> eventEntityList, AppUserEntity user){
        com.server.datatype.Event[] datatypeEvents = new com.server.datatype.Event[ eventEntityList.size()];
        for(int i = 0; i< eventEntityList.size(); i++){
            EventEntity eventEntity = eventEntityList.get(i);
            datatypeEvents[i] = new com.server.datatype.Event(
                    eventEntity, user.getLikedEventEntities().contains( eventEntity ));
        }
        return datatypeEvents;
    }


    public static com.server.datatype.Event[] convertToDataEventArray(List<EventEntity> eventEntityList ){
        com.server.datatype.Event[] datatypeEvents = new com.server.datatype.Event[ eventEntityList.size()];
        for(int i = 0; i< eventEntityList.size(); i++){
            EventEntity eventEntity = eventEntityList.get(i);
            datatypeEvents[i] = new com.server.datatype.Event( eventEntity, false);
        }
        return datatypeEvents;
    }

    public static com.server.datatype.Comment[] convertToDataCommentArray(List<CommentEntity> commentEntityList, AppUserEntity user){
        com.server.datatype.Comment[] datatypeComment = new com.server.datatype.Comment[ commentEntityList.size()];
        for(int i = 0; i< commentEntityList.size(); i++){
            CommentEntity commentEntity = commentEntityList.get(i);
            datatypeComment[i] = new com.server.datatype.Comment(
                    commentEntity, user.getLikedCommentEntities().contains( commentEntity ));
        }
        return datatypeComment;
    }

    public static com.server.datatype.Location[] convertToDataLocationArray(List<LocationEntity> locationEntityList ){
        com.server.datatype.Location[] datatypeLocations = new com.server.datatype.Location[ locationEntityList.size()];
        for(int i = 0; i< locationEntityList.size(); i++){
            LocationEntity locationEntity = locationEntityList.get(i);
            datatypeLocations[i] = new com.server.datatype.Location( locationEntity, new ArrayList<Tag>(), false);
        }
        return datatypeLocations;
    }

    public static com.server.datatype.User[] convertToDataUserArray(List<AppUserEntity> appUserEntityList){
        User[] users = new User[appUserEntityList.size()];
        for(int i = 0; i< appUserEntityList.size(); i++){
            AppUserEntity appUserEntity = appUserEntityList.get( i );
            users[i] = new User(  appUserEntity );
        }
        return users;
    }

    public static User convertToDataUser(AppUserEntity userEntity) {
        return new User(userEntity);
    }
}
