package pzm.core;
/*
 * User.java
 *
 * Created on 13. Dezember 2005, 13:04
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

/**
 *
 * @author hertel
 */
public class User {
    
    private int id;
    private String name;
    private String lastName;
    private String login;
    private String pass;
    
    /** Creates a new instance of User */
    public User(String name, String lastName, String login, String pass) {
        this.name = name;
        this.lastName = lastName;
        this.login = login;
        this.pass = pass;
    }
    
    public User(String name, String lastName, String login) {
        this.name = name;
        this.lastName = lastName;
        this.login = login;
    }
       
    
    
    
    public int getUserID() {
        return id;
    }
    
    public String getUserName() {
        return name;
    }
    
    public String getUserLastName() {
        return lastName;
    }
    
    public String getUserLogin() {
        return login;
    }
    
    public String getUserPass() {
        return pass;
    }
    
    public void setUserID(int id) {
        this.id = id;
    }
    
    public void setUserName(String name) {
        this.name = name;
    }
    
    public void setUserLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void setUserLogin(String login) {
        this.login = login;
    }
    
    public void setUserPass(String pass) {
        this.pass = pass;
    }
}
