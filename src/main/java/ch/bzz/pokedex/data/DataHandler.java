package ch.bzz.pokedex.data;

import ch.bzz.pokedex.model.Category;
import ch.bzz.pokedex.model.Pokemon;
import ch.bzz.pokedex.model.Type;
import ch.bzz.pokedex.service.Config;


import com.fasterxml.jackson.databind.ObjectMapper;
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
     * @return
     */
    public static DataHandler getInstance() {
        if (instance == null)
            instance = new DataHandler();
        return instance;
    }


    /**
     * reads all books
     * @return list of books
     */
    public List<Pokemon> readAllPokemon() {
        return getPokemonList();
    }

    /**
     * reads a book by its uuid
     * @param pokemonId
     * @return the Book (null=not found)
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
    public List<Type> readAllType() {
        return getTypeList();
    }
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
     * reads all Publishers
     * @return list of publishers
     */
    public List<Category> readAllCategories() {

        return getCategoryList();
    }

    /**
     * reads a publisher by its uuid
     * @param categoryId
     * @return the Publisher (null=not found)
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
     * reads the books from the JSON-file
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
     * reads the publishers from the JSON-file
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
    private void readTypeJSON(){
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
     * gets bookList
     *
     * @return value of bookList
     */
    private List<Pokemon> getPokemonList() {
        return pokemonList;
    }
    private List<Type> getTypeList() {
        return typeList;
    }

    /**
     * sets bookList
     *
     * @param pokemonList the value to set
     */
    private void setPokemonList(List<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }
    private void setTypeList(List<Type> typeList) {
        this.typeList = typeList;
    }

    /**
     * gets publisherList
     *
     * @return value of publisherList
     */
    private List<Category> getCategoryList() {
        return categoryList;
    }

    /**
     * sets publisherList
     *
     * @param categoryList the value to set
     */
    private void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }


}
