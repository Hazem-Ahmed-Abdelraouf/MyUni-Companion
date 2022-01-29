package org.companion.myunicompanion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;

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
    @FXML public void checkCredentials(ActionEvent e)
    {
        //not empty check
       out1: if(idField.getText() != "" && password.getText() != ""){
            //check if id is a number
           try {
               int id = Integer.parseInt(idField.getText());
               // see if ID is in the students table
               if(db.students.containsKey(id)){
                   //check password of student
                   if(db.students.get(id).getPassword() == password.getText()){
                       db.dataTransporter.put("student id",idField.getText());
                       //go to  student homepage scene
                   }
                   else {//password doesn't match
                       errMsg.setText("Wrong Password!");
                   }
                   //the id is not a student so let's see if it is a lecturer
               }else if(db.lecturers.containsKey(id)){
                   //check password for lecturer
                   if(db.lecturers.get(id).getPassword() == password.getText()){
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
    private void goToStudentHomePage(ActionEvent e){

    }
    private void goToLecturerHomePage(ActionEvent e){
        
    }



}
