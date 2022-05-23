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


/*
 * Getter and Setter for the Category
 */
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
