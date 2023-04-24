package miu.edu.pm.project.onlineshoppingcartsystem.config.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;


import miu.edu.pm.project.onlineshoppingcartsystem.payload.request.LoginRequest;
import miu.edu.pm.project.onlineshoppingcartsystem.payload.request.SignupRequest;
import miu.edu.pm.project.onlineshoppingcartsystem.payload.response.JwtResponse;
import miu.edu.pm.project.onlineshoppingcartsystem.payload.response.MessageResponse;
import miu.edu.pm.project.onlineshoppingcartsystem.user.model.Role;
import miu.edu.pm.project.onlineshoppingcartsystem.user.model.User;
import miu.edu.pm.project.onlineshoppingcartsystem.user.model.UserStatus;
import miu.edu.pm.project.onlineshoppingcartsystem.user.repository.UserRepository;
import miu.edu.pm.project.onlineshoppingcartsystem.user.service.impl.UserDetailsImpl;
import miu.edu.pm.project.onlineshoppingcartsystem.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        System.out.println("__________ SIGN IN  _______" + loginRequest);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        System.out.println("JWT is : " + jwt);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                (roles.isEmpty()) ? null : roles.get(0)));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getFullName(),
                signUpRequest.getPhone(),
                signUpRequest.getEmail(),
                Role.valueOf(signUpRequest.getRole()),
                signUpRequest.getAddress()
                );
        if(user.getRole() != Role.CUSTOMER){
            user.setStatus(UserStatus.INACTIVE);
        }else{
            user.setStatus(UserStatus.ACTIVE);
        }
        /*
        if (!Role.VENDOR.name().equals(signUpRequest.getRole())) {
            user.setRole(Role.CUSTOMER);
        } else {
            user.setRole(Role.VENDOR);
            user.setStatus(UserStatus.INACTIVE);
        }

        user.setAddress(signUpRequest.getAddress());
        user.setPhone(signUpRequest.getPhone());
 */
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
