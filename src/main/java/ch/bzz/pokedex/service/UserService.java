package ch.bzz.pokedex.service;

import ch.bzz.pokedex.data.DataHandler;
import ch.bzz.pokedex.data.UserData;
import ch.bzz.pokedex.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

@Path("user")
public class UserService {

    @POST
    @Path("login")
    @Produces
    public Response login(
            @FormParam("username") String username,
            @FormParam("password") String password
    )
    {
        int httpStatus;
        // || user.getRole()
        User user = UserData.findUser(username, password);
        if (user == null || DataHandler.readUserRole(username, password) == null) {
            httpStatus = 404;
        } else {
            httpStatus = 200;
        }

        NewCookie roleCookie = new NewCookie(
                "role",
                user.getRole(),
                "/",
                "",
                "Login-Cookie",
                600,
                false
        );

        Response response = Response
                .status(httpStatus)
                .entity("")
                .cookie(roleCookie)
                .build();
        return  response;

    }

    @DELETE
    @Path("logoff")
    @Produces
    public Response login()
    {
        NewCookie roleCookie = new NewCookie(
                "userRole",
                "",
                "/",
                "",
                "Logout-Cookie",
                1,
                false
        );
        Response response = Response
                .status(200)
                .entity("")
                .header("Access-Control-Allow-Origin", "*")
                .cookie(roleCookie)
                .build();
        return  response;

    }


}
