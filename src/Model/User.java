/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author ASUS
 */

public class User {
    private final String username;
    private String password;
    private final String designation; // "Admin" or "User"
    private String contact;
    private String address;
    
 private static ArrayList<User> userList = new ArrayList<>();

    public User(String username, String password, String designation, String contact, String address) {
        this.username = username;
        this.password = password;
        this.designation = designation;
        this.contact = contact;
        this.address = address;
    }

    // Getters and Setters
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getDesignation() { return designation; }
    public String getContact() { return contact; }
    public String getAddress() { return address; }

    public void setPassword(String password) { this.password = password; }
    public void setContact(String contact) { this.contact = contact; }
    public void setAddress(String address) { this.address = address; }

public static User findUser(String username) {
        for (User u : userList) {
            if (u.username.equalsIgnoreCase(username)) {
                return u;
            }
        }
        return null;
    }
public static User login(String username, String password) {
        User u = findUser(username);
        if (u != null && u.password.equals(password)) {
            return u;
        }
        return null;
    }
public static void addUser(User user) {
        userList.add(user);
    }


}