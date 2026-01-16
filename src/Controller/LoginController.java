package Controller;

import Model.User;

/**
 * Controller for handling login and authentication logic.
 */
public class LoginController {

    /**
     * Authenticates an admin user.
     * 
     * @param username
     * @param password
     * @return true if credentials match admin, false otherwise
     */
    private User currentUser = null;
    private boolean adminLoggedIn = false;
    public boolean authenticateAdmin(String username, String password) {
        return username.equalsIgnoreCase("admin") && "98411".equals(password);
    }

    /**
     * Authenticates a regular user.
     * 
     * @param username
     * @param password
     * @return The User object if authentication is successful, null otherwise.
     */
    public User authenticateUser(String username, String password) {
        User user = User.login(username, password);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null; // Username not found or password mismatch
    }

    /**
     * Checks if a username already exists.
     * 
     * @param username
     * @return true if taken, false otherwise
     */
    public boolean isUsernameTaken(String username) {
        return User.findUser(username) != null;
    }

    /**
     * Registers a new user.
     * 
     * @param username
     * @param password
     * @param role
     * @param contact
     * @param address
     */
    public void registerUser(String username, String password, String role, String contact, String address) {
        User newUser = new User(username, password, role, contact, address);
        User.addUser(newUser);
    }
    
    public LoginResult login(String username, String password) {

    if (username == null || username.trim().isEmpty()) {
        return LoginResult.fail("Enter username");
    }

    if (password == null || password.trim().isEmpty()) {
        return LoginResult.fail("Enter password");
    }

    if (!password.matches("\\d+")) {
        return LoginResult.fail("Password must be numeric");
    }

            if (authenticateAdmin(username, password)) {
            adminLoggedIn = true;
            currentUser = null;
            return LoginResult.admin(password);
        }

        if (username.equalsIgnoreCase("admin")) {
            return LoginResult.fail("Incorrect admin password");
        }
        User u = authenticateUser(username, password);
        if (u == null) {
            return LoginResult.fail("Invalid username or password");
        }

        adminLoggedIn = false;
        currentUser = u;
        return LoginResult.user(u);
}
    public SignupResult signup(String username, String password, String confirm,
                           String contact, String address) {

    if (username == null || username.trim().isEmpty()
            || password == null || password.trim().isEmpty()
            || confirm == null || confirm.trim().isEmpty()
            || contact == null || contact.trim().isEmpty()
            || address == null || address.trim().isEmpty()) {
        return SignupResult.fail("Please enter all the fields");
    }

    if (!password.equals(confirm)) {
        return SignupResult.fail("Passwords do not match");
    }

    if (isUsernameTaken(username)) {
        return SignupResult.fail("Username already exists");
    }

    if (!contact.matches("\\d{10}")) {
        return SignupResult.fail("Contact must be of 10 digits");
    }

    // Everything valid → create user (Model operation)
    registerUser(username, password, "User", contact, address);

    return SignupResult.ok("Signup successful! Please login.");
}

    public static class LoginResult {
        public boolean success;
        public boolean admin;
        public User user;
        public String error;
        public String adminPassword;

        private LoginResult() {}

        public static LoginResult fail(String msg) {
            LoginResult r = new LoginResult();
            r.success = false;
            r.error = msg;
            return r;
        }

        public static LoginResult admin(String pass) {
            LoginResult r = new LoginResult();
            r.success = true;
            r.admin = true;
            r.adminPassword = pass;
            return r;
        }

        public static LoginResult user(User u) {
            LoginResult r = new LoginResult();
            r.success = true;
            r.admin = false;
            r.user = u;
            return r;
        }
    }
    public static class SignupResult {
    public final boolean success;
    public final String message;

    private SignupResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public static SignupResult ok(String msg) {
        return new SignupResult(true, msg);
    }

    public static SignupResult fail(String msg) {
        return new SignupResult(false, msg);
    }
}
    // inside LoginController

public ProfileResult updateProfile(String name, String pass, String desig, String contact) {

    if (name == null || name.trim().isEmpty()
            || pass == null || pass.trim().isEmpty()
            || desig == null || desig.trim().isEmpty()
            || contact == null || contact.trim().isEmpty()) {
        return ProfileResult.fail("All fields are required.");
    }

    if (!contact.matches("\\d{10}")) {
        return ProfileResult.fail("Contact must be exactly 10 digits.");
    }

    if (!pass.matches("\\d+")) {
        return ProfileResult.fail("Password must be numeric.");
    }
    if (currentUser != null) {
        currentUser.setUsername(name);
        currentUser.setPassword(pass);
        //currentUser.setDesignation(desig);
        currentUser.setContact(contact);

        // Optional: if you persist users to file, call save here:
        // User.saveAll();
    } else if (adminLoggedIn) {
        // Admin profile is usually fixed; you can either allow or block:
        // return ProfileResult.fail("Admin profile cannot be edited.");
        // OR just accept and treat as UI-only.
    } else {
        return ProfileResult.fail("No user logged in.");
    }

    return ProfileResult.ok("Profile updated successfully!");
}

public static class ProfileResult {
    public final boolean success;
    public final String message;

    private ProfileResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public static ProfileResult ok(String msg) { return new ProfileResult(true, msg); }
    public static ProfileResult fail(String msg) { return new ProfileResult(false, msg); }
}

}
