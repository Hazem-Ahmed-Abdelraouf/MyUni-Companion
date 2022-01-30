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
import org.companion.myunicompanion.classes.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import static org.companion.myunicompanion.DatabaseFiller.db;

public class StudentRegisterCourse implements Initializable {
    private int stu_id;
    @FXML
    private TableView<Course> table;
    @FXML
    private TableColumn<Course,String> courseCode;

    @FXML
    private TableColumn<Course,Integer> courseCredits;

    @FXML
    private TableColumn<Course,String> courseName;

    @FXML
    private TableColumn<Course,String> courseType;
    @FXML
    private Button registerBtn;

    @FXML
    private Button returnBtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //getting the transfered data
        stu_id = Integer.parseInt(db.dataTransporter.get("student id"));
        //setting the columns
        courseName.setCellValueFactory(new PropertyValueFactory<Course, String>("course_name"));
        courseCode.setCellValueFactory(new PropertyValueFactory<Course, String>("course_ID"));
        courseType.setCellValueFactory(new PropertyValueFactory<Course, String>("course_type"));
        courseCredits.setCellValueFactory(new PropertyValueFactory<Course, Integer>("credits_num"));
        ObservableList<Course> list = getCourses();
        if(!(list.isEmpty()))
            table.setItems(list);
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }
    private ObservableList<Course> getCourses(){
        ObservableList<Course> list =  FXCollections.observableArrayList();
        int stu_year = db.students.get(stu_id).get_student_year();
        String stu_school = db.students.get(stu_id).getSchool_name();
        HashMap<String, Course_Metadata> stu_courses = db.students.get(stu_id).get_all_courses();
        db.courses.forEach((id,course)->{
            if(course.getYear_applicable() == stu_year && course.getSchool_name().equals(stu_school) ){
                if(!(stu_courses.containsKey(course.getCourse_ID()))){
                    list.add(course);
                }
            }
        });
        return list;
    }
    @FXML
    void registerCourse(ActionEvent e) {
        Alert alert = null;
        if(table.getSelectionModel().getSelectedItem() == null){
            alert = new Alert(Alert.AlertType.ERROR,"Please select a course to register",ButtonType.OK);
            alert.showAndWait();
        }
        else
        {//register the course and remove from list
            Course  selected_course = table.getSelectionModel().getSelectedItem();
            Course_Metadata course_metadata = new Course_Metadata();
            course_metadata.setCourse_ID(selected_course.getCourse_ID());
            db.students.get(stu_id).add_course_metadata(course_metadata.getCourse_ID(), course_metadata);
            alert = new Alert(Alert.AlertType.INFORMATION,"Course has been successfully registered, Check it out in your home page.",ButtonType.OK);
            alert.showAndWait();
            table.getItems().remove(selected_course);

        }
    }

    @FXML
    void switchToHome(ActionEvent e){
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


}
