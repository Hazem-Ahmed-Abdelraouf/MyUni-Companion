package org.companion.myunicompanion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.companion.myunicompanion.classes.Assignment;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static org.companion.myunicompanion.DatabaseFiller.db;

public class CreateAsgnController implements Initializable {

    @FXML
    private Button cancelBtn;

    @FXML
    private Button createBtn;

    @FXML
    private TextField name;

    @FXML
    private TextArea questions;

    @FXML
    private ChoiceBox<String> typesbox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typesbox.getItems().setAll("H.W.","Quiz","Test","Exam");
        typesbox.getSelectionModel().selectFirst();
    }
    @FXML
    void createAsgn(ActionEvent e) {
        Alert alert = null;
        if(!(name.getText().isBlank() || questions.getText().isBlank())){
            Assignment newAsgn = new Assignment();
            db.incrementAsgnSurKey();
            newAsgn.setAssignment_ID(db.getAsgnSurKey());
            newAsgn.setAssignment_name(name.getText());
            newAsgn.setCourse_ID(db.dataTransporter.get("course id"));
            newAsgn.setAssignment_type(typesbox.getValue());
            newAsgn.set_assignment_question(questions.getText());
            db.assignments.put(newAsgn.getAssignment_ID(), newAsgn);
            alert = new Alert(Alert.AlertType.INFORMATION, "Assignment created successfully", ButtonType.OK);
            alert.showAndWait();
            returnToCoursesAsgns(e);
        }
        else
        {
            alert = new Alert(Alert.AlertType.ERROR, "Please fill all the assignment fields!", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    void returnToCoursesAsgns(ActionEvent e) {
        Parent coursePage = null;
        try {
            coursePage = FXMLLoader.load(LecturerCourseAssignments.class.getResource("lecturer-see course assignments(Scene11).fxml"));
            Scene homeScene = new Scene(coursePage);
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            window.setScene(homeScene);
            window.setTitle("Courses Assignments");
            window.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
