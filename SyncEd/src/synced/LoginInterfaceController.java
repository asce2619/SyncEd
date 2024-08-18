/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package synced;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Login Page Controller class
 *
 * @author Abesa Sivananthanathan
 * @author Ilakiya Pakeerathan
 * @author Jannis Saini
 * @author Riya Dave
 */
public class LoginInterfaceController implements Initializable {

    @FXML
    private MenuButton accountType;
    @FXML
    private TextField userLogin;
    @FXML
    private PasswordField passwordLogin;
    @FXML
    private Button login;
    @FXML
    private AnchorPane loginPage;
    
    public static String role;
    
    public ParentAccount p;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void loginButtonAction(ActionEvent event) throws Exception {
        String username = userLogin.getText();
        String password = passwordLogin.getText();
        int enter = 0;
        int login = 0;
        
        /*Teacher Login*/
        if(username.equals("admin") && password.equals("admin") && role.equals("Teacher")){
            login = 1;
            Stage stageLogin = (Stage) loginPage.getScene().getWindow();
            stageLogin.close();//close login scene
            
            /* Teacher Page */
            Parent root = FXMLLoader.load(getClass().getResource("TeacherInterface.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
        /* Parent login */
        else if(role.equals("Parent")){
            login = 2;
            String currentDirectory = System.getProperty("user.dir"); 
            File dir = new File(currentDirectory);

            for(File file : dir.listFiles()){
                if(file.getName().endsWith((".txt"))){
                    try(Scanner readFile = new Scanner(file)){
                        String un = readFile.next();
                        String pw = readFile.next();
                        if(username.equals(un) && password.equals(pw)){
                            enter = 1;//meaning parent created
                            p = new ParentAccount(un, pw);//makes temp parent according to file
                            break;
                        }
                    }
                    catch(Exception e3){}   
                }
            }
        }
        
        if(login == 2 && enter == 1){
            //if parent created
            if(enter == 1){
                Stage stageLogin = (Stage) loginPage.getScene().getWindow();
                stageLogin.close();//closes login page
                
                /* Opens Parent page */
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ParentInterface.fxml"));
                Parent root = (Parent)loader.load();

                ParentInterfaceController controller = loader.getController();
                controller.reciveData(p); //passes parent logging in to parent controller
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            }
        }
        else{
            /* Login Error */
            Parent root = FXMLLoader.load(getClass().getResource("LoginError.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            
            userLogin.clear();
            passwordLogin.clear();
        }
    }

    @FXML
    private void teacherLogin(ActionEvent event) {
        accountType.setText("Teacher");
        role = "Teacher";//account type is manager
    }

    @FXML
    private void parentLogin(ActionEvent event) {
        accountType.setText("Parent");
        role = "Parent";//account type is customer
    }
    
}
