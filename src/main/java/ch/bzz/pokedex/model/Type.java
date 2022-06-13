package ch.bzz.pokedex.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

/**
 * Model Class for the Types of a Pokemon
 */

public class Type {

    @NotNull
    private int typeId;
    @FormParam("typeName")
    @NotEmpty
    @Size(min = 1, max = 15)
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
