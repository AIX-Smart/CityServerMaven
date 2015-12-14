package com.server;

/**
 * Created by jp on 02.11.15.
 */

import com.server.controller.CityController;
import com.server.datatype.Event;
import com.server.entities.CityEntity;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;


@Path( "/city" )
@Produces( MediaType.APPLICATION_JSON )
@Stateless
@Interceptors( AuditingInterceptor.class )
public class CityRestService
        extends ApplicationRestService {

    @EJB
    private CityController controller;
    private Logger logger = Logger.getLogger( this.getClass().getName() );



    // Get all cities in the database
    // When release, @RolesAllowed( { "admin" } )
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    public Response getAll() {
        Event[] events = controller.getAllPost();
        try {
            return Response.ok( mapper.writeValueAsString( events ) ).build();
        } catch ( Exception e ) {

        }
        return Response.status( Response.Status.NOT_FOUND ).build();
    }



    //Create Event
    @PUT
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Consumes( "text/plain" )
    @Path( "{id}/{userId}" )
    public Response CreatePost( @PathParam( "id" ) int id,
                                @PathParam( "userId" ) int userId,
                                String text ) {
        controller.createPost( id, userId, text );
        return Response.ok().build();
    }



    //Get first Posts
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}/{postNum}/{userId}" )
    public Response getPost( @PathParam( "id" ) int id,
                             @PathParam( "postNum" ) int postNum,
                             @PathParam( "userId" ) int userId ) {

        try {

            Event[] events = controller.getFirstPosts( id, userId, postNum );
            return Response.ok( mapper.writeValueAsString( events ) ).build();
        } catch ( Exception e ) {
            return Response.ok( e.toString() ).build();
        }

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
            return Response.ok( mapper.writeValueAsString( events ) ).build();
        } catch ( Exception e ) {

        }

        return Response.status( Response.Status.NOT_FOUND ).build();

    }



    //Get cityInformation
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}" )
    public Response get( @PathParam( "id" ) long id ) {
        return Response.ok().build();
    }



    @PUT
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{cityName}" )
    public Response create( @PathParam( "cityName" ) String cityName ) {

        controller.createCity( cityName );


        return Response.ok().build();

    }



    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{cityId}/location" )
    public Response getLocations( @PathParam( "cityId" ) int cityId ) {

        com.server.datatype.Location[] allLocations = controller.getLocations( cityId );

        try {
            return Response.ok( objectMapper.writeValueAsString( allLocations ) ).build();
        } catch ( IOException e ) {
        }
        return Response.noContent().build();

    }



    //for web application
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/getAllCities" )
    public Response getAllCities() {

        List<CityEntity> allCities = controller.getAllCities();

        try {
            return Response.ok( objectMapper.writeValueAsString( allCities ) ).build();
        } catch ( IOException e ) {
        }
        return Response.noContent().build();

    }


}