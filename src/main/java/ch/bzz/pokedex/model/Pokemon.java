package ch.bzz.pokedex.model;

/**
 * Model Class for a Pokemon
 */

import java.util.ArrayList;
import java.util.List;

public class Pokemon {

    private int id;
    private String name;
    private int categoryId;

    private List<Type> types;

    public Pokemon() {
        this.types = new ArrayList<>();
        Type type = new Type();
        type.setTypeId(1);
        type.setTypeName("Grass");
        this.addType(type);
    }

    /**
     * Getters and Setters
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    /**
     *Methods for adding and removing types
     */

    public void addType(Type type) {
        this.types.add(type);
    }

    public void removeType(Type type) {
        this.types.remove(type);
    }
}
