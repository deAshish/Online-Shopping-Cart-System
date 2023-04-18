package miu.edu.pm.project.onlineshoppingcartsystem.user.controller;

public class UserFoundException extends Exception{
    public UserFoundException() {
        super("User with this Username is not found in database");
    }
    public UserFoundException(String msg){super(msg);}
}
