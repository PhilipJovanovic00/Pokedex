package ch.bzz.pokedex.model;

/**
 * Class for the User.
 */
public class User {

    private String userUUID;
    private String username;
    private String password;
    private String role;

    /**
     * Getters and Setters
     *
     * @return
     */
    public String getUserUUID() {
        return userUUID;
    }

    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    /**
     * Logon and Logoff Methods for the User
     */
    public void logon() {

    }

    public void logoff() {

    }

}
