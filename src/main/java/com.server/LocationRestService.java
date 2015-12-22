package com.server;

import com.server.controller.LocationController;
import com.server.datatype.Event;
import com.server.datatype.Location;
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


        Location location= controller.createLocation(name, cityId, street, houseNumber, phoneNumber, description, gPS);


        try {
            return Response.ok( objectMapper.writeValueAsString( location ) ).build();
        } catch ( IOException e ) {
            logger.error( e );
        }
        return Response.serverError().build();
    }


}