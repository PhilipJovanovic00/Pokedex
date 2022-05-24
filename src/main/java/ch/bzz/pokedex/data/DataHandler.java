package ch.bzz.pokedex.data;

/**
 * This class is used to handle the data.
 */

//Import of some Classes that are needed

import ch.bzz.pokedex.model.Category;
import ch.bzz.pokedex.model.Pokemon;
import ch.bzz.pokedex.model.Type;
import ch.bzz.pokedex.service.Config;

//Import of the Json-Library
import com.fasterxml.jackson.databind.ObjectMapper;

//Import of other crucial libraries such as arrays, lists, etc.
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataHandler {

    private static DataHandler instance = null;
    private List<Pokemon> pokemonList;
    private List<Category> categoryList;

    private List<Type> typeList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
        setCategoryList(new ArrayList<>());
        readCategoryJSON();
        setPokemonList(new ArrayList<>());
        readPokemonJSON();
        setTypeList(new ArrayList<>());
        readTypeJSON();
    }

    /**
     * gets the only instance of this class
     *
     * @return
     */
    public static DataHandler getInstance() {
        if (instance == null)
            instance = new DataHandler();
        return instance;
    }


    /**
     * reads all Pokemon
     *
     * @return list of Pokemon
     */
    public List<Pokemon> readAllPokemon() {
        return getPokemonList();
    }

    /**
     * reads a Pokemon by its ID
     *
     * @param pokemonId
     * @return the Pokemon (null=not found)
     */
    public Pokemon readPokemonById(int pokemonId) {
        Pokemon pokemon = null;
        for (Pokemon entry : getPokemonList()) {
            if (entry.getId() == pokemonId) {
                pokemon = entry;
            }
        }
        return pokemon;
    }

    /**
     * reads all Types
     *
     * @return list of Types
     */
    public List<Type> readAllType() {
        return getTypeList();
    }

    /**
     * reads a Type by its ID
     *
     * @param typeId
     * @return the Type (null=not found)
     */
    public Type readTypeById(int typeId) {
        Type type = null;
        for (Type entry : getTypeList()) {
            if (entry.getTypeId() == typeId) {
                type = entry;
            }
        }
        return type;
    }

    /**
     * reads all Categories
     *
     * @return list of categories
     */
    public List<Category> readAllCategories() {

        return getCategoryList();
    }

    /**
     * reads a category by its ID
     *
     * @param categoryId
     * @return the Category (null=not found)
     */
    public Category readCategoryById(int categoryId) {
        Category category = null;
        for (Category entry : getCategoryList()) {
            if (entry.getCategoryId() == categoryId) {
                category = entry;
            }
        }
        return category;
    }

    /**
     * reads the Pokemon from the JSON-file
     */
    private void readPokemonJSON() {
        try {
            String path = Config.getProperty("pokemonJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Pokemon[] pokemons = objectMapper.readValue(jsonData, Pokemon[].class);
            for (Pokemon pokemon : pokemons) {
                getPokemonList().add(pokemon);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the categories from the JSON-file
     */
    private void readCategoryJSON() {
        try {
            String path = Config.getProperty("categoryJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Category[] categories = objectMapper.readValue(jsonData, Category[].class);
            for (Category category : categories) {
                getCategoryList().add(category);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the types from the JSON-file
     */
    private void readTypeJSON() {
        try {
            String path = Config.getProperty("typeJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Type[] types = objectMapper.readValue(jsonData, Type[].class);
            for (Type type : types) {
                getTypeList().add(type);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * gets pokemonList
     *
     * @return value of pokemonList
     */
    private List<Pokemon> getPokemonList() {
        return pokemonList;
    }

    /**
     * gets typeList
     *
     * @return value of typeList
     */
    private List<Type> getTypeList() {
        return typeList;
    }

    /**
     * sets pokemonList
     *
     * @param pokemonList the value to set
     */
    private void setPokemonList(List<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }

    /**
     * sets typeList
     *
     * @param typeList the value to set
     */
    private void setTypeList(List<Type> typeList) {
        this.typeList = typeList;
    }

    /**
     * gets categoryList
     *
     * @return value of categoryList
     */
    private List<Category> getCategoryList() {
        return categoryList;
    }

    /**
     * sets categoryList
     *
     * @param categoryList the value to set
     */
    private void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }


}
