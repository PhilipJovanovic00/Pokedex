package ch.bzz.pokedex.model;

/**
 * Model Class for the Types of a Pokemon
 */

public class Type {

    private int typeId;
    private String typeName;

    /**
     * Getter for the TypeId
     *
     * @return typeId
     */
    public int getTypeId() {
        return typeId;
    }

    /**
     * Setter for the TypeId
     *
     * @param typeId
     */
    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    /**
     * Getter for the TypeName
     *
     * @return
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * Setter for the TypeName
     *
     * @param typeName
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
