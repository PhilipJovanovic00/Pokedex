package ch.bzz.pokedex.service;

import ch.bzz.pokedex.data.DataHandler;
import ch.bzz.pokedex.model.Category;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("category")
public class CategoryService {

    //@GET
    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listCategory(){
        List<Category> categoryList = DataHandler.getInstance().readAllCategories();
        Response response = Response
                .status(200)
                .entity(categoryList)
                .build();
        return response;
    }
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readCategory(
            @QueryParam("id") int categoryId
    ) {
        Category category = DataHandler.getInstance().readCategoryById(categoryId);
        Response response = Response
                .status(200)
                .entity(category)
                .build();
        return response;


    }

}
