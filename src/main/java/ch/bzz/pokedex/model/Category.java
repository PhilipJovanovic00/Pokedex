package ch.bzz.pokedex.model;

/*
 * Model Class for the Categories a pokemon has
 */

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

public class Category {

    /*
     * The id of the Category
     * the name of the Category
     */


    private int categoryId;
    @FormParam("categoryName")
    @NotEmpty
    @Size(min = 3, max = 11)
    private String categoryName;


    /**
     * Getter for the CategoryId
     *
     * @return
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * Setter for the CategoryId
     *
     * @param categoryId
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Getter for the CategoryName
     *
     * @return
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Setter for the CategoryName
     *
     * @param categoryName
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
