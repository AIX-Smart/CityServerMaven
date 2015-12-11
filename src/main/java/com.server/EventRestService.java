package com.server;

import com.server.controller.EventController;
import com.server.datatype.Comment;
import com.server.datatype.Event;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by jp on 02.11.15.
 */
@Path( "/event" )
@Produces( MediaType.APPLICATION_JSON )
@Stateless
@Interceptors( AuditingInterceptor.class )
public class EventRestService
        extends ApplicationRestService {

    @EJB
    private EventController controller;
    private Logger logger = Logger.getLogger( this.getClass().getName() );



    // Get all events in the database which the user created himself
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "{userId}/" )
    public Response getAll( @PathParam( "userId" ) int userId ) {

        Event[] events = controller.allOwnPosts( userId );
        try {
            return Response.ok( mapper.writeValueAsString(events) ).build();
        } catch ( Exception e ) {

        }

        return Response.status( Response.Status.NOT_FOUND ).build();
    }



    //Create Comment
    @POST
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Consumes( "text/plain" )
    @Path( "{id}/{userId}" )
    public Response createComment( @PathParam( "id" ) int id,
                                   @PathParam( "userId" ) int userId,
                                   String text

    ) {
        controller.createComment( id, userId, text );
        return Response.ok().build();
    }



    //like Event
    @PUT
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "{id}/{userId}" )
    public Response likePost( @PathParam( "id" ) int id,
                              @PathParam( "userId" ) int userId

    ) {
        controller.likePost( id, userId );
        return Response.ok().build();
    }



    //Get first Comments
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}/{userId}/{comNum}" )
    public Response getComment( @PathParam( "id" ) int id,
                                @PathParam( "userId" ) int userId,
                                @PathParam( "comNum" ) int comNum ) {
        Comment[] comments = controller.getFirstComments( id, userId, comNum );
        try {
            return Response.ok( mapper.writeValueAsString( comments ) ).build();
        } catch ( Exception e ) {

        }

        return Response.status( Response.Status.NOT_FOUND ).build();
    }



    //Get following comment from last commentId on
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}/{userId}/{comNum}/{lastCommentId}" )
    public Response getComment( @PathParam( "id" ) int id,
                                @PathParam( "userId" ) int userId,
                                @PathParam( "comNum" ) int comNum,
                                @PathParam( "lastCommentId" ) int lastCommentId
    ) {
        Comment[] comments = controller.getNextComments( id, userId, comNum, lastCommentId );
        try {
            return Response.ok( mapper.writeValueAsString( comments ) ).build();
        } catch ( Exception e ) {

        }

        return Response.status( Response.Status.NOT_FOUND ).build();
    }



    //Delete Event
    @DELETE
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}/{userId}/" )
    public Response deletePost( @PathParam( "id" ) int id,
                                @PathParam( "userId" ) int userId
    ) {
        controller.deletePost( id, userId );
        return Response.ok().build();
    }

    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/getAllPosts" )
    public Response getAll( ) {

        Event[] events = controller.getAllPost();

        try {
            return Response.ok( mapper.writeValueAsString(events) ).build();
        } catch ( Exception e ) {

        }

        return Response.status( Response.Status.NOT_FOUND ).build();
    }


}