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
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

//Import of other crucial libraries such as arrays, lists, etc.
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataHandler {

    private static DataHandler instance = null;
    private static List<Pokemon> pokemonList;
    private static List<Category> categoryList;

    private static List<Type> typeList;

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


    //Delete insert and update methods for Pokemon
    public static boolean deletePokemon(int pokemonId){
        Pokemon pokemon = readPokemonById(pokemonId);
        if(pokemon != null){
            getPokemonList().remove(pokemon);
            writePokemonJSON();
            return true;
        } else {
            return false;
        }

    }
    public static void insertPokemon(Pokemon pokemon){
        getPokemonList().add(pokemon);
        writePokemonJSON();
    }
    public static void updatePokemon(){
        writePokemonJSON();
    }
    //Delete insert and update methods for Pokemon end

    //Delete insert and update methods for Category
    public static boolean deleteCategory(int categoryId){
        Category category = readCategoryById(categoryId);
        if(category != null){
            getCategoryList().remove(category);
            writeCategoryJSON();
            return true;
        } else {
            return false;
        }

    }
    public static void insertCategory(Category category){
        getCategoryList().add(category);
        writeCategoryJSON();
    }
    public static void updateCategory(){
        writeCategoryJSON();
    }
    //Delete insert and update methods for Category end

    //Delete insert and update methods for Type
    public static boolean deleteType(int typeId){
        Type type = readTypeById(typeId);
        if(type != null){
            getTypeList().remove(type);
            writeTypeJSON();
            return true;
        } else {
            return false;
        }

    }
    public static void insertType(Type type){
        getTypeList().add(type);
        writeTypeJSON();
    }
    public static void updateType(){
        writeTypeJSON();
    }

    public static void writePokemonJSON(){
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter = null;

        String pokemonPath = Config.getProperty("pokemonJSON");
        try {
            fileOutputStream = new FileOutputStream(pokemonPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getPokemonList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public static void writeCategoryJSON(){
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter = null;

        String categoryPath = Config.getProperty("categoryJSON");
        try {
            fileOutputStream = new FileOutputStream(categoryPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getCategoryList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public static void writeTypeJSON(){
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter = null;

        String typePath = Config.getProperty("typeJSON");
        try {
            fileOutputStream = new FileOutputStream(typePath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getTypeList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * reads all Pokemon
     *
     * @return list of Pokemon
     */
    public static List<Pokemon> readAllPokemon() {
        return getPokemonList();
    }

    /**
     * reads a Pokemon by its ID
     *
     * @param pokemonId
     * @return the Pokemon (null=not found)
     */
    public static Pokemon readPokemonById(int pokemonId) {
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
    public static List<Type> readAllType() {
        return getTypeList();
    }

    /**
     * reads a Type by its ID
     *
     * @param typeId
     * @return the Type (null=not found)
     */
    public static Type readTypeById(int typeId) {
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
    public static List<Category> readAllCategories() {

        return getCategoryList();
    }

    /**
     * reads a category by its ID
     *
     * @param categoryId
     * @return the Category (null=not found)
     */
    public static Category readCategoryById(int categoryId) {
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
    public static void readPokemonJSON() {
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
    public static void readCategoryJSON() {
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
    public static void readTypeJSON() {
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
    private static List<Pokemon> getPokemonList() {
        if(pokemonList == null) {
            pokemonList = new ArrayList<>();
            readPokemonJSON();
        }
        return pokemonList;
    }

    /**
     * gets typeList
     *
     * @return value of typeList
     */
    private static List<Type> getTypeList() {
        if(typeList == null) {
            typeList = new ArrayList<>();
            readTypeJSON();
        }
        return typeList;
    }

    /**
     * sets pokemonList
     *
     * @param pokemonList the value to set
     */
    private void setPokemonList(List<Pokemon> pokemonList) {
        DataHandler.pokemonList = pokemonList;
    }

    /**
     * sets typeList
     *
     * @param typeList the value to set
     */
    private void setTypeList(List<Type> typeList) {
        DataHandler.typeList = typeList;
    }

    /**
     * gets categoryList
     *
     * @return value of categoryList
     */
    private static List<Category> getCategoryList() {
        if(categoryList == null) {
            categoryList = new ArrayList<>();
            readCategoryJSON();
        }
        return categoryList;
    }

    /**
     * sets categoryList
     *
     * @param categoryList the value to set
     */
    private void setCategoryList(List<Category> categoryList) {
        DataHandler.categoryList = categoryList;
    }


}
