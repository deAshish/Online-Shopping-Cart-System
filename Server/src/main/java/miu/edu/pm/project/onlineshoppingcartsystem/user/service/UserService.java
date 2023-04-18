package miu.edu.pm.project.onlineshoppingcartsystem.user.service;



import miu.edu.pm.project.onlineshoppingcartsystem.user.model.User;
import miu.edu.pm.project.onlineshoppingcartsystem.user.model.UserRole;

import java.util.Set;

public interface UserService {
    //create user
    public User createUser(User user, Set<UserRole> userRoles) throws Exception;

   //get user by username
    public User getUser(String username);

    //delete user by id
    public void deleteUser(Long userId);

    //update user
    public  User updateUser(User user) throws Exception;
    public User updateUserUpdatePhone(String userName,String userPhone) throws Exception;
}
