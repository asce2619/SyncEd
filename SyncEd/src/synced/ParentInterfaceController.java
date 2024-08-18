/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package synced;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Parent Page Controller class
 *
 * @author Abesa Sivananthanathan
 * @author Ilakiya Pakeerathan
 * @author Jannis Saini
 * @author Riya Dave
 */
public class ParentInterfaceController implements Initializable {

    @FXML
    private AnchorPane parentPage;
    @FXML
    private TextArea parentDayPlan;
    @FXML
    private TextArea teachersMessage;
    @FXML
    private TextArea parentsReply;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public ParentAccount p;
    /* recives data from login interface after parent login */
    public void reciveData(ParentAccount p){
        this.p = p;
    }

    /**
     * LogoutButton
     * @param event
     * @throws Exception 
     * 
     * Logout of Parent class and return to Front Page interface
     */
    @FXML
    private void logoutButton(ActionEvent event) throws Exception {
        Stage stageLogin = (Stage) parentPage.getScene().getWindow();
        stageLogin.close();//close parent page
            
        /* Front Page Interface */
        javafx.scene.Parent root = FXMLLoader.load(getClass().getResource("FrontPageInterface.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Parent Day Plan TabPane Tab
     * @param event
     * @throws IOException 
     * 
     * Uploads the Teachers day plan to the parents side
     */
    @FXML
    private void dayPlanTab(Event event) throws IOException {
        String plan = "";
        
        List<String> allLines = Files.readAllLines(Paths.get("DayPlan.txt"));
        for (String line : allLines) {
            plan += line;
            plan += "\n";
        }
        
        parentDayPlan.setText(plan);
    }

    /**
     * Parents Feedback
     * @param event 
     * 
     * Parents Feedback to the Announcement
     */
    @FXML
    private void parentsFeedback(ActionEvent event) throws IOException {
        String reply = parentsReply.getText();
        
        //creates new file
        File fileName = new File("ParentsReply" + ".txt");
        if(fileName.createNewFile() == true){
            FileWriter writeToFile = new FileWriter("ParentsReply" + ".txt");
            writeToFile.write(reply);
            writeToFile.close();
        }
        //if file created ubdates
        else{
            FileWriter writer = new FileWriter("ParentsReply" + ".txt");
            writer.append(reply);
            writer.flush();
        }
        
        /* Parents reply sent page */
        javafx.scene.Parent root = FXMLLoader.load(getClass().getResource("ReplySent.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Parents Message TabPane Tab
     * @param event 
     * 
     * Loads teachers messages to the tab and parents previous feedback
     */
    @FXML
    private void messagesTab(Event event) throws IOException {
        String message = "";
        String reply = "";
        
        List<String> allLines = Files.readAllLines(Paths.get("TeachersMessage.txt"));
        for (String line : allLines) {
            message += line;
            message += "\n";
        }
        teachersMessage.setText(message);
        
        List<String> allLines2 = Files.readAllLines(Paths.get("ParentsReply.txt"));
        for (String line : allLines2) {
            reply += line;
            reply += "\n";
        }
        parentsReply.setText(reply);
    }
    
}
