package org.companion.myunicompanion;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.companion.myunicompanion.classes.Assignment;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static org.companion.myunicompanion.DatabaseFiller.db;

public class LecturerCourseAssignments implements Initializable {

    @FXML
    private Button addAsgnBtn;

    @FXML
    private TableColumn<Assignment,String> asgnName;

    @FXML
    private TableColumn<Assignment,String> asgnQuestion;

    @FXML
    private TableColumn<Assignment,String> asgnType;

    //@FXML
   // private Button gradeAsgnBtn;

    @FXML
    private Button removeAsgnBtn;

    @FXML
    private Button returnBtn;

    @FXML
    private TableView<Assignment> table;
    private String current_course_id;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        current_course_id =  db.dataTransporter.get("course id");
        asgnName.setCellValueFactory(new PropertyValueFactory<Assignment, String>("assignment_name"));
        asgnQuestion.setCellValueFactory(new PropertyValueFactory<Assignment, String>("assignment_question"));
        asgnType.setCellValueFactory(new PropertyValueFactory<Assignment, String>("assignment_type"));

        ObservableList<Assignment> list = getAssignments(current_course_id);

        if(!(list.isEmpty()))
            table.setItems(list);
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }
    private ObservableList<Assignment> getAssignments(String current_course_id){
        ObservableList<Assignment> list =  FXCollections.observableArrayList();
        //getting all the assignments ids
        if(!(db.assignments.isEmpty())) {
            db.assignments.forEach((asgn_id, asgn) -> {
                if (current_course_id.equals(asgn.getCourse_ID()))
                    list.add(asgn);

            });
        }
        return list;
    }

    @FXML
    public void addAsgn(ActionEvent e){
        Parent createAsgnPage = null;
        try {
            createAsgnPage = FXMLLoader.load(LecturerCourseAssignments.class.getResource("Lecturer-Add assignment (Scene 12).fxml"));
            Scene createAsgnScene = new Scene(createAsgnPage);
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            window.setScene(createAsgnScene);
            window.setTitle("Create Assignment Page");
            window.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    public void removeAsgn(ActionEvent e){
        Alert alert = null;
        if (table.getSelectionModel().getSelectedItem() == null) {
            alert = new Alert(Alert.AlertType.ERROR, "Please select an assignment to remove", ButtonType.OK);
            alert.showAndWait();
        } else {
            alert = new Alert(Alert.AlertType.WARNING, "Are you sure you want to remove this assignment", ButtonType.YES,ButtonType.CANCEL);
            alert.showAndWait();
            if(alert.getResult() == ButtonType.YES){
                Assignment removed_asgn = table.getSelectionModel().getSelectedItem();
                //remove from db
                db.assignments.remove(removed_asgn.getAssignment_ID());
                //remove from  each student
                db.students.forEach((key, stu)->{
                    if(stu.get_all_assignments().containsKey(removed_asgn.getAssignment_ID())){
                        stu.get_all_assignments().remove(removed_asgn.getAssignment_ID());
                    }
                });
                table.getItems().remove(removed_asgn);
                alert = new Alert(Alert.AlertType.INFORMATION, "Assignment removed successfully", ButtonType.OK);
                alert.showAndWait();
            }
        }
    }


    @FXML
    public void returnToHome(ActionEvent e) {
        Parent homePage = null;
        try {
            homePage = FXMLLoader.load(LecturerCourseAssignments.class.getResource("Student HomePage(Scene3).fxml"));
            Scene homeScene = new Scene(homePage);
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            window.setScene(homeScene);
            window.setTitle("Lecturer Home Page");
            window.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
