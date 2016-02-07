package com.server;

import com.server.controller.TagController;
import com.server.datatype.Event;
import com.server.datatype.Location;
import com.server.datatype.Tag;
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
@Path("/tag")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
@Interceptors(AuditingInterceptor.class)
public class TagRestService
        extends ApplicationRestService {

    @EJB
    private TagController controller;
    private Logger logger = Logger.getLogger(this.getClass().getName());


    @GET
    @Produces(MediaType.APPLICATION_XHTML_XML)
    @Path("/{id}")
    public Response get(@PathParam("id") int id) {

        Tag tag = controller.getTag(id);
        try {
            return Response.ok(objectMapper.writeValueAsString(tag)).build();
        } catch (IOException e) {
            logger.error(e);
        }
        return Response.serverError().build();

    }

    //get isUpToDate
    @GET
    @Produces(MediaType.APPLICATION_XHTML_XML)
    @Path("/{id}/{cityId}/{eventId}/new")
    public Response isUpToDate(@PathParam("id") int id,
                               @PathParam("cityId") int cityId,
                               @PathParam("eventId") int eventId) {

        boolean isUpToDate = controller.isUpToDate(id, cityId, eventId);
        try {
            return Response.ok(objectMapper.writeValueAsString(isUpToDate)).build();
        } catch (IOException e) {
            logger.error(e);
        }
        return Response.serverError().build();

    }


    @GET
    @Produces(MediaType.APPLICATION_XHTML_XML)
    @Path("/{id}/{cityId}/{postNum}/{userId}/new")
    public Response getPost(@PathParam("id") int id,
                            @PathParam("cityId") int cityId,
                            @PathParam("postNum") int postNum,
                            @PathParam("userId") int userId

    ) {
        Event[] events = controller.getFirstEvents(id, cityId, userId, postNum);
        try {
            return Response.ok(mapper.writeValueAsString(events)).build();
        } catch (Exception e) {

        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }


    //Get following post from last PostId on with tag
    @GET
    @Produces(MediaType.APPLICATION_XHTML_XML)
    @Path("/{id}/{cityId}/{postNum}/{userId}/{lastPostId}/new")
    public Response getPost(@PathParam("id") int id,
                            @PathParam("cityId") int cityId,
                            @PathParam("postNum") int postNum,
                            @PathParam("userId") int userId,
                            @PathParam("lastPostId") int lastPostId
    ) {
        Event[] events = controller.getNextEvents(id, cityId, userId, postNum, lastPostId);
        try {
            return Response.ok(mapper.writeValueAsString(events)).build();
        } catch (Exception e) {

        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }


    //Get popular post
    @GET
    @Produces(MediaType.APPLICATION_XHTML_XML)
    @Path("/{id}/{postNum}/{userId}/popular")
    public Response getPostPopular(@PathParam("id") int id,
                                   @PathParam("cityId") int cityId,
                                   @PathParam("postNum") int postNum,
                                   @PathParam("userId") int userId
    ) {
        Event[] events = controller.getNextTagEventsByPopularity(id, cityId, userId, postNum);
        try {
            return Response.ok(mapper.writeValueAsString(events)).build();
        } catch (Exception e) {
            logger.error(e);
        }

        return Response.serverError().build();

    }


    //Get first Location with tag
    @GET
    @Produces(MediaType.APPLICATION_XHTML_XML)
    @Path("/{id}/{cityId}/location/{locationNum}/")
    public Response getLocation(@PathParam("id") int id,
                                @PathParam("cityId") int cityId,
                                @PathParam("locationNum") int locationNum
    ) {
        Location[] locations = controller.getFirstLocations(id, cityId, locationNum);
        try {
            return Response.ok(mapper.writeValueAsString(locations)).build();
        } catch (Exception e) {

        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }


    //Get following Location from lastLocationId on with tag
    @GET
    @Produces(MediaType.APPLICATION_XHTML_XML)
    @Path("/{id}/{cityId}/location/{locationNum}/{lastLocationId}")
    public Response getLocation(@PathParam("id") int id,
                                @PathParam("cityId") int cityId,
                                @PathParam("locationNum") int locationNum,
                                @PathParam("lastLocationId") int lastLocationId
    ) {
        Location[] locations = controller.getNextLocations(id, cityId, locationNum, lastLocationId);
        try {
            return Response.ok(mapper.writeValueAsString(locations)).build();
        } catch (Exception e) {

        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }


    @GET
    @Produces(MediaType.APPLICATION_XHTML_XML)
    @Path("/all")
    public Response getAll() {

        Tag[] tags = controller.getAllTags();

        try {
            return Response.ok(objectMapper.writeValueAsString(tags)).build();
        } catch (IOException e) {
            logger.error(e);
        }
        return Response.serverError().build();
    }


    //Create Tag
    @POST
    @Produces(MediaType.APPLICATION_XHTML_XML)
    @Path("/{tagName}")
    public Response createTag(@PathParam("tagName") String name) {
        Tag tag = controller.createTag(name);
        try {
            return Response.ok(mapper.writeValueAsString(tag)).build();
        } catch (Exception e) {
            logger.error(e);
        }

        return Response.serverError().build();
    }

}