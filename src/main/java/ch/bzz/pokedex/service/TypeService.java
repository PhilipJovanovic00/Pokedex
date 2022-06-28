package ch.bzz.pokedex.service;

/**
 * Service for the Type Class.
 */

import ch.bzz.pokedex.data.DataHandler;
import ch.bzz.pokedex.model.Pokemon;
import ch.bzz.pokedex.model.Type;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


/**
 * Gets the instance of the Type and builds it with the whole list of Types.
 */
@Path("type")
public class TypeService {

    @PermitAll
    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listType(
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus = 200;
        if (userRole == null || userRole.equals("guest")){
            httpStatus = 403;
        }
        List<Type> typeList = DataHandler.readAllType();
        Response response = Response
                .status(httpStatus)
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
            @NotNull
            @QueryParam("typeId") int typeId,
            @CookieParam("userRole") String userRole
    ) {
        Type type = null;
        int httpStatus = 200;
        if(userRole == null || userRole.equals("guest")){
            httpStatus = 403;
        } else {
            type = DataHandler.readTypeById(typeId);
        }

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
                .status(httpStatus)
                .entity(type)
                .build();
        return response;
    }

    @POST
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createType(
            @Valid @BeanParam Type type,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus;
        if(userRole == null || userRole.equals("guest") || userRole.equals("user")) {
            httpStatus = 403;
        } else {

            try {
                DataHandler.insertType(type);
                httpStatus = 200;
            } catch (IllegalArgumentException argEx) {
                httpStatus = 400;
            }
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
            @Valid @BeanParam Type type,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus = 200;
        if (userRole == null || userRole.equals("guest") ||userRole.equals("user")){
            httpStatus = 403;
        } else {
            if (type != null) {
                type.setTypeId(type.getTypeId());
                type.setTypeName(type.getTypeName());
                DataHandler.updateType();
            } else {
                httpStatus = 410;
            }
        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteType(
            @NotNull
            @QueryParam("typeId") int id,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus = 200;
        if (userRole == null || userRole.equals("guest") || userRole.equals("user")){
            httpStatus = 403;
        } else {
            if (!DataHandler.deleteType(id)) {
                httpStatus = 410;
            }
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

}
