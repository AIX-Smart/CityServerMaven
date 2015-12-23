package com.server;

import com.server.controller.CommentController;
import com.server.datatype.Comment;
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
 * Created by jp on 05.11.15.
 */
@Path("/comment")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
@Interceptors(AuditingInterceptor.class)
public class CommentRestService
        extends ApplicationRestService {

    @EJB
    private CommentController controller;
    private Logger logger = Logger.getLogger(this.getClass().getName());


    @GET
    @Produces(MediaType.APPLICATION_XHTML_XML)
    @Path("/{userId}")
    public Response getAll(@PathParam("userId") int userId) {

        Comment[] comments = controller.allOwnComments(userId);
        try {
            return Response.ok(mapper.writeValueAsString(comments)).build();
        } catch (Exception e) {
            logger.error(e);
        }

        return Response.serverError().build();
    }


    @GET
    @Produces(MediaType.APPLICATION_XHTML_XML)
    @Path("/all")
    public Response getAll() {

        Comment[] comments = controller.allComments();
        try {
            return Response.ok(mapper.writeValueAsString(comments)).build();
        } catch (Exception e) {
            logger.error(e);
        }

        return Response.serverError().build();
    }


    //Like Comment
    @PUT
    @Produces(MediaType.APPLICATION_XHTML_XML)
    @Path("/{id}/{userId}/")
    public Response likeComment(@PathParam("id") int id,
                                @PathParam("userId") int userId,
                                String text
    ) {
        boolean like = Boolean.parseBoolean(text);
        boolean liked = controller.likeComment(id, userId, like);
        try {
            return Response.ok(mapper.writeValueAsString(liked)).build();
        } catch (Exception e) {
            logger.error(e);
        }

        return Response.serverError().build();
    }


}