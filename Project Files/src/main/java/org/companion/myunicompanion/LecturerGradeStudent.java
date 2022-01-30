package org.companion.myunicompanion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.companion.myunicompanion.classes.Student;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static org.companion.myunicompanion.DatabaseFiller.db;

public class LecturerGradeStudent implements Initializable {

    @FXML
    private Button cancelBtn;

    @FXML
    private Button finishBtn;

    @FXML
    private ComboBox<String> gradesBox;

    @FXML
    private Button returnBtn;

    @FXML
    private Text stuAge;

    @FXML
    private Text stuCountry;

    @FXML
    private Text stuEmail;

    @FXML
    private Text stuGrade;

    @FXML
    private Text stuID;

    @FXML
    private Text stuName;

    @FXML
    private Text stuYear;
    private  int stu_id;
    private String course_id;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stu_id = Integer.parseInt(db.dataTransporter.get("student id"));
        course_id = db.dataTransporter.get("course id");
        Student stu = db.students.get(stu_id);

        stuAge.setText("Age: " + Integer.toString(stu.getAge()));
        stuCountry.setText("Country: "+stu.getCountry());
        stuEmail.setText("Email: "+stu.getEmail());
        stuID.setText("ID: "+Integer.toString(stu.getUni_ID()));
        stuName.setText("Name: "+ stu.getFname() + " " + stu.getLname());
        stuYear.setText("Current Year: " + Integer.toString(stu.get_student_year()));
        String grade = stu.get_course_grade(db.dataTransporter.get("course id"));
        stuGrade.setText("Current Grade:" + grade);
        gradesBox.getItems().setAll("A","A-","B+","B","B-","C+","C","C-","D+","D","D-","F");
        gradesBox.getSelectionModel().selectFirst();
    }

    @FXML
    public void finishGrade(ActionEvent e){
        String grade_assigned = gradesBox.getSelectionModel().getSelectedItem();
        db.students.get(stu_id).set_course_grade(course_id, grade_assigned);
        //go to inspect course
        Parent inspectPage = null;
        try {
            inspectPage= FXMLLoader.load(LecturerGradeStudent.class.getResource("lecturer-inspect course(scene 10).fxml"));
            Scene inspectScene = new Scene(inspectPage);
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            window.setScene(inspectScene);
            window.setTitle("Inspect Course");
            window.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    @FXML
    public void returnToHome(ActionEvent e){
        Parent homePage = null;
        try {
            homePage = FXMLLoader.load(LecturerGradeStudent.class.getResource("Lecturer Home Page(Scene7).fxml"));
            Scene homeScene = new Scene(homePage);
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            window.setScene(homeScene);
            window.setTitle("Lecturer Home Page");
            window.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void cancelGrade(ActionEvent e){
        Parent inspectPage = null;
        try {
            inspectPage= FXMLLoader.load(LecturerGradeStudent.class.getResource("lecturer-inspect course(scene 10).fxml"));
            Scene inspectScene = new Scene(inspectPage);
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            window.setScene(inspectScene);
            window.setTitle("Inspect Course");
            window.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }




}
