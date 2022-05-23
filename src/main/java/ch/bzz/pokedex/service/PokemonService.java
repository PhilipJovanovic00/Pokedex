package ch.bzz.pokedex.service;

import ch.bzz.pokedex.data.DataHandler;
import ch.bzz.pokedex.model.Pokemon;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("pokemon")
public class PokemonService {

    //@GET
    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listPokemon(){
        List<Pokemon> pokemonList = DataHandler.getInstance().readAllPokemon();
        Response response = Response
                .status(200)
                .entity(pokemonList)
                .build();
        return response;
    }

    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readPokemon(
            @QueryParam("id") int pokemonId
    ) {
        Pokemon pokemon = DataHandler.getInstance().readPokemonById(pokemonId);
        Response response = Response
                .status(200)
                .entity(pokemon)
                .build();
        return response;


    }

}
