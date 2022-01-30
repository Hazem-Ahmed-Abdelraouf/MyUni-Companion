package org.companion.myunicompanion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

import static org.companion.myunicompanion.DatabaseFiller.db;

public class LoginScene1Controller {
    @FXML
    private Button loginBtn;
    @FXML
    private TextField idField;
    @FXML
    private PasswordField password;
    @FXML
    private Text errMsg;
    @FXML
    private Button newAccountBtn;
    @FXML public void checkCredentials(ActionEvent e)
    {

        //not empty check
       if(!(idField.getText().isBlank()) && !(password.getText().isBlank())){
            //check if id is a number
           try {
               int id = Integer.parseInt(idField.getText());
               // see if ID is in the students table
               if(db.students.containsKey(id)){
                   //check password of student
                   if(db.students.get(id).getPassword().equals(password.getText())){
                       db.dataTransporter.put("student id",idField.getText());
                       //go to  student homepage scene
                       goToStudentHomePage(e);
                   }
                   else {//password doesn't match
                       errMsg.setText("Wrong Password!");
                   }
                   //the id is not a student so let's see if it is a lecturer
               }else if(db.lecturers.containsKey(id)){
                   //check password for lecturer
                   if(db.lecturers.get(id).getPassword().equals(password.getText())){
                       db.dataTransporter.put("lecturer id",idField.getText());
                       //go to lecturer homepage scene
                   }//password doesn't match
                   else {
                       errMsg.setText("Wrong Password!");
                   }
               } // the id doesn't belong to any student or lecturer
               else {
                   errMsg.setText("This ID doesnt belong to any student or lecturer.\n" +
                           "Please create a new account or contact the university admin.");
               }
           }
           //The wasn't an integer
           catch (NumberFormatException nfe){
               errMsg.setText("The ID must be an Integer");
           }

        }// either one of the fields was empty
        else{
           errMsg.setText("Cannot leave any field empty!");
        }

    }
    private void goToStudentHomePage(ActionEvent e) {
        Parent stuPage = null;
        try {
            stuPage = FXMLLoader.load(LoginScene1Controller.class.getResource("Student HomePage(Scene3).fxml"));
            Scene stuScene = new Scene(stuPage);
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            window.setScene(stuScene);
            window.setTitle("Student Home Page");
            window.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    private void goToLecturerHomePage(ActionEvent e){

    }

    @FXML
    public void switchToCreateAccount(ActionEvent e) {
        Parent accountPage = null;
        try {
            accountPage = FXMLLoader.load(LoginScene1Controller.class.getResource("New Account(Scene2).fxml"));
            Scene accountScene = new Scene(accountPage);
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            window.setScene(accountScene);
            window.setTitle("Create Account Page");
            window.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
