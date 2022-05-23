package ch.bzz.pokedex.model;

/**
 * Model Class for the Types of a Pokemon
 */

public class Type {

    private int typeId;
    private String typeName;

    /**
     * Getters and Setters
     */
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
