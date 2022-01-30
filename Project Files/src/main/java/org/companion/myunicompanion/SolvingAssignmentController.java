package org.companion.myunicompanion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.companion.myunicompanion.classes.Assignment_Metadata;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static org.companion.myunicompanion.DatabaseFiller.db;

public class SolvingAssignmentController implements Initializable {

    int stu_id, current_assignment_id;
    String course_id;
    @FXML
    private Text questionArea;
    @FXML
    private TextArea solutionArea;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button returnBtn;
    @FXML
    private Button submitBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //getting the the transfered data
        stu_id = Integer.parseInt(db.dataTransporter.get("student id"));
        current_assignment_id = Integer.parseInt(db.dataTransporter.get("assignment id"));
        course_id = db.assignments.get(current_assignment_id).getCourse_ID();
        //initializing the texts in the scene
        questionArea.setText(db.assignments.get(current_assignment_id).getAssignment_question());
        if(db.students.get(stu_id).get_all_assignments().containsKey(current_assignment_id)) {
            String stu_previous_answer = db.students.get(stu_id).get_all_assignments().get(current_assignment_id).getAnswer();
            solutionArea.setText(stu_previous_answer + "  " + stu_id +"  "  + current_assignment_id);
        }
    }

    @FXML
    public void returnToHome(ActionEvent e) {
        //checking if there is a solution
        if(!(solutionArea.getText().isBlank())) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Your solution will not be saved, Are you sure?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                switchToHome(e);
            }
        }
        else
            switchToHome(e);
    }

    private void switchToHome(ActionEvent e) {
        Parent homePage = null;
        try {
            homePage = FXMLLoader.load(StudentInspectCourseController.class.getResource("Student HomePage(Scene3).fxml"));
            Scene homeScene = new Scene(homePage);
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            window.setScene(homeScene);
            window.setTitle("Student Home Page");
            window.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void submitSolution(ActionEvent e){
        //checking if the user wants to save the solution when it is blank
        if(solutionArea.getText().isBlank()){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Your solution is empty, Are you sure you want to submit?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES)
            {
                saveSolution();
                returnToInspectCourse(e);
            }

        }
        else
        {
            saveSolution();
            returnToInspectCourse(e);
        }
    }
    private void saveSolution(){
        Assignment_Metadata asgn_metadata = new Assignment_Metadata();
        asgn_metadata.setAssignment_id(current_assignment_id);
        asgn_metadata.setCourse_id(course_id);
        asgn_metadata.setAnswer(solutionArea.getText());
        //put solution in assignment metadata of the student
        db.students.get(stu_id).add_asgn_metadata(current_assignment_id, asgn_metadata);

    }
    @FXML
    public void cancelSolution(ActionEvent e) {
        if (solutionArea.getText().isBlank()) {
            returnToInspectCourse(e);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Your solution will not be saved, Are you sure?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                returnToInspectCourse(e);
            }

        }
    }

    @FXML
    public void returnToInspectCourse(ActionEvent e) {
        Parent coursePage = null;
        try {
            coursePage = FXMLLoader.load(StudentInspectCourseController.class.getResource("Student Inspect Course(Scene4).fxml"));
            Scene courseScene = new Scene(coursePage);
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            window.setScene(courseScene);
            window.setTitle("Inspect Course Page");
            window.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
