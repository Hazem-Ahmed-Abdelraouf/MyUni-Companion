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
import org.companion.myunicompanion.classes.Course;
import org.companion.myunicompanion.classes.lecturer;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static org.companion.myunicompanion.DatabaseFiller.db;

public class LecturerRegisterCourse implements Initializable {


    @FXML
    private TableColumn<Course,String> courseCode;

    @FXML
    private TableColumn<Course,String> courseCredits;

    @FXML
    private TableColumn<Course,String> courseName;

    @FXML
    private TableColumn<Course,String> courseType;
    
    @FXML
    private Button registerBtn;

    @FXML
    private Button returnBtn;

    @FXML
    private TableView<Course> table;
    private int lec_id;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lec_id = Integer.parseInt(db.dataTransporter.get("lecturer id"));
        //setting the columns
        courseName.setCellValueFactory(new PropertyValueFactory<Course, String>("course_name"));
        courseCode.setCellValueFactory(new PropertyValueFactory<Course, String>("course_ID"));
        courseType.setCellValueFactory(new PropertyValueFactory<Course, String>("course_type"));
        courseCredits.setCellValueFactory(new PropertyValueFactory<Course, String>("credits_num"));


        ObservableList<Course> list = getCourses();
        if(!(list.isEmpty()))
            table.setItems(list);

        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }
    private ObservableList<Course> getCourses(){
        ObservableList<Course> list =  FXCollections.observableArrayList();
        lecturer lec = db.lecturers.get(lec_id);
        db.courses.forEach((key,course)->{
            if(lec.getSchool_name().equals(course.getSchool_name()) && course.getLecturer_id() == -99)
                list.add(course);
        });
        return list;
    }
    @FXML
    void registerCourse(ActionEvent event) {
        Alert alert = null;
        if (table.getSelectionModel().getSelectedItem() == null) {
            alert = new Alert(Alert.AlertType.ERROR, "Please select a course to register", ButtonType.OK);
            alert.showAndWait();
        } else {
            Course selected_course = table.getSelectionModel().getSelectedItem();
            db.lecturers.get(lec_id).add_course_taught(selected_course.getCourse_ID());
            table.getItems().remove(selected_course);
            alert = new Alert(Alert.AlertType.INFORMATION, "Successfully registered course", ButtonType.OK);
            alert.showAndWait();
        }
    }


    @FXML
    void switchToHome(ActionEvent e) {
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
