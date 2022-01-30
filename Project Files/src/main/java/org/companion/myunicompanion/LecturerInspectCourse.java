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
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.companion.myunicompanion.classes.Assignment;
import org.companion.myunicompanion.classes.Course;
import org.companion.myunicompanion.classes.Student;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static org.companion.myunicompanion.DatabaseFiller.db;

public class LecturerInspectCourse implements Initializable {

    @FXML
    private TableColumn<?, ?> StudendIdCol;

    @FXML
    private Text courseCredits;

    @FXML
    private Text courseID;

    @FXML
    private Text courseName;

    @FXML
    private Text courseType;

    @FXML
    private Text lecturerName;

    @FXML
    private Button returnBtn;

    @FXML
    private Button seeAsgnBtn;

    @FXML
    private Button solveBtn1;

    @FXML
    private TableColumn<?, ?> stuEmailCol;

    @FXML
    private TableColumn<?, ?> stuNameCol;

    @FXML
    private TableView<?> table;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //getting the current student  and course ids from transporter
        int stu_id = Integer.parseInt(db.dataTransporter.get("student id"));
        Student stu = db.students.get(stu_id);
        String current_course_id = db.dataTransporter.get("course id");
        Course course = db.courses.get(current_course_id);
        //getting the courses's lecturer info
        int lecturer_id = course.getLecturer_id();
        String lec_full_name = "";
        String lec_fisrt_name = "";
        if(lecturer_id== -99)
            lec_full_name = "No Lecturer assigned yet";
        else {
            lec_fisrt_name=db.lecturers.get(lecturer_id).getFname();
            lec_full_name = lec_fisrt_name + " " + db.lecturers.get(lecturer_id).getLname();
        }
        // Setting the course info on the scene
        courseID.setText("Course code: "+ course.getCourse_ID());
        courseName.setText("Course Name: "+course.getCourse_name());
        courseType.setText("Course Type: "+course.getCourse_type());
        lecturerName.setText("Lecturer Name: "+lec_full_name);
        courseCredits.setText("Course Credits: "+Integer.toString(course.getCredits_num()));
        //setting the columns
        /*assignQuestionCol.setCellValueFactory(new PropertyValueFactory<Assignment, String>("assignment_name"));
        assignTypeCol.setCellValueFactory(new PropertyValueFactory<Assignment, String>("assignment_type"));

        ObservableList<Assignment> list = getAssignments(current_course_id);

        if(!(list.isEmpty()))
            table.setItems(list);
        */
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
    public void returnToHome(ActionEvent e) {
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
    public void switchToSolvingScene(ActionEvent e) {
       /* Assignment selected_asgn  = table.getSelectionModel().getSelectedItem();
        db.dataTransporter.put("assignment id",Integer.toString(selected_asgn.getAssignment_ID()));
        Parent solvingPage = null;
        try {
            solvingPage = FXMLLoader.load(StudentInspectCourseController.class.getResource("Solving Assignment(Scene5).fxml"));
            Scene solvingScene = new Scene(solvingPage);
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            window.setScene(solvingScene);
            window.setTitle("Solving Assignment Page");
            window.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }*/
    }




}
