package ch.bzz.pokedex.service;

/**
 * Service for the Category Class
 */

import ch.bzz.pokedex.data.DataHandler;
import ch.bzz.pokedex.model.Category;
import ch.bzz.pokedex.model.Pokemon;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Gets the instance of the Category class and builds the whole list of categories
 */
@Path("category")
public class CategoryService {

    //@GET
    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listCategory() {
        List<Category> categoryList = DataHandler.readAllCategories();
        Response response = Response
                .status(200)
                .entity(categoryList)
                .build();
        return response;
    }

    /**
     * Reads the category with the given id
     *
     * @param categoryId
     * @return
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readCategory(
            @NotNull
            @QueryParam("id") int categoryId
    ) {
        Category category = null;
        int httpStatus;

        try {
            category = DataHandler.readCategoryById(categoryId);
            if (category == null) {
                httpStatus = 404;
            } else {
                httpStatus = 200;
            }
        } catch (IllegalArgumentException argEx) {
            httpStatus = 400;
        }
        Response response = Response
                .status(200)
                .entity(category)
                .build();
        return response;


    }

    @POST
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCategory(
            @Valid @BeanParam Category category
    ) {
        int httpStatus;
        try {
            DataHandler.insertCategory(category);
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
    public Response updateCategory(
            @Valid @BeanParam Category category
    ) {
        int httpStatus = 200;
        if(category != null) {
            category.setCategoryId(category.getCategoryId());
            category.setCategoryName(category.getCategoryName());
            DataHandler.updateCategory();
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
    public Response deleteCategory(
            @NotNull
            @QueryParam("id") int id
    ) {
        int httpStatus = 200;
        if(!DataHandler.deleteCategory(id)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

}
