package ch.bzz.pokedex.service;

import ch.bzz.pokedex.data.UserData;
import ch.bzz.pokedex.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

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
        User user = UserData.findUser(username, password);
        if (user == null || user.getRole() == null || user.getRole().equals("guest")) {
            httpStatus = 404;
        } else {
            httpStatus = 200;
        }

        NewCookie roleCookie = new NewCookie(
                "userRole",
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
    @Path("logout")
    @Produces
    public Response logout()
    {
        Response response = Response
                .status(200)
                .entity("")
                .build();
        return  response;

    }


}
