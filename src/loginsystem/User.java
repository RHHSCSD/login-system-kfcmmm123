/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginsystem;

/**
 *
 * @author alan
 */
public class User {
    private String userName;
    private String password;
    private String email;
    private int phoneNum;
    private int age;
    
    /**
     * constructor for user 
     * @param userName
     * @param password
     * @param email
     * @param phoneNum
     * @param age
     */
    public User(String userName, String password, String email, int phoneNum, int age){
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phoneNum = phoneNum;
        this.age = age;
    }
    
    /**
     *
     * @param password
     */
    public void setPassword(String password){
        this.password = password;
    }
    
    /**
     *
     * @return
     */
    public String getPassword(){
        return password;
    }

    /**
     *
     * @return
     */
    public String getUserName(){
        return userName;
    }

    /**
     *
     * @return
     */
    public String getEmail(){
        return email;
    }

    /**
     *
     * @return
     */
    public int getPhoneNum(){
        return phoneNum;
    }

    /**
     *
     * @return
     */
    public int getAge(){
        return age;
    }
}
