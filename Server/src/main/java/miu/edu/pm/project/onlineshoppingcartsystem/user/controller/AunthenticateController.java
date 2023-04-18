package miu.edu.pm.project.onlineshoppingcartsystem.user.controller;

import miu.edu.pm.project.onlineshoppingcartsystem.user.config.JwtUtils;
import miu.edu.pm.project.onlineshoppingcartsystem.user.model.JwtRequest;
import miu.edu.pm.project.onlineshoppingcartsystem.user.model.JwtResponse;
import miu.edu.pm.project.onlineshoppingcartsystem.user.model.User;
import miu.edu.pm.project.onlineshoppingcartsystem.user.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AunthenticateController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtUtils jwtUtils;

    //generate token
    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try
        {
            aunthenticate(jwtRequest.getUsername(),jwtRequest.getPassword());
        }catch (UsernameNotFoundException e)
        {
            e.printStackTrace();
            throw new Exception("User Not found");
        }
        //authenticate
        UserDetails userDetails= this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
                String token =this.jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }


    private void aunthenticate(String username,String password) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (DisabledException e){
            throw new Exception("User disabled" +e.getMessage());
        }catch (BadCredentialsException e){
            throw new Exception("invalid credentials" +e.getMessage());
        }
    }

    //return details of current user
    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal){
        return ((User) this.userDetailsService.loadUserByUsername(principal.getName()));
    }
}
