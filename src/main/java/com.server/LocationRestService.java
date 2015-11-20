package com.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.server.controller.LocationController;
import com.server.datatype.Event;
import com.server.datatype.LocationData;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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

        LocationData[] locationBeans = controller.getAllLocation();

        try {
            return Response.ok( mapper.writeValueAsString( locationBeans ) ).build();
        } catch ( JsonProcessingException e ) {
            e.printStackTrace();
        }


        return Response.status( Response.Status.NOT_FOUND ).build();
    }



    //Create Event
    @PUT
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}/{userId}" )
    public Response createPost( @PathParam( "id" ) int id,
                                @PathParam( "userId" ) int userId,
                                String text ) {
        controller.createPost( id, userId, text );
        return Response.ok().build();

    }



    //Get first Posts
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}/{userId}/{postNum}" )
    public Response getPost( @PathParam( "id" ) int id,
                             @PathParam( "userId" ) int userId,
                             @PathParam( "postNum" ) int postNum
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
    @Path( "/{id}/{userId}/{postNum}/{lastPostId}" )
    public Response getPost( @PathParam( "id" ) int id,
                             @PathParam( "userId" ) int userId,
                             @PathParam( "postNum" ) int postNum,
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
    public Response get( @PathParam( "id" ) long id ) {
        return Response.ok("42").build();
    }


}