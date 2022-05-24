package ch.bzz.pokedex.model;

/*
 * Model Class for the Categories a pokemon has
 */

public class Category {

    /*
     * The id of the Category
     * the name of the Category
     */


    private int categoryId;
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
