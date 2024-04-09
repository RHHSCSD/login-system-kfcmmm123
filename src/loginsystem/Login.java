package loginsystem;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Login {
    private ArrayList<User> users;
    
    public Login() {
        this.users = new ArrayList<>(); // Use diamond operator for simplicity
    }
    
    public boolean registerUser(User user) throws FileNotFoundException, NoSuchAlgorithmException {
        //check if the name already exists
        if (!isUniqueName(user.getUserName())) {
            System.out.println("Registration failed: Username already exists.");
            return false;
        }

        File f = new File("users.txt");
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(f, true))) {
            pw.println(user.getUserName());
            pw.println(encryptPassword(user.getPassword())); // Store encrypted password
            pw.println(user.getEmail());
            pw.println(user.getPhoneNum());
            pw.println(user.getAge());
            pw.println(); // Separator line for each user
        } 
        System.out.println("Registration for " + user.getUserName() + " is successful");
        users.add(user); // Add the new user to the ArrayList
        return true;
    }

    public void loadUsers() throws FileNotFoundException, NumberFormatException {
        File f = new File("users.txt");
        try (Scanner sc = new Scanner(f)) {
            while (sc.hasNextLine()) {
                String userName = sc.nextLine().trim();
                String password = sc.nextLine().trim(); // Encrypted password
                String email = sc.nextLine().trim();
                int phoneNum = Integer.parseInt(sc.nextLine().trim());
                int age = Integer.parseInt(sc.nextLine().trim());
                if (sc.hasNextLine()) sc.nextLine(); // Skip the blank line

                User user = new User(userName, password, email, phoneNum, age);
                users.add(user);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            throw e; 
        }
    }
    
    public void logUserIn(String userName, String password) throws NoSuchAlgorithmException {
        User user = getUserByName(userName);
        if (user != null && checkPassword(user, password)) {
            System.out.println("Login successfully!");
        } else {
            System.out.println("Login failed");
        }
    }
    
    public boolean checkPassword(User user, String password) throws NoSuchAlgorithmException {
        return encryptPassword(password).equals(user.getPassword());
    }

    public User getUserByName(String userName) {
        for (User user : users) {
            if (user.getUserName().equals(userName)) {
                return user; // Return the matching user immediately
            }
        }
        return null; // Return null if no matching user is found
    }

    
    public boolean isUniqueName(String userName) {
        for (User user : users) {
            if (user.getUserName().equals(userName)) {
                return false; 
            }
        }
        return true;
    }
    
    public String encryptPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : md.digest()) {
            sb.append(Integer.toHexString((b & 0xFF) | 0x100), 1, 3);
        }
        return sb.toString();
    }
    
    public boolean checkStrongPassword(String pw) throws FileNotFoundException{
        File f = new File("dictbadpass.txt");
        boolean isStrong = true;
        try (Scanner sc = new Scanner(f)) {
            while (sc.hasNextLine()) {
                String password = sc.nextLine().trim();

                if (password.equals(pw)) isStrong = false;
            }
            return isStrong;
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            throw e; // Rethrow exception after logging to handle it further up if necessary
        }
    }
}