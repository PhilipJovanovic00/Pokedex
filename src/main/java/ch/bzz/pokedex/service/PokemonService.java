package ch.bzz.pokedex.service;

/**
 * Service for the Pokemon Class
 */

import ch.bzz.pokedex.data.DataHandler;
import ch.bzz.pokedex.model.Pokemon;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
        List<Pokemon> pokemonList = DataHandler.getInstance().readAllPokemon();
        Response response = Response
                .status(200)
                .entity(pokemonList)
                .build();
        return response;
    }

    /**
     * Reads the Pokemon with the given id
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
            pokemon = DataHandler.getInstance().readPokemonById(pokemonId);
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

}
