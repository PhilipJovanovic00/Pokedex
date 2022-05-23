package ch.bzz.pokedex.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class Pokemon {
/**
    @JsonIgnore
    private String categoryName;
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

    public int getID() {
        return ID;
    }


    public void setCategoryId(int categoryId){
        this.categoryId = categoryId;
    }

    public void setID(int ID) {
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
    }*/

    private Integer iD;
    private String name;
    private int categoryId;

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
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
}
