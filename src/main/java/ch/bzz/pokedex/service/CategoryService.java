package ch.bzz.pokedex.service;

/**
 * Service for the Category Class
 */

import ch.bzz.pokedex.data.DataHandler;
import ch.bzz.pokedex.model.Category;
import ch.bzz.pokedex.model.Pokemon;

import javax.annotation.security.PermitAll;
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
    @PermitAll
    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listCategory(
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus = 200;
        if (userRole == null || userRole.equals("guest")){
            httpStatus = 403;
        }
        List<Category> categoryList = DataHandler.readAllCategories();
        Response response = Response
                .status(httpStatus)
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
            @QueryParam("categoryId") int categoryId,
            @CookieParam("userRole") String userRole
    ) {
        Category category = null;
        int httpStatus = 200;
        if (userRole == null || userRole.equals("guest")){
            httpStatus = 403;
        } else {
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
        }
        Response response = Response
                .status(httpStatus)
                .entity(category)
                .build();
        return response;


    }


    /**
     * Creates a new category
     * @param category
     * @param userRole
     * @return
     */
    @POST
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCategory(
            @Valid @BeanParam Category category,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus;
        if (userRole == null || userRole.equals("guest") ||userRole.equals("user")){
            httpStatus = 403;
        } else {
            try {
                DataHandler.insertCategory(category);
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

    /**
     * Updates the category with the given id
     * @param category
     * @param userRole
     * @return
     */
    @PUT
    @Path("update")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCategory(
            @Valid @BeanParam Category category,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus = 200;
        if (userRole == null || userRole.equals("guest") ||userRole.equals("user")){
            httpStatus = 403;
        } else {
            if (category != null) {
                category.setCategoryId(category.getCategoryId());
                category.setCategoryName(category.getCategoryName());
                DataHandler.updateCategory();
            } else {
                httpStatus = 410;
            }
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * Deletes the category with the given id
     * @param id
     * @param userRole
     * @return
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteCategory(
            @NotNull
            @QueryParam("categoryId") int id,
            @CookieParam("userRole") String userRole
    ) {
        int httpStatus = 200;
        if (userRole == null || userRole.equals("guest") || userRole.equals("user")){
            httpStatus = 403;
        } else {
            if (!DataHandler.deleteCategory(id)) {
                httpStatus = 410;
            }
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

}
