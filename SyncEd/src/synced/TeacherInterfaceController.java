/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package synced;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Teacher Page Controller class
 *
 * @author Abesa Sivananthanathan
 * @author Ilakiya Pakeerathan
 * @author Jannis Saini
 * @author Riya Dave
 */
public class TeacherInterfaceController implements Initializable {

    @FXML
    private AnchorPane teacherPage;
    @FXML
    private TextArea teacherDayPlan;
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
    
    /**
     * Logout Button
     * @param event
     * @throws Exception 
     * 
     * Logouts the user and returns to the Front Page interface
     */
    @FXML
    private void logoutButton(ActionEvent event) throws Exception {
        Stage stageLogin = (Stage) teacherPage.getScene().getWindow();
        stageLogin.close();//close teacher page
            
        /* Front Page Interface */
        javafx.scene.Parent root = FXMLLoader.load(getClass().getResource("FrontPageInterface.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    /**
     * Save Teachers Day Plan
     * @param event 
     * 
     * Saving the teachers plan in a text file
     */
    @FXML
    private void savePlan(ActionEvent event) throws IOException {
        String plan = teacherDayPlan.getText();
        
        //creating the file
        File fileName = new File("DayPlan" + ".txt");
        if(fileName.createNewFile() == true){
            FileWriter writeToFile = new FileWriter("DayPlan" + ".txt");
            writeToFile.write(plan);
            writeToFile.close();
        }
        //if file already created updates
        else{
            FileWriter writer = new FileWriter("DayPlan" + ".txt");
            writer.append(plan);
            writer.flush();
        }
        
        /* Plan Saved Page */
        javafx.scene.Parent root = FXMLLoader.load(getClass().getResource("DayPlanSaved.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Teachers Day Plan TabPane Tab
     * @param event 
     * 
     * Uploads previous Day Plan
     */
    @FXML
    private void dayPlanTab(Event event) throws IOException {
        String plan = "";
        
        List<String> allLines = Files.readAllLines(Paths.get("DayPlan.txt"));
        for (String line : allLines) {
            plan += line;
            plan += "\n";
        }
        
        teacherDayPlan.setText(plan);//sets text     
    }

    /**
     * Teachers Announcements Submit Button
     * @param event
     * @throws IOException 
     * 
     * saves teachers messages to the parents
     */
    @FXML
    private void teachersMessageButton(ActionEvent event) throws IOException {
        String message = teachersMessage.getText();
        
        //creates message file
        File fileName = new File("TeachersMessage" + ".txt");
        if(fileName.createNewFile() == true){
            FileWriter writeToFile = new FileWriter("TeachersMessage" + ".txt");
            writeToFile.write(message);
            writeToFile.close();
        }
        //if file created updates
        else{
            FileWriter writer = new FileWriter("TeachersMessage" + ".txt");
            writer.append(message);
            writer.flush();
        }
        
        /* Message Sent Interface */
        javafx.scene.Parent root = FXMLLoader.load(getClass().getResource("MessageSent.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Teachers Message TabPane Tab
     * @param event 
     * 
     * Load parents reply for the teacher and previous teacher message
     */
    @FXML
    private void messagesTab(Event event) throws IOException {
        String reply = "";
        String message = "";
        
        List<String> allLines = Files.readAllLines(Paths.get("ParentsReply.txt"));
        for (String line : allLines) {
            reply += line;
            reply += "\n";
        }
        parentsReply.setText(reply);
        
        List<String> allLines2 = Files.readAllLines(Paths.get("TeachersMessage.txt"));
        for (String line : allLines2) {
            message += line;
            message += "\n";
        }
        teachersMessage.setText(message);
    }
}
