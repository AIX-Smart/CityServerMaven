package com.server;

/**
 * Created by jp on 02.11.15.
 */

import com.server.controller.CityController;
import com.server.datatype.City;
import com.server.datatype.Event;
import com.server.datatype.Location;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;


@Path("/city")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
@Interceptors(AuditingInterceptor.class)
public class CityRestService
        extends ApplicationRestService {

    @EJB
    private CityController controller;
    private Logger logger = Logger.getLogger(this.getClass().getName());


    //get city
    @GET
    @Produces(MediaType.APPLICATION_XHTML_XML)
    @Path("/{id}")
    public Response get(@PathParam("id") int id) {

        City city = controller.getCity(id);
        try {
            return Response.ok(objectMapper.writeValueAsString(city)).build();
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
    @Path("/{id}/{postNum}/{userId}")
    public Response getPost(@PathParam("id") int id,
                            @PathParam("postNum") int postNum,
                            @PathParam("userId") int userId) {

        Event[] events = controller.getFirstPostsOfCity(id, userId, postNum);
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
    @Path("/{id}/{postNum}/{userId}/{lastPostId}")
    public Response getPost(@PathParam("id") int id,
                            @PathParam("postNum") int postNum,
                            @PathParam("userId") int userId,
                            @PathParam("lastPostId") int lastPostId
    ) {
        Event[] events = controller.getNextPostsOfCity(id, userId, postNum, lastPostId);
        try {
            return Response.ok(mapper.writeValueAsString(events)).build();
        } catch (Exception e) {
            logger.error(e);
        }

        return Response.serverError().build();

    }



    //Get first Posts
    @GET
    @Produces(MediaType.APPLICATION_XHTML_XML)
    @Path("/{id}/{postNum}/{userId}/popular")
    public Response getPostPopular(@PathParam("id") int id,
                            @PathParam("postNum") int postNum,
                            @PathParam("userId") int userId) {

        Event[] events = controller.getFirstPostsOfCityByPopularity(id, userId, postNum);
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
    @Path("/{id}/{postNum}/{userId}/{lastPostId}/popular")
    public Response getPostPopular(@PathParam("id") int id,
                            @PathParam("postNum") int postNum,
                            @PathParam("userId") int userId,
                            @PathParam("lastPostId") int lastPostId
    ) {
        Event[] events = controller.getNextPostsOfCityByPopularity(id, userId, postNum, lastPostId);
        try {
            return Response.ok(mapper.writeValueAsString(events)).build();
        } catch (Exception e) {
            logger.error(e);
        }

        return Response.serverError().build();

    }


    @GET
    @Produces(MediaType.APPLICATION_XHTML_XML)
    @Path("/all")
    public Response getAll() {
        City[] cities = controller.getAll();
        try {
            return Response.ok(objectMapper.writeValueAsString(cities)).build();
        } catch (IOException e) {
            logger.error(e);
        }
        return Response.serverError().build();

    }


    //Get all locations in the city
    @GET
    @Produces(MediaType.APPLICATION_XHTML_XML)
    @Path("/{cityId}/location")
    public Response getLocations(@PathParam("cityId") int cityId) {

        Location[] allLocations = controller.getLocations(cityId);
        try {
            return Response.ok(objectMapper.writeValueAsString(allLocations)).build();
        } catch (IOException e) {
            logger.error(e);
        }
        return Response.serverError().build();

    }


    @GET
    @Produces(MediaType.APPLICATION_XHTML_XML)
    @Path("/search")
    public Response get(String searchText) {

        Location[] locations = controller.getLocationBySearchText(searchText);

        try {
            return Response.ok(objectMapper.writeValueAsString(locations)).build();
        } catch (IOException e) {
            logger.error(e);
        }
        return Response.serverError().build();
    }


    @POST
    @Produces(MediaType.APPLICATION_XHTML_XML)
    @Path("/{cityName}")
    public Response create(@PathParam("cityName") String cityName) {

        City city = controller.createCity(cityName);


        try {
            return Response.ok(objectMapper.writeValueAsString(city)).build();
        } catch (IOException e) {
            logger.error(e);
        }

        return Response.noContent().build();

    }

    //Muss noch diskutiert werden
    @POST
    @Produces(MediaType.APPLICATION_XHTML_XML)
    @Path("{id}/{userId}")
    public Response CreatePost(@PathParam("id") int id,
                               @PathParam("userId") int userId,
                               String text) {
        Event event = controller.createEvent(id, userId, text);

        try {
            return Response.ok(objectMapper.writeValueAsString(event)).build();
        } catch (IOException e) {
            logger.error(e);
        }

        return Response.noContent().build();
    }


}