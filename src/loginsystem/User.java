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
    
    public User(String userName, String password, String email, int phoneNum, int age){
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phoneNum = phoneNum;
        this.age = age;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public String getPassword(){
        return password;
    }
    public String getUserName(){
        return userName;
    }
    public String getEmail(){
        return email;
    }
    public int getPhoneNum(){
        return phoneNum;
    }
    public int getAge(){
        return age;
    }
}
