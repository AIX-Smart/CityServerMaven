package com.server;

import com.server.controller.TagController;
import com.server.datatype.Event;
import com.server.datatype.Location;
import com.server.datatype.Tag;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Created by jp on 02.11.15.
 */
@Path( "/tag" )
@Produces( MediaType.APPLICATION_JSON )
@Stateless
@Interceptors( AuditingInterceptor.class )
public class TagRestService
        extends ApplicationRestService {

    @EJB
    private TagController controller;
    private Logger logger = Logger.getLogger( this.getClass().getName() );



    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}" )
    public Response get( @PathParam( "id" ) long id ) {

        Tag tag = controller.getTag( id );
        try {
            return Response.ok( objectMapper.writeValueAsString( tag ) ).build();
        } catch ( IOException e ) {
            logger.error( e );
        }
        return Response.serverError().build();

    }



    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}/{postNum}/{userId}" )
    public Response getPost( @PathParam( "id" ) int id,
                             @PathParam( "postNum" ) int postNum,
                             @PathParam( "userId" ) int userId

    ) {
        Event[] events = controller.getNextPosts( id, userId, postNum );
        try {
            return Response.ok( mapper.writeValueAsString( events ) ).build();
        } catch ( Exception e ) {

        }

        return Response.status( Response.Status.NOT_FOUND ).build();
    }



    //Get following post from last PostId on with tag
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
            return Response.ok( mapper.writeValueAsString( events ) ).build();
        } catch ( Exception e ) {

        }

        return Response.status( Response.Status.NOT_FOUND ).build();
    }



    //Get first Location with tag
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}/location/{locationNum}/{userId}" )
    public Response getLocation( @PathParam( "id" ) int id,
                                 @PathParam( "locationNum" ) int locationNum,
                                 @PathParam( "userId" ) int userId
    ) {
        Location[] locations = controller.getNextLocations( id, userId, locationNum );
        try {
            return Response.ok( mapper.writeValueAsString( locations ) ).build();
        } catch ( Exception e ) {

        }

        return Response.status( Response.Status.NOT_FOUND ).build();
    }



    //Get following Location from lastLocationId on with tag
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}/location/{locationNum}/{userId}/{lastLocationId}" )
    public Response getLocation( @PathParam( "id" ) int id,
                                 @PathParam( "locationNum" ) int locationNum,
                                 @PathParam( "userId" ) int userId,
                                 @PathParam( "lastLocationId" ) int lastPostId
    ) {
        Location[] locations = controller.getNextLocations( id, userId, locationNum, lastPostId );
        try {
            return Response.ok( mapper.writeValueAsString( locations ) ).build();
        } catch ( Exception e ) {

        }

        return Response.status( Response.Status.NOT_FOUND ).build();
    }



    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/all" )
    public Response getAll() {

        Tag[] tags = controller.getAllTags();

        try {
            return Response.ok( objectMapper.writeValueAsString( tags ) ).build();
        } catch ( IOException e ) {
            logger.error( e );
        }
        return Response.serverError().build();
    }


}