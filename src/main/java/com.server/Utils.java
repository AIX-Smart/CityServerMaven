package com.server;

import com.server.datatype.Tag;
import com.server.entities.AppUser;
import com.server.entities.Comment;
import com.server.entities.Event;
import com.server.entities.Location;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jp on 20.11.2015.
 */
public class Utils {


    public static com.server.datatype.Event[] convertToDataEventArray(List<Event> eventList, AppUser user){
        com.server.datatype.Event[] datatypeEvents = new com.server.datatype.Event[eventList.size()];
        for(int i = 0; i<eventList.size(); i++){
            Event event = eventList.get(i);
            datatypeEvents[i] = new com.server.datatype.Event(event, user.getLikedEvents().contains(event));
        }
        return datatypeEvents;
    }


    public static com.server.datatype.Event[] convertToDataEventArray(List<Event> eventList){
        com.server.datatype.Event[] datatypeEvents = new com.server.datatype.Event[eventList.size()];
        for(int i = 0; i<eventList.size(); i++){
            Event event = eventList.get(i);
            datatypeEvents[i] = new com.server.datatype.Event(event, false);
        }
        return datatypeEvents;
    }

    public static com.server.datatype.Comment[] convertToDataCommentArray(List<Comment> commentList, AppUser user){
        com.server.datatype.Comment[] datatypeComment = new com.server.datatype.Comment[commentList.size()];
        for(int i = 0; i<commentList.size(); i++){
            Comment comment = commentList.get(i);
            datatypeComment[i] = new com.server.datatype.Comment(comment, user.getLikedComments().contains(comment));
        }
        return datatypeComment;
    }

    public static com.server.datatype.Location[] convertToDataLocationArray(List<Location> locationList){
        com.server.datatype.Location[] datatypeLocations = new com.server.datatype.Location[locationList.size()];
        for(int i = 0; i<locationList.size(); i++){
            Location location = locationList.get(i);
            datatypeLocations[i] = new com.server.datatype.Location(location, new ArrayList<Tag>(), false);
        }
        return datatypeLocations;
    }
}
