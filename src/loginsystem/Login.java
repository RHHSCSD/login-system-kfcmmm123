package loginsystem;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 *
 * @author alan
 */
public class Login {
    private ArrayList<User> users;
    
    /**
     *
     */
    public Login() {
        this.users = new ArrayList<>(); 
    }
    
    /**
     * add the new registered user into the list
     * @param user
     * @return
     * @throws FileNotFoundException
     * @throws NoSuchAlgorithmException
     */
    public boolean registerUser(User user) throws FileNotFoundException, NoSuchAlgorithmException, IOException {
        //check if the name already exists
        if (!isUniqueName(user.getUserName())) {
            System.out.println("Registration failed: Username already exists.");
            return false;
        }
        
        //write the user's information into the file
        File f = new File("users.txt");
        try (PrintWriter pw = new PrintWriter(new FileWriter(f, true))) {
            pw.println(user.getUserName());
            pw.println(encryptPassword(user.getPassword())); // Store encrypted password
            pw.println(user.getEmail());
            pw.println(user.getPhoneNum());
            pw.println(user.getAge());
            pw.println(); // Separator line for each user
            pw.close();
        } 
        System.out.println("Registration for " + user.getUserName() + " is successful");
        users.add(user); // Add the new user to the ArrayList
        return true;
    }

    /**
     * load all the users stored in the file
     * @throws FileNotFoundException
     * @throws NumberFormatException
     */
    public void loadUsers() throws FileNotFoundException, NumberFormatException {
        File f = new File("users.txt");
        try (Scanner sc = new Scanner(f)) {
            while (sc.hasNextLine()) {
                //get all the information of each user from the file
                String userName = sc.nextLine().trim();
                String password = sc.nextLine().trim(); // Encrypted password
                String email = sc.nextLine().trim();
                int phoneNum = Integer.parseInt(sc.nextLine().trim());
                int age = Integer.parseInt(sc.nextLine().trim());
                if (sc.hasNextLine()) sc.nextLine(); // Skip the blank line

                User user = new User(userName, password, email, phoneNum, age);
                users.add(user); //add them to the list
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            throw e; 
        }
    }
    
    /**
     * log the user in 
     * @param userName
     * @param password
     * @throws NoSuchAlgorithmException
     */
    public void logUserIn(String userName, String password) throws NoSuchAlgorithmException {
        User user = getUserByName(userName);
        if (user != null && checkPassword(user, password)) {
            System.out.println("Login successfully!");
        } else {
            System.out.println("Login failed");
        }
    }
    
    /**
     * check if the password is correct
     * @param user
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     */
    public boolean checkPassword(User user, String password) throws NoSuchAlgorithmException {
        return encryptPassword(password).equals(user.getPassword());
    }

    /**
     * check if the entered name matches a user name in the list
     * @param userName
     * @return
     */
    public User getUserByName(String userName) {
        for (User user : users) {
            if (user.getUserName().equals(userName)) {
                return user; // Return the matching user immediately
            }
        }
        return null; // Return null if no matching user is found
    }

    /**
     * check if the name is unique in the list
     * @param userName
     * @return
     */
    public boolean isUniqueName(String userName) {
        for (User user : users) {
            if (user.getUserName().equals(userName)) {
                return false; 
            }
        }
        return true;
    }
    
    /**
     * encrypt the password and store
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     */
    public String encryptPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : md.digest()) {
            sb.append(Integer.toHexString((b & 0xFF) | 0x100), 1, 3);
        }
        return sb.toString();
    }
    
    /**
     * check if the password is strong 
     * @param pw
     * @return
     * @throws FileNotFoundException
     */
    public boolean checkStrongPassword(String pw) throws FileNotFoundException{
        File f = new File("dictbadpass.txt");
        boolean isStrong = true;
        try (Scanner sc = new Scanner(f)) {
            //loop through the weak passwords list 
            while (sc.hasNextLine()) {
                String password = sc.nextLine().trim();

                if (password.equals(pw)) isStrong = false;
            }
            return isStrong;
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
        return isStrong;
    }
}