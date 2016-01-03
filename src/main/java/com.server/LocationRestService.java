package com.server;

import com.server.controller.LocationController;
import com.server.datatype.Event;
import com.server.datatype.Location;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Created by jp on 02.11.15.
 */
@Path( "/location" )
@Produces( MediaType.APPLICATION_JSON )
@Stateless
@Interceptors( AuditingInterceptor.class )
public class LocationRestService
        extends ApplicationRestService {

    @EJB
    private LocationController controller;
    private Logger logger = Logger.getLogger( this.getClass().getName() );



    //Get location
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}" )
    public Response get( @PathParam( "id" ) int id ) {

        Location location = controller.getLocationById( id );

        try {
            return Response.ok( objectMapper.writeValueAsString( location ) ).build();
        } catch ( IOException e ) {
            logger.error( e );
        }
        return Response.serverError().build();

    }




    //Get first Posts
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}/{postNum}/{userId}" )
    public Response getPost( @PathParam( "id" ) int id,
                             @PathParam( "postNum" ) int postNum,
                             @PathParam( "userId" ) int userId
    ) {
        Event[] events = controller.getFirstPosts( id, userId, postNum );
        try {
            return Response.ok( objectMapper.writeValueAsString( events ) ).build();
        } catch ( IOException e ) {
            logger.error( e );
        }
        return Response.serverError().build();

    }



    //Get following post from last PostId on
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}/{postNum}/{userId}/{lastPostId}" )
    public Response getPost( @PathParam( "id" ) int id,
                             @PathParam( "postNum" ) int postNum,
                             @PathParam( "userId" ) int userId,
                             @PathParam( "lastPostId" ) int lastPostId
    ) {
        Event[] events = controller.getNextPosts( id, userId, postNum, lastPostId );
        try {
            return Response.ok( objectMapper.writeValueAsString( events ) ).build();
        } catch ( IOException e ) {
            logger.error( e );
        }
        return Response.serverError().build();
    }


    //Get following post from last PostId on
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}/{userId}" )
    public Response getPost( @PathParam( "id" ) int id,
                             @PathParam( "userId" ) int userId
    ) {
        boolean liked = controller.getLiked( id, userId );
        try {
            return Response.ok( objectMapper.writeValueAsString( liked ) ).build();
        } catch ( IOException e ) {
            logger.error( e );
        }
        return Response.serverError().build();
    }


    //Get following post from last PostId on
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}/likeCount" )
    public Response getPost( @PathParam( "id" ) int id
    ) {
        int likeCount = controller.getLikeCount( id );
        try {
            return Response.ok( objectMapper.writeValueAsString( likeCount ) ).build();
        } catch ( IOException e ) {
            logger.error( e );
        }
        return Response.serverError().build();
    }


    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/all" )
    public Response getAll() {

        Location[] locationBeans = controller.getAllLocation();

        try {
            return Response.ok( objectMapper.writeValueAsString( locationBeans ) ).build();
        } catch ( IOException e ) {
            logger.error( e );
        }
        return Response.serverError().build();
    }

    @PUT
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}/addTag" )
    public Response addTag( @PathParam( "id" ) int id,
                             String tagId){

        Location location = controller.addTag(id, Integer.parseInt(tagId));

        try {
            return Response.ok( objectMapper.writeValueAsString( location ) ).build();
        } catch ( IOException e ) {
            logger.error( e );
        }
        return Response.serverError().build();


    }

    @PUT
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}/removeTag" )
    public Response removeTag( @PathParam( "id" ) int id,
                                String tagId){

        Location location = controller.removeTag(id, Integer.parseInt(tagId));

        try {
            return Response.ok( objectMapper.writeValueAsString( location ) ).build();
        } catch ( IOException e ) {
            logger.error( e );
        }
        return Response.serverError().build();


    }



    //Create Event
    @POST
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}/{userId}" )
    public Response createPost( @PathParam( "id" ) int id,
                                @PathParam( "userId" ) int userId,
                                String text ) {

        Event event = controller.createEvent(id, userId, text);

        try {
            return Response.ok( objectMapper.writeValueAsString( event ) ).build();
        } catch ( IOException e ) {
            logger.error( e );
        }
        return Response.serverError().build();

    }

    //Create Event over Web Application
    @POST
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}/{userId}/{content}" )
    public Response createPostWebsite( @PathParam( "id" ) int id,
                                       @PathParam( "userId" ) int userId,
                                       @PathParam("content") String text ) {

        Event event = controller.createEvent(id, userId, text);

        try {
            return Response.ok( objectMapper.writeValueAsString( event ) ).build();
        } catch ( IOException e ) {
            logger.error( e );
        }
        return Response.serverError().build();

    }



    @POST
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/create" )
    public Response create( Location location){


        Location newLocation = controller.createLocation(location);

        try {
            return Response.ok( objectMapper.writeValueAsString( newLocation ) ).build();
        } catch ( IOException e ) {
            logger.error( e );
        }
        return Response.serverError().build();

    }
    //create Location with Webservice
    @POST
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/create/{name}/{cityId}/{street}/{phoneNumber}/{GPS}/{description}/{houseNumber}" )
    public Response create( @PathParam( "name" ) String name,
                            @PathParam( "cityId" ) int cityId,
                            @PathParam( "street" ) String street,
                            @PathParam( "phoneNumber" ) String phoneNumber,
                            @PathParam( "GPS" ) String gPS,
                            @PathParam( "description" ) String description,
                            @PathParam( "houseNumber" ) String houseNumber ){


        Location location= new Location( controller.createLocation(name, cityId, street, houseNumber, phoneNumber, description, gPS) );


        try {
            return Response.ok( objectMapper.writeValueAsString( location ) ).build();
        } catch ( IOException e ) {
            logger.error( e );
        }
        return Response.serverError().build();
    }


}