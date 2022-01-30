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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.companion.myunicompanion.classes.Course;
import org.companion.myunicompanion.classes.Course_Metadata;
import org.companion.myunicompanion.classes.Student;
import org.companion.myunicompanion.classes.lecturer;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import static org.companion.myunicompanion.DatabaseFiller.db;

public class LecturerHompPage implements Initializable {

    @FXML
    private TableView<Course> table;
    @FXML
    private TableColumn<Course, String> courseCode;

    @FXML
    private TableColumn<Course, String> courseName;
    @FXML
    private TableColumn<Course, String> courseType;


    @FXML
    private Text lecAge;

    @FXML
    private Text lecCountry;

    @FXML
    private Text lecEmail;

    @FXML
    private Text lecIDText;

    @FXML
    private Text lecName;

    @FXML
    private Text lecYear;
    @FXML
    private Button inspectBtn;
    @FXML
    private Button unregisterBtn;
    @FXML
    private Button registerBtn;

    private int lec_id;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //getting the current lecturer id from transporter
        lec_id = Integer.parseInt(db.dataTransporter.get("lecturer id"));
        lecturer lec = db.lecturers.get(lec_id);

        lecAge.setText("Age: " + Integer.toString(lec.getAge()));
        lecCountry.setText("Country: "+lec.getCountry());
        lecEmail.setText("Email: "+lec.getEmail());
        lecIDText.setText("ID: "+Integer.toString(lec.getUni_ID()));
        lecName.setText("Name: "+ lec.getFname() + " " + lec.getLname());
        lecYear.setText("Lecturing Year: " + Integer.toString(lec.get_lecturing_period()));
        //setting the columns
        courseName.setCellValueFactory(new PropertyValueFactory<Course, String>("course_name"));
        courseCode.setCellValueFactory(new PropertyValueFactory<Course, String>("course_ID"));
        courseType.setCellValueFactory(new PropertyValueFactory<Course, String>("course_type"));
        ObservableList<Course> list = getCourses(lec_id);
        if(!(list.isEmpty()))
            table.setItems(list);

        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    private ObservableList<Course> getCourses(int lec_id){
        ObservableList<Course> list =  FXCollections.observableArrayList();
        //getting the course ids to get their information
        ArrayList<String> courses_taught = db.lecturers.get(lec_id).get_course_taught();
        //filling the list
        for(String course_id: courses_taught ){
            list.add(db.courses.get(course_id));
        }
        return list;
    }

    //inspect course method
    @FXML
    private void inspectCourse(ActionEvent e){
        Alert alert = null;
        if (table.getSelectionModel().getSelectedItem() == null) {
            alert = new Alert(Alert.AlertType.ERROR, "Please select a course to inspect", ButtonType.OK);
            alert.showAndWait();
        } else {
            Course selected_course = table.getSelectionModel().getSelectedItem();
            if (selected_course != null) {
                //transferring the data
                db.dataTransporter.put("course id", selected_course.getCourse_ID());
                switchToInspectCourseScene("lecturer-inspect course(scene 10).fxml", e);
            }
        }
    }

    private void switchToInspectCourseScene(String fxmlPath, ActionEvent e){
        Parent coursePage = null;
        try {
            coursePage = FXMLLoader.load(LecturerHompPage.class.getResource(fxmlPath));
            Scene lecScene = new Scene(coursePage);
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            window.setScene(lecScene);
            window.setTitle("Inspect Course");
            window.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    private void unregisterCourse(ActionEvent e) {
        Course selected_course = table.getSelectionModel().getSelectedItem();
        if (selected_course != null) {
            //Alert popup
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to unregister this course?", ButtonType.YES, ButtonType.CANCEL);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                table.getItems().remove(selected_course);
                //removing the course
                db.lecturers.get(lec_id).deregister_course(selected_course.getCourse_ID());
            }
        }
    }
        @FXML
        public void switchToLecRegisterCourse(ActionEvent e){
            Parent coursePage = null;
            try {
                coursePage = FXMLLoader.load(LecturerHompPage.class.getResource("Lecturer-register course(scene 8).fxml"));
                Scene lecScene = new Scene(coursePage);
                Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
                window.setScene(lecScene);
                window.setTitle("Register Course");
                window.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }



}
