package ch.bzz.pokedex.model;

/*
 * Model Class for a Pokemon
 */
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import java.util.ArrayList;
import java.util.List;

public class Pokemon {

    @NotNull
    private int id;
    @FormParam("name")
    @NotEmpty
    @Size(min = 3, max = 11)
    private String name;
    @FormParam("categoryId")
    @NotNull
    private int categoryId;
    @FormParam("typeId")
    @NotNull
    private int typeId;
    @FormParam("typeName")
    @NotEmpty
    @Size(min = 1, max = 15)
    private String typeName;
    private List<Type> types;

    //Constructor
    public Pokemon() {
        this.types = new ArrayList<>();
    }

    /**
     * Getter for the id of the pokemon
     *
     * @return id
     */
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for the name of the pokemon
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * setter for the name of the pokemon
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the categoryId of the pokemon
     *
     * @return categoryId
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * setter for the categoryId of the pokemon
     *
     * @param categoryId
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Getter for the types of the pokemon
     *
     * @return types
     */
    public List<Type> getTypes() {
        return types;
    }

    /**
     * Setter for the types of the pokemon
     *
     * @param types
     */
    public void setTypes(List<Type> types) {
        this.types = types;
    }

    /**
     * Method to add a type to the pokemon
     *
     * @param type
     */

    public void addType(Type type) {
        this.types.add(type);
    }

    /**
     * Method to remove a type from the pokemon
     *
     * @param type
     */
    public void removeType(Type type) {
        this.types.remove(type);
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
