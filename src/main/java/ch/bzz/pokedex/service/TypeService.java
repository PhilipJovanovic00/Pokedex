package ch.bzz.pokedex.service;

/**
 * Service for the Type Class.
 */

import ch.bzz.pokedex.data.DataHandler;
import ch.bzz.pokedex.model.Pokemon;
import ch.bzz.pokedex.model.Type;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


/**
 * Gets the instance of the Type and builds it with the whole list of Types.
 */
@Path("type")
public class TypeService {

    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listType() {
        List<Type> typeList = DataHandler.readAllType();
        Response response = Response
                .status(200)
                .entity(typeList)
                .build();
        return response;
    }

    /**
     * Reads the Type with the given id
     *
     * @param typeId
     * @return
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readType(
            @QueryParam("id") int typeId
    ) {
        Type type = null;
        int httpStatus;

        try {
            type = DataHandler.readTypeById(typeId);
            if (type == null) {
                httpStatus = 404;
            } else {
                httpStatus = 200;
            }
        } catch (IllegalArgumentException argEx) {
            httpStatus = 400;
        }
        Response response = Response
                .status(200)
                .entity(type)
                .build();
        return response;
    }

    @POST
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createType(
            @FormParam("name") String name,
            @FormParam("id") int id
    ) {
        Type type = new Type();
        int httpStatus;
        try {
            type.setTypeId(id);
            type.setTypeName(name);
            DataHandler.insertType(type);
            httpStatus = 200;
        } catch (IllegalArgumentException argEx) {
            httpStatus = 400;
        }
        Response response = Response
                .status(httpStatus)
                .entity("")
                .build();
        return response;
    }

    @PUT
    @Path("update")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateType(
            @FormParam("id") int id,
            @FormParam("name") String name
    ) {
        int httpStatus = 200;
        Type type = DataHandler.readTypeById(id);
        if(type != null) {
            type.setTypeId(id);
            type.setTypeName(name);

            DataHandler.updateType();
        } else {
            httpStatus = 410;
        }
        return Response
                .status(200)
                .entity("")
                .build();
    }

    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteType(
            @QueryParam("id") int id
    ) {
        int httpStatus = 200;
        if(!DataHandler.deleteType(id)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

}
