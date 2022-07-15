package ch.zli.m223.punchclock.controller;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;

import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.service.AuthenticationService;
import ch.zli.m223.punchclock.service.UserService;
import io.quarkus.security.UnauthorizedException;

@Path("/user")
@Tag(name = "Users", description = "Handling of User")
public class UserController {
    @Inject
    UserService userService;

    @Inject
    AuthenticationService authorizationService;

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "get user from db", description = "")
    public String authorize(User user) {
        var newUser = userService.findUser(user.getUsername());
        if(newUser.isPresent()) {
            String password1 = newUser.get().getPasswort();
            String password2 = user.getPasswort();
            if(password1.equals(password2)) {
                return authorizationService.GenerateValidJwtToken(user);
            }
        }
        throw new UnauthorizedException("Username or password invalid");
    }

    @POST
    @Path("/register")
    @Operation(summary = "create user in db", description = "")
    public void create(User user) {
        userService.createUser(user);
    
    }



}
