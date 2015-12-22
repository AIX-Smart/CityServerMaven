package com.server;

import com.fasterxml.jackson.core.JsonProcessingException;
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



    // Get all locations?
    // When release, @RolesAllowed( { "admin" } )
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    public Response getAll() {

        Location[] locationBeans = controller.getAllLocation();

        try {
            return Response.ok( mapper.writeValueAsString( locationBeans ) ).build();
        } catch ( JsonProcessingException e ) {
            e.printStackTrace();
        }


        return Response.status( Response.Status.NOT_FOUND ).build();
    }



    //Create Event
    @POST
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}/{userId}" )
    public Response createPost( @PathParam( "id" ) int id,
                                @PathParam( "userId" ) int userId,
                                String text ) {
        controller.createPost( id, userId, text );
        return Response.ok().build();

    }

    //Create Event over Web Application
    @POST
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}/{userId}/{content}" )
    public Response createPostWebsite( @PathParam( "id" ) int id,
                                @PathParam( "userId" ) int userId,
                                @PathParam("content") String text ) {
        controller.createPost( id, userId, text );
        return Response.ok().build();

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
            return Response.ok( mapper.writeValueAsString(events) ).build();
        } catch ( Exception e ) {

        }

        return Response.status( Response.Status.NOT_FOUND ).build();

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
            return Response.ok( mapper.writeValueAsString(events) ).build();
        } catch ( Exception e ) {

        }

        return Response.status( Response.Status.NOT_FOUND ).build();
    }


    //Get locationInformation
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}" )
    public Response get( @PathParam( "id" ) int id ) {

        Location location = controller.getLocationById( id );

        try {
            return Response.ok( mapper.writeValueAsString(location) ).build();
        } catch ( Exception e ) {

        }

        return Response.status( Response.Status.NOT_FOUND ).build();

    }


    @PUT
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/create" )
    public Response create( Location location){


        controller.createLocation(location);


        return Response.ok().build();

    }

    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/search" )
    public Response get( String searchText ) {

        Location[] locations = controller.getLocationBySearchText(searchText);

        try {
            return Response.ok( mapper.writeValueAsString(locations) ).build();
        } catch ( Exception e ) {

        }

        return Response.status( Response.Status.NOT_FOUND ).build();

    }


    //create Location with Webservice
    @PUT
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/create/{name}/{cityId}/{street}/{phoneNumber}/{GPS}/{description}/{houseNumber}" )
    public Response create( @PathParam( "name" ) String name,
                            @PathParam( "cityId" ) int cityId,
                            @PathParam( "street" ) String street,
                            @PathParam( "phoneNumber" ) String phoneNumber,
                            @PathParam( "GPS" ) String gPS,
                            @PathParam( "description" ) String description,
                            @PathParam( "houseNumber" ) String houseNumber ){



        logger.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXHier komme ich rein >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        controller.createLocation(name, cityId, street, houseNumber, phoneNumber, description, gPS);


        return Response.ok().build();

    }

}