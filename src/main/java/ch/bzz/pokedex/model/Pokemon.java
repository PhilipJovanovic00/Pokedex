package ch.bzz.pokedex.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class Pokemon {

    @JsonIgnore
    private String categoryName;
    @JsonIgnore
    private int categoryId;

    private List<Pokemon> pokemonList;
    private int ID;
    private String name;


    public List<Pokemon> getPokemonList() {
        return pokemonList;
    }

    public void setPokemonList(List<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }

    /*
    public void setCategoryId(int categoryId) {
        setCategory(new Category()){

        }
    }
*/
    public int getPokemonId() {
        return ID;
    }
    public void setCategory(Category category){
        this.categoryId = category.getCategoryId();
        this.categoryName = category.getCategoryName();
    }

    public void setPokemonId(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryName() {
        return categoryName;
    }
    public int getCategoryId() {
        return categoryId;
    }
}
