package miu.edu.pm.project.onlineshoppingcartsystem;

import miu.edu.pm.project.onlineshoppingcartsystem.user.controller.UserFoundException;
import miu.edu.pm.project.onlineshoppingcartsystem.user.model.Role;
import miu.edu.pm.project.onlineshoppingcartsystem.user.model.User;
import miu.edu.pm.project.onlineshoppingcartsystem.user.model.UserRole;
import miu.edu.pm.project.onlineshoppingcartsystem.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class OnlineShoppingCartSystemApplication implements CommandLineRunner {
    @Autowired
    public UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public static void main(String[] args) {
        SpringApplication.run(OnlineShoppingCartSystemApplication.class, args);
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("starting code");
//		try {
//			User user = new User();
//			user.setFirstname("Binod");
//			user.setLastname("kathayat");
//			user.setEmail("er.binod7@gmail.com");
//			user.setPassword(this.bCryptPasswordEncoder.encode("abc"));
//			user.setProfile("default.png");
//			user.setUsername("BinodK");
//			user.setPhone("7878978789");
//			user.setEnabled(true);
//
//			Role role1 = new Role();
//			role1.setRoleID(44L);
//			role1.setRoleName("ADMIN");
//
//			Set<UserRole> userRoleSet = new HashSet<>();
//			UserRole userRole = new UserRole();
//			userRole.setRole(role1);
//			userRole.setUser(user);
//			userRoleSet.add(userRole);
//			User user1 = this.userService.createUser(user, userRoleSet);
//			System.out.println(user1.getUsername());
//		}catch (UserFoundException e)
//		{
//			e.printStackTrace();
//		}
    }
}
