package com.server;

import com.server.controller.LocationController;
import com.server.datatype.Event;
import com.server.datatype.Location;
import com.sun.jersey.core.header.ContentDisposition;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;

/**
 * Created by jp on 02.11.15.
 */
@Path("/location")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
@Interceptors(AuditingInterceptor.class)
public class LocationRestService
        extends ApplicationRestService {

    @EJB
    private LocationController controller;
    private Logger logger = Logger.getLogger(this.getClass().getName());


    //Get location
    @GET
    @Produces(MediaType.APPLICATION_XHTML_XML)
    @Path("/{id}")
    public Response get(@PathParam("id") int id) {

        Location location = controller.getLocationById(id);

        try {
            return Response.ok(objectMapper.writeValueAsString(location)).build();
        } catch (IOException e) {
            logger.error(e);
        }
        return Response.serverError().build();

    }


    //get isUpToDate
    @GET
    @Produces(MediaType.APPLICATION_XHTML_XML)
    @Path("/{id}/{eventId}/new")
    public Response isUpToDate(@PathParam("id") int id,
                               @PathParam("eventId") int eventId) {

        boolean isUpToDate = controller.isUpToDate(id, eventId);
        try {
            return Response.ok(objectMapper.writeValueAsString(isUpToDate)).build();
        } catch (IOException e) {
            logger.error(e);
        }
        return Response.serverError().build();

    }


    //Get first Posts
    @GET
    @Produces(MediaType.APPLICATION_XHTML_XML)
    @Path("/{id}/{postNum}/{userId}/new")
    public Response getPost(@PathParam("id") int id,
                            @PathParam("postNum") int postNum,
                            @PathParam("userId") int userId
    ) {
        Event[] events = controller.getFirstPosts(id, userId, postNum);
        try {
            return Response.ok(objectMapper.writeValueAsString(events)).build();
        } catch (IOException e) {
            logger.error(e);
        }
        return Response.serverError().build();

    }


    //Get following post from last PostId on
    @GET
    @Produces(MediaType.APPLICATION_XHTML_XML)
    @Path("/{id}/{postNum}/{userId}/{lastPostId}/new")
    public Response getPost(@PathParam("id") int id,
                            @PathParam("postNum") int postNum,
                            @PathParam("userId") int userId,
                            @PathParam("lastPostId") int lastPostId
    ) {
        Event[] events = controller.getNextPosts(id, userId, postNum, lastPostId);
        try {
            return Response.ok(objectMapper.writeValueAsString(events)).build();
        } catch (IOException e) {
            logger.error(e);
        }
        return Response.serverError().build();
    }


    //Get popular post
    @GET
    @Produces(MediaType.APPLICATION_XHTML_XML)
    @Path("/{id}/{postNum}/{userId}/popular")
    public Response getPostPopular(@PathParam("id") int id,
                                   @PathParam("postNum") int postNum,
                                   @PathParam("userId") int userId
    ) {
        Event[] events = controller.getNextPostsOLocactionByPopularity(id, userId, postNum);
        try {
            return Response.ok(mapper.writeValueAsString(events)).build();
        } catch (Exception e) {
            logger.error(e);
        }

        return Response.serverError().build();

    }


    //Get liked
    @GET
    @Produces(MediaType.APPLICATION_XHTML_XML)
    @Path("/{id}/{userId}/isLiked")
    public Response getPost(@PathParam("id") int id,
                            @PathParam("userId") int userId
    ) {
        boolean liked = controller.getLiked(id, userId);
        try {
            return Response.ok(objectMapper.writeValueAsString(liked)).build();
        } catch (IOException e) {
            logger.error(e);
        }
        return Response.serverError().build();
    }


    //Get like count of a Location
    @GET
    @Produces(MediaType.APPLICATION_XHTML_XML)
    @Path("/{id}/likeCount")
    public Response getPost(@PathParam("id") int id
    ) {
        int likeCount = controller.getLikeCount(id);
        try {
            return Response.ok(objectMapper.writeValueAsString(likeCount)).build();
        } catch (IOException e) {
            logger.error(e);
        }
        return Response.serverError().build();
    }


    @GET
    @Produces(MediaType.APPLICATION_XHTML_XML)
    @Path("/all")
    public Response getAll() {

        Location[] locationBeans = controller.getAllLocation();

        try {
            return Response.ok(objectMapper.writeValueAsString(locationBeans)).build();
        } catch (IOException e) {
            logger.error(e);
        }
        return Response.serverError().build();
    }


    //isOwner
    @GET
    @Produces(MediaType.APPLICATION_XHTML_XML)
    @Path("/{id}/{userId}/isOwner")
    public Response isOwner(@PathParam("id") int id,
                            @PathParam("userId") int userId
    ) {
        boolean isOwner = controller.isOwner(id, userId);
        try {
            return Response.ok(objectMapper.writeValueAsString(isOwner)).build();
        } catch (IOException e) {
            logger.error(e);
        }
        return Response.serverError().build();
    }

    //like Location
    @PUT
    @Produces(MediaType.APPLICATION_XHTML_XML)
    @Path("/{id}/{userId}/")
    public Response likePost(@PathParam("id") int id,
                             @PathParam("userId") int userId,
                             String text
    ) {
        boolean like = Boolean.parseBoolean(text);
        boolean liked = controller.likeLocation(id, userId, like);
        try {
            return Response.ok(mapper.writeValueAsString(liked)).build();
        } catch (Exception e) {
            logger.error(e);
        }

        return Response.serverError().build();
    }


    @PUT
    @Produces(MediaType.APPLICATION_XHTML_XML)
    @Path("{userId}/changeDescription")
    public Response changedDescription(@PathParam("userId") int userId, Location location) {


        try {
            Location location1 = controller.changeDescription(userId, location);
            return Response.ok(objectMapper.writeValueAsString(location1)).build();
        } catch (IOException e) {
            logger.error(e);
        }
        return Response.serverError().build();


    }


    @PUT
    @Produces(MediaType.APPLICATION_XHTML_XML)
    @Path("/{id}/{tagId}/addTag")
    public Response addTag(@PathParam("id") int id,
                           @PathParam("tagId") int tagId) {

        Location location = controller.addTag(id, tagId);

        try {
            return Response.ok(objectMapper.writeValueAsString(location)).build();
        } catch (IOException e) {
            logger.error(e);
        }
        return Response.serverError().build();


    }


    @PUT
    @Produces(MediaType.APPLICATION_XHTML_XML)
    @Path("/{id}/{tagId}/removeTag")
    public Response removeTag(@PathParam("id") int id,
                              @PathParam("tagId") int tagId) {

        Location location = controller.removeTag(id, tagId);

        try {
            return Response.ok(objectMapper.writeValueAsString(location)).build();
        } catch (IOException e) {
            logger.error(e);
        }
        return Response.serverError().build();


    }


    //Authenticate User
    @PUT
    @Produces(MediaType.APPLICATION_XHTML_XML)
    @Path("/{id}/{userId}/{userMail}/authenticate")
    public Response authenticateUser(@PathParam("id") int id,
                                     @PathParam("userId") int userId,
                                     @PathParam("userMail") String userMail,

                                     String password) {

        Boolean success = controller.authenticateUser(id, userId, userMail, password);
        Boolean isAdmin = false;
        Boolean [] rights = new Boolean[]{success, isAdmin};
        logger.info("Restservice");

        try {
            return Response.ok(objectMapper.writeValueAsString(rights)).build();
        } catch (IOException e) {
            logger.error(e);
        }
        return Response.serverError().build();

    }


    //Create Event
    @POST
    @Produces(MediaType.APPLICATION_XHTML_XML)
    @Path("/{id}/{userId}")
    public Response createPost(@PathParam("id") int id,
                               @PathParam("userId") int userId,
                               String text) {

        Event event = controller.createEvent(id, userId, text);

        try {
            return Response.ok(objectMapper.writeValueAsString(event)).build();
        } catch (IOException e) {
            logger.error(e);
        }
        return Response.serverError().build();

    }


    //Create Event over Web Application
    @POST
    @Produces(MediaType.APPLICATION_XHTML_XML)
    @Path("/{id}/{userId}/{content}")
    public Response createPostWebsite(@PathParam("id") int id,
                                      @PathParam("userId") int userId,
                                      @PathParam("content") String text) {

        Event event = controller.createEvent(id, userId, text);

        try {
            return Response.ok(objectMapper.writeValueAsString(event)).build();
        } catch (IOException e) {
            logger.error(e);
        }
        return Response.serverError().build();

    }


    //create Location with Webservice
    @POST
    @Produces(MediaType.APPLICATION_XHTML_XML)
    @Path("/create/{name}/{cityId}/{postalCode}/{street}/{phoneNumber}/{GPS}/{description}/{houseNumber}/{userMail}/{openHours}/")
    public Response create(@PathParam("name") String name,
                           @PathParam("cityId") int cityId,
                           @PathParam("postalCode") String postalCode,
                           @PathParam("street") String street,
                           @PathParam("phoneNumber") String phoneNumber,
                           @PathParam("GPS") String gPS,
                           @PathParam("description") String description,
                           @PathParam("houseNumber") String houseNumber,
                           @PathParam("userMail") String userMail,
                           @PathParam("openHours") String openHours,
                           String password) {


        Location location = new Location(
                controller.createLocation(name, cityId, postalCode, street, houseNumber, phoneNumber, description, gPS, userMail, password, openHours));


        try {
            return Response.ok(objectMapper.writeValueAsString(location)).build();
        } catch (IOException e) {
            logger.error(e);
        }
        return Response.serverError().build();
    }

    @POST
    @Path("/{locationId}/uploadImage")
    @Produces(MediaType.APPLICATION_XHTML_XML)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadImage(FormDataMultiPart form,
                                @PathParam("locationId") int locationId) {

        Location location = controller.saveToDisk(locationId, form);


        try {
            return Response.ok(objectMapper.writeValueAsString(location)).build();
        } catch (IOException e) {
            logger.error(e);
        }
        return Response.serverError().build();

    }


    @GET
    @Path("/{locationId}/downloadImage")
    @Produces("image/png")
    public Response downloadImage(@PathParam("locationId") int locationId) {

        try {

            File file = new File("/home/glassfish/imgs/" + locationId + ".png");
            Response.ResponseBuilder responseBuilder = Response.ok((Object) file);
            responseBuilder.header("Content-Disposition",
                    "attachment; filename=DisplayName-" + locationId + ".png");
            return responseBuilder.build();

        } catch (Exception e) {

            return Response.serverError().build();

        }

    }


}