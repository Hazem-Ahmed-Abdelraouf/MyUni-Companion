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
import org.companion.myunicompanion.classes.Assignment;
import org.companion.myunicompanion.classes.Course;
import org.companion.myunicompanion.classes.Student;
import org.companion.myunicompanion.classes.lecturer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static org.companion.myunicompanion.DatabaseFiller.db;

public class LecturerInspectCourse implements Initializable {


    @FXML
    private TableView<Student> table;
    @FXML
    private TableColumn<Student,String> stuIDCol;
    @FXML
    private TableColumn<Student,String> stuEmailCol;

    @FXML
    private TableColumn<Student,String> stuFNameCol;
    @FXML
    private TableColumn<Student,String> stuLNameCol;
    @FXML
    private Text courseCredits;

    @FXML
    private Text courseID;

    @FXML
    private Text courseName;

    @FXML
    private Text courseType;

    @FXML
    private Button returnBtn;

    @FXML
    private Button seeAsgnBtn;

    @FXML
    private Button gradeStuBtn;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //getting the current lecturer  and course ids from transporter
        int lec_id = Integer.parseInt(db.dataTransporter.get("lecturer id"));
        lecturer lec = db.lecturers.get(lec_id);
        String current_course_id = db.dataTransporter.get("course id");
        Course course = db.courses.get(current_course_id);
        // Setting the course info on the scene
        courseID.setText("Course code: "+ course.getCourse_ID());
        courseName.setText("Course Name: "+course.getCourse_name());
        courseType.setText("Course Type: "+course.getCourse_type());
        courseCredits.setText("Course Credits: "+Integer.toString(course.getCredits_num()));
        //setting the columns
        stuFNameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("Fname" ));
        stuLNameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("Lname" ));
        stuEmailCol.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
        stuIDCol.setCellValueFactory(new PropertyValueFactory<Student, String>("uni_ID"));

        ObservableList<Student> list = getStudents(current_course_id);
        if(!(list.isEmpty()))
            table.setItems(list);

        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    private ObservableList<Student> getStudents(String current_course_id){
        ObservableList<Student> list =  FXCollections.observableArrayList();
        db.students.forEach((stu_id,student)->{
            if(student.get_course_metadata(current_course_id) != null)
                list.add(student);
        });
        return list;
    }
    @FXML
    public void returnToHome(ActionEvent e) {
        Parent homePage = null;
        try {
            homePage = FXMLLoader.load(LecturerInspectCourse.class.getResource("Lecturer Home Page(Scene7).fxml"));
            Scene homeScene = new Scene(homePage);
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            window.setScene(homeScene);
            window.setTitle("Lecturer Home Page");
            window.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void gradeStudent(ActionEvent e) {
        Alert alert = null;
        if (table.getSelectionModel().getSelectedItem() == null) {
            alert = new Alert(Alert.AlertType.ERROR, "Please select a student to grade", ButtonType.OK);
            alert.showAndWait();
        } else {
            Student selected_stu = table.getSelectionModel().getSelectedItem();
            db.dataTransporter.put("student id", Integer.toString(selected_stu.getUni_ID()));
            Parent solvingPage = null;
            try {
                solvingPage = FXMLLoader.load(LecturerInspectCourse.class.getResource("leturer-grade student(scene 15).fxml"));
                Scene solvingScene = new Scene(solvingPage);
                Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
                window.setScene(solvingScene);
                window.setTitle("Grade Assignment");
                window.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
    @FXML
    public void seeAsgn(ActionEvent e){
        Parent asgnPage = null;
        try {
            asgnPage = FXMLLoader.load(LecturerInspectCourse.class.getResource("lecturer-see course assignments(Scene11).fxml"));
            Scene asgnScene = new Scene(asgnPage);
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            window.setScene(asgnScene);
            window.setTitle("Course Assignments");
            window.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
