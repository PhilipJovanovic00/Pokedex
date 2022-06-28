package ch.bzz.pokedex.service;

/**
 * Service for the Pokemon Class
 */

import ch.bzz.pokedex.data.DataHandler;
import ch.bzz.pokedex.model.Pokemon;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.crypto.Data;
import java.text.NumberFormat;
import java.util.List;

/**
 * Gets the instance of the DataHandler and builds the whole list of Pokemon
 */
@Path("pokemon")
public class PokemonService {

    //@GET
    @PermitAll
    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listPokemon(
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus = 200;
        if (userRole == null || userRole.equals("guest")){
            httpStatus = 403;
        }
        List<Pokemon> pokemonList = DataHandler.readAllPokemon();
        Response response = Response
                .status(httpStatus)
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
            @NotNull
            @QueryParam("id") int pokemonId,
            @CookieParam("userRole") String userRole

     ){
        Pokemon pokemon = null;
        int httpStatus = 200;
        if (userRole == null || userRole.equals("guest")){
            httpStatus = 403;
        } else {
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
        }
        Response response = Response
                .status(httpStatus)
                .entity(pokemon)
                .build();
        return response;
    }
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createPokemon(
            @Valid @BeanParam Pokemon pokemon,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus;
        if (userRole == null || userRole.equals("guest") ||userRole.equals("user")){
            httpStatus = 403;
        } else {
            try {
                DataHandler.insertPokemon(pokemon);
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
    public Response updatePokemon(
            @Valid @BeanParam Pokemon pokemon,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus = 200;
        if (userRole == null || userRole.equals("guest") ||userRole.equals("user")){
            httpStatus = 403;
        } else {
            if (pokemon != null) {
                pokemon.setId(pokemon.getId());
                pokemon.setName(pokemon.getName());
                DataHandler.updatePokemon();
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
    public Response deletePokemon(
            @NotNull
            @QueryParam("id") int pokemonId,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus = 200;
        if (userRole == null || userRole.equals("guest") || userRole.equals("user")){
            httpStatus = 403;
        } else{
            if (!DataHandler.deletePokemon(pokemonId)) {
                httpStatus = 410;
            }
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
}
