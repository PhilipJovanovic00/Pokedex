package ch.bzz.pokedex.service;

/**
 * Service for the Type Class.
 */

import ch.bzz.pokedex.data.DataHandler;
import ch.bzz.pokedex.model.Type;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
        List<Type> typeList = DataHandler.getInstance().readAllType();
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
            type = DataHandler.getInstance().readTypeById(typeId);
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

}
