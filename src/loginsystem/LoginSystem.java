/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package loginsystem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author michael.roy-diclemen
 */
public class LoginSystem {

    /**
     *
     * @param args
     * @throws NoSuchAlgorithmException
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        try {
            Login l = new Login();
            l.loadUsers(); // Load users once at the beginning

            User user1 = new User("Peter", "12345678", "123@gmail.com", 12345678, 18);
            
            if (!l.checkStrongPassword(user1.getPassword())){
                System.out.println("The password is too weak");
            }
            else{
                System.out.println("The password is strong");
            }
            
            l.registerUser(user1);
            
            
            User user2 = new User("Hello", "sdjhpu3y234whd19234y31", "rhhs@gmail.com" , 911, 200);
            l.registerUser(user2);
            
            if (l.isUniqueName("Ben")) System.out.println("The user name is not used.");
            
            l.logUserIn("Peter", "1234567890");
            l.logUserIn("Hello", "12397yuoewq");
        } catch (FileNotFoundException | NoSuchAlgorithmException e) {
            System.err.println("An error occurred: " + e.getMessage());
        } catch (IOException e){
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
