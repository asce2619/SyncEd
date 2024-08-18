/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package synced;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Front Page Controller class
 *
 * @author Abesa Sivananthanathan
 * @author Ilakiya Pakeerathan
 * @author Jannis Saini
 * @author Riya Dave
 */
public class FrontPageInterfaceController implements Initializable {

    @FXML
    private AnchorPane frontPage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    /**
     * Login Button
     * @param event
     * @throws Exception 
     * 
     * Opens the login interface
     */
    @FXML
    private void loginButton(ActionEvent event) throws Exception {
        Stage stageLogin = (Stage) frontPage.getScene().getWindow();
        stageLogin.close();//close front page
        
        /* Login Page */
        javafx.scene.Parent root = FXMLLoader.load(getClass().getResource("LoginInterface.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    
}
