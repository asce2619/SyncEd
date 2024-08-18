/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package synced;

import java.io.File;

/**
 * Parent Account Class
 * @author Abesa Sivananthanathan
 * @author Ilakiya Pakeerathan
 * @author Jannis Saini
 * @author Riya Dave
 */
public class ParentAccount {
    
    private String username;
    private String password;
    private String role = "Parent";
    
    /**
     * Parent Login Constructor
     * @param username
     * @param password
     * 
     * when parent login saves the parents details
     */
    public ParentAccount(String username, String password){
        File fileName = new File(username + ".txt");
        if(fileName.exists()){
            this.username = username;
            this.password = password;
        }
    }
    
}
