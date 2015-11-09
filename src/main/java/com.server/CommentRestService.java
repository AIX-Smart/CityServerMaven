package com.server;

import com.server.controller.CommentController;
import com.server.entities.Comment;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by jp on 05.11.15.
 */
@Path( "/comment" )
@Produces( MediaType.APPLICATION_JSON )
@Stateless
@Interceptors( AuditingInterceptor.class )
public class CommentRestService
        extends ApplicationRestService {

    @EJB
    private CommentController controller;
    private Logger logger = Logger.getLogger( this.getClass().getName() );



    // Get all comments in the database which the user created himself
    @GET
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "{userId}/" )
    public Response getAll( @PathParam( "userId" ) int userId ) {

        Comment[] comments = controller.allOwnComments( userId );
        try {
            return Response.ok( mapper.writeValueAsString( comments ) ).build();
        } catch ( Exception e ) {

        }

        return Response.status( Response.Status.NOT_FOUND ).build();
    }

    //Delete Comment
    @POST
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}/{userId}/" )
    public Response likeComment( @PathParam( "id" ) int id,
                                    @PathParam( "userId" ) int userId
    ) {
        controller.likeComment(id, userId);
        return Response.ok().build();
    }



    //Delete Comment
    @DELETE
    @Produces( MediaType.APPLICATION_XHTML_XML )
    @Path( "/{id}/{userId}/" )
    public Response deleteComments( @PathParam( "id" ) int id,
                                @PathParam( "userId" ) int userId
    ) {
        controller.deleteComments(id, userId);
        return Response.ok().build();
    }


}