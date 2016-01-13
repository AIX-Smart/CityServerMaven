package com.server;

import com.server.controller.LocationController;
import com.server.datatype.Event;
import com.server.datatype.Location;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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



    //get isUpToDate
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}/{eventId}" )
    public Response get( @PathParam( "id" ) int id,
                         @PathParam( "eventId" ) int eventId ) {

        boolean isUpToDate = controller.isUpToDate( id, eventId );
        try {
            return Response.ok( objectMapper.writeValueAsString( isUpToDate ) ).build();
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



    //Get liked
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



    //Get like count of a Location
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



    //isOwner
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}/{userId}/isOwner" )
    public Response isOwner( @PathParam( "id" ) int id,
                             @PathParam( "userId" ) int userId
    ) {
        boolean isOwner = controller.isOwner( id, userId );
        try {
            return Response.ok( objectMapper.writeValueAsString( isOwner ) ).build();
        } catch ( IOException e ) {
            logger.error( e );
        }
        return Response.serverError().build();
    }





    @PUT
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}/{tagId}/addTag" )
    public Response addTag( @PathParam( "id" ) int id,
                            @PathParam( "tagId" ) int tagId ) {

        Location location = controller.addTag( id, tagId );

        try {
            return Response.ok( objectMapper.writeValueAsString( location ) ).build();
        } catch ( IOException e ) {
            logger.error( e );
        }
        return Response.serverError().build();


    }



    @PUT
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}/{tagId}/removeTag" )
    public Response removeTag( @PathParam( "id" ) int id,
                               @PathParam( "tagId" ) int tagId ) {

        Location location = controller.removeTag( id, tagId );

        try {
            return Response.ok( objectMapper.writeValueAsString( location ) ).build();
        } catch ( IOException e ) {
            logger.error( e );
        }
        return Response.serverError().build();


    }



    //Authenticate User
    @PUT
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}/{userId}/{userMail}" )
    public Response authenticateUser( @PathParam( "id" ) int id,
                                      @PathParam( "userId" ) int userId,
                                      @PathParam( "userMail" ) String userMail,

                                      String password ) {

        Boolean success = controller.authenticateUser( id, userId, userMail, password );

        try {
            return Response.ok( objectMapper.writeValueAsString( success ) ).build();
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

        Event event = controller.createEvent( id, userId, text );

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
                                       @PathParam( "content" ) String text ) {

        Event event = controller.createEvent( id, userId, text );

        try {
            return Response.ok( objectMapper.writeValueAsString( event ) ).build();
        } catch ( IOException e ) {
            logger.error( e );
        }
        return Response.serverError().build();

    }




    //create Location with Webservice
    @POST
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/create/{name}/{cityId}/{street}/{phoneNumber}/{GPS}/{description}/{houseNumber}/{userMail}" )
    public Response create( @PathParam( "name" ) String name,
                            @PathParam( "cityId" ) int cityId,
                            @PathParam( "street" ) String street,
                            @PathParam( "phoneNumber" ) String phoneNumber,
                            @PathParam( "GPS" ) String gPS,
                            @PathParam( "description" ) String description,
                            @PathParam( "houseNumber" ) String houseNumber,
                            @PathParam( "userMail" ) String userMail,
                            String password) {


        Location location = new Location(
                controller.createLocation( name, cityId, street, houseNumber, phoneNumber, description, gPS, userMail, password ) );


        try {
            return Response.ok( objectMapper.writeValueAsString( location ) ).build();
        } catch ( IOException e ) {
            logger.error( e );
        }
        return Response.serverError().build();
    }


}