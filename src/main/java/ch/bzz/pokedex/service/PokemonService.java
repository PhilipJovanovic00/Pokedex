package ch.bzz.pokedex.service;

/**
 * Service for the Pokemon Class
 */

import ch.bzz.pokedex.data.DataHandler;
import ch.bzz.pokedex.model.Pokemon;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.crypto.Data;
import java.util.List;

/**
 * Gets the instance of the DataHandler and builds the whole list of Pokemon
 */
@Path("pokemon")
public class PokemonService {

    //@GET
    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listPokemon() {
        List<Pokemon> pokemonList = DataHandler.readAllPokemon();
        Response response = Response
                .status(200)
                .entity(pokemonList)
                .build();
        return response;
    }

    /**
     * Reads the Pokemon with the given id
     *
     * @param pokemonId
     * @return
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readPokemon(
            @QueryParam("id") int pokemonId
    ) {
        Pokemon pokemon = null;
        int httpStatus;

        try {
            pokemon = DataHandler.readPokemonById(pokemonId);
            if (pokemon == null) {
                httpStatus = 404;
            } else {
                httpStatus = 200;
            }
        } catch (IllegalArgumentException argEx) {
            httpStatus = 400;
        }
        Response response = Response
                .status(httpStatus)
                .entity(pokemon)
                .build();
        return response;
    }
    @POST
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPokemon(
            @FormParam("name") String name,
            @FormParam("id") int id
    ) {
        Pokemon pokemon = new Pokemon();
        int httpStatus;
        try {
            pokemon.setId(id);
            pokemon.setName(name);
            DataHandler.insertPokemon(pokemon);
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
    public Response updatePokemon(
            @FormParam("id") int id,
            @FormParam("categoryId") int categoryId,
            @FormParam("name") String name,
            @FormParam("typeId") int typeId,
            @FormParam("typeName") int typeName
    ) {
        int httpStatus = 200;
        Pokemon pokemon = DataHandler.readPokemonById(id);
        if(pokemon != null) {
            pokemon.setId(id);
            pokemon.setName(name);

            DataHandler.updatePokemon();
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
    public Response deletePokemon(
            @QueryParam("id") int id
    ) {
        int httpStatus = 200;
        if(!DataHandler.deletePokemon(id)) {
            httpStatus = 410;
    }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

}
