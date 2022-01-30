package org.companion.myunicompanion;

import javafx.beans.Observable;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.companion.myunicompanion.classes.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import static org.companion.myunicompanion.DatabaseFiller.db;

public class StudentHomeController implements Initializable {

    @FXML
    private TableView<Course> table;
    @FXML
    private TableColumn<Course, String> courseCode;

    @FXML
    private TableColumn<Course, String> courseName;
    @FXML
    private TableColumn<Course, String> courseType;


    @FXML
    private Text stuAge;

    @FXML
    private Text stuCountry;

    @FXML
    private Text stuEmail;

    @FXML
    private Text stuID;

    @FXML
    private Text stuName;

    @FXML
    private Text stuYear;
    @FXML
    private Button inspectBtn;
    @FXML
    private Button unregisterBtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //getting the current student id from transporter
        int stu_id = Integer.parseInt(db.dataTransporter.get("student id"));
        Student stu = db.students.get(stu_id);

        stuAge.setText("Age: " + Integer.toString(stu.get_student_year()));
        stuCountry.setText("Country: "+stu.getCountry());
        stuEmail.setText("Email: "+stu.getEmail());
        stuID.setText("ID: "+Integer.toString(stu.getUni_ID()));
        stuName.setText("Name: "+ stu.getFname() + " " + stu.getLname());
        stuYear.setText("Current Year: " + Integer.toString(stu.get_student_year()));
        //setting the columns
        courseName.setCellValueFactory(new PropertyValueFactory<Course, String>("course_name"));
        courseCode.setCellValueFactory(new PropertyValueFactory<Course, String>("course_ID"));
        courseType.setCellValueFactory(new PropertyValueFactory<Course, String>("course_type"));
        ObservableList<Course> list = getCourses(stu_id);
        if(!(list.isEmpty()))
            table.setItems(list);

        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    private ObservableList<Course> getCourses(int stu_id){
        ObservableList<Course> list =  FXCollections.observableArrayList();
        //getting the course ids to get their information later
        ArrayList<String> student_courses_ids =  new ArrayList<>();
        HashMap<String,Course_Metadata> stu_courses = db.students.get(stu_id).get_all_courses();
        //checking if the student has enrolled in any course
        if( stu_courses != null && !(stu_courses.isEmpty()) ) {
            db.students.get(stu_id).get_all_courses().forEach((key, value) -> {
                student_courses_ids.add(key);
            });
            //extracting these courses from the database and filling our list with it
            student_courses_ids.forEach((course_id) -> {
                list.add(db.courses.get(course_id));
            });
        }
        return list;
    }

    //inspect course method
    @FXML
    private void inspectCourse(ActionEvent e){
       Course selected_course = table.getSelectionModel().getSelectedItem();

       if(selected_course != null){
           //transferring the data
           db.dataTransporter.put("course id",selected_course.getCourse_ID());
           switchToInspectCourseScene("Student Inspect Course(Scene4).fxml", e);
       }
    }
    @FXML
    private void unregisterCourse(ActionEvent e){
        Course selected_course = table.getSelectionModel().getSelectedItem();
        if(selected_course !=null){
            //Alert popup
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "If you unregister this course, all you your assignment solutions will be lost, Are you sure?", ButtonType.YES, ButtonType.CANCEL);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                table.getItems().remove(selected_course);
                //getting the current student id from transporter
                int stu_id = Integer.parseInt(db.dataTransporter.get("student id"));
                //removing the course
                db.students.get(stu_id).unregister_course(selected_course.getCourse_ID());
            }
        }

    }

    private void switchToInspectCourseScene(String fxmlPath, ActionEvent e){
        Parent coursePage = null;
        try {
            coursePage = FXMLLoader.load(LoginScene1Controller.class.getResource(fxmlPath));
            Scene stuScene = new Scene(coursePage);
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            window.setScene(stuScene);
            window.setTitle("Inspect Course");
            window.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
