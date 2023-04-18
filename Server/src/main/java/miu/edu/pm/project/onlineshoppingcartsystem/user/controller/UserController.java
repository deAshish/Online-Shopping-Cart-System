package miu.edu.pm.project.onlineshoppingcartsystem.user.controller;

import miu.edu.pm.project.onlineshoppingcartsystem.user.model.Role;
import miu.edu.pm.project.onlineshoppingcartsystem.user.model.User;
import miu.edu.pm.project.onlineshoppingcartsystem.user.model.UserRole;
import miu.edu.pm.project.onlineshoppingcartsystem.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/test")
    public String test(){
        return  "Welcome to backend api of training";
    }

    //creating user
    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception {
        System.out.println("Hello today user"+user);
        Set<UserRole> roles = new HashSet<>();
        user.setProfile("Default.png");
        //encoding password with bcryptpasswordencoder
        user.setPassword(this.bCryptPasswordEncoder.encode((user.getPassword())));

        Role role = new Role();
        if (user.getUsertype().equals("vendor")){
            role.setRoleName("VENDOR");
            role.setRoleID(46L);
        }
        else {
            role.setRoleName("CUSTOMER");
            role.setRoleID(45L);
        }
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        roles.add(userRole);
        return this.userService.createUser(user, roles);
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username)
    {
        return this.userService.getUser(username);
    }

   //delete the user by  id
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userID){
        this.userService.deleteUser(userID);
    }

    //update the user phone number
    @PutMapping("/")
    public void updateUser(@RequestBody User user) throws Exception {
         this.userService.updateUser(user);
    }

    @PutMapping("/{userName}/{userPhone}")
    public void updateUserUpdatePhone(@PathVariable String userName,@PathVariable String userPhone) throws Exception {
         this.userService.updateUserUpdatePhone(userName,userPhone);
    }

    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<?> exceptionHandler(UserFoundException ex){return ResponseEntity.ok("user Not found");}
}

