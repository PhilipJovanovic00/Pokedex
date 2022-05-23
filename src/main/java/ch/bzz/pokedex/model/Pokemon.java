package ch.bzz.pokedex.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
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
    public void addType(Type type){
        this.types.add(type);
    }
    public void removeType(Type type){
        this.types.remove(type);
    }
}
