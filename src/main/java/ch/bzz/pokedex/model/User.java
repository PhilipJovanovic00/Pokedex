package ch.bzz.pokedex.model;

/**
 * Class for the User.
 */
public class User {

    private String UUID;
    private String username;
    private String password;
    private String userRole;

    /**
     * Getters and Setters
     *
     * @return
     */
    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
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

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }


    /**
     * Logon and Logoff Methods for the User
     */
    public void logon() {

    }

    public void logoff() {

    }

}
