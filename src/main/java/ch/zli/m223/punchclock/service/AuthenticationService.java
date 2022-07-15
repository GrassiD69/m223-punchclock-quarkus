package ch.zli.m223.punchclock.service;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;

import javax.enterprise.context.RequestScoped;

import io.smallrye.jwt.build.Jwt;
import org.eclipse.microprofile.jwt.Claims;

import ch.zli.m223.punchclock.domain.User;

@RequestScoped
public class AuthenticationService {
    
    public String GenerateValidJwtToken(User user){
        String token =
            Jwt.issuer("https://zli.ch/issuer") 
            .upn(user.getUsername()) 
            .groups(new HashSet<>(Arrays.asList("User", "Admin"))) 
            .claim(Claims.birthdate.name(), "2001-07-13")
            .expiresIn(Duration.ofHours(1)) 
            .sign();
        return token;
    }

}
