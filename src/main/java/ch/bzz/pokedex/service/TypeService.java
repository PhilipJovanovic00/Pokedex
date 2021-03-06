package ch.bzz.pokedex.service;

import ch.bzz.pokedex.data.DataHandler;
import ch.bzz.pokedex.model.Type;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("type")
public class TypeService {

    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listType(){
        List<Type> typeList = DataHandler.getInstance().readAllType();
        Response response = Response
                .status(200)
                .entity(typeList)
                .build();
        return response;
    }

    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readType(
            @QueryParam("id") int typeId
    ) {
        Type type = DataHandler.getInstance().readTypeById(typeId);
        Response response = Response
                .status(200)
                .entity(type)
                .build();
        return response;


    }

}
