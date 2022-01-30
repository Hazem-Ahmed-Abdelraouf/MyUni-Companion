package org.companion.myunicompanion;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.companion.myunicompanion.classes.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static org.companion.myunicompanion.DatabaseFiller.db;

public class CreateAccountController implements Initializable {

    @FXML
    private DatePicker birthdate;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField email;

    @FXML
    private TextField fname;

    @FXML
    private TextField idField;

    @FXML
    private TextField lname;

    @FXML
    private PasswordField password;

    @FXML
    private Button registerBtn;

    @FXML
    private ChoiceBox<String> schoolNameChoiceBox;

    @FXML
    private ChoiceBox<String> sessionsChoiceBox;

    @FXML
    private ChoiceBox<String> userTypeChoiceBox;
    @FXML
    private TextField country;
    @FXML
    private Text errMsg;
    private int minStudentEnrollmentYear,maxStudentEnrollmentYear,maxYearsAllowed ;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        maxYearsAllowed = 20;
        minStudentEnrollmentYear = 7;
        maxStudentEnrollmentYear =  1;
        userTypeChoiceBox.getItems().setAll("Student","Lecturer");
        //setting the year sessions
        int max_year = LocalDate.now().getYear();
        int min_year = max_year - maxYearsAllowed;
        String session = "",cur_year_str = "";
        for(int cur_year = min_year ; cur_year<max_year ; cur_year++ )
        {
            cur_year_str= Integer.toString(cur_year);
            session = cur_year_str+"/"+Integer.toString(cur_year + 1);
            sessionsChoiceBox.getItems().add(session);
        }
        //getting the unique school names
        db.courses.forEach((id, course)->{
            if(!(schoolNameChoiceBox.getItems().contains(course.getSchool_name()))){
                schoolNameChoiceBox.getItems().add(course.getSchool_name());
            }
        });

        //restricting datepicker
        birthdate.setDayCellFactory(param -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.compareTo(LocalDate.of(2004,1,1)) > 0 );
            }
        });
    }
    @FXML
    public void register(ActionEvent e){
        String userChoice = userTypeChoiceBox.getSelectionModel().getSelectedItem();
        String schoolChoice =  schoolNameChoiceBox.getSelectionModel().getSelectedItem();
        String session = sessionsChoiceBox.getSelectionModel().getSelectedItem();
        //check if any fiels is blank
        if(!(lname.getText().isBlank() || fname.getText().isBlank() || idField.getText().isBlank() || email.getText().isBlank() || country.getText().isBlank()
                || password.getText().isBlank() || userChoice==null || schoolChoice==null || session == null || birthdate.getValue() == null ))
        {
            //check if id is integer
            try {
                int id = Integer.parseInt(idField.getText());
                // see if ID is in the students table
                if (db.students.containsKey(id) || db.lecturers.containsKey(id)) {
                    errMsg.setText("ID has been used before, If you forgot your ID please contact the Admin to get it for you.");
                } else {//unique ID has been entered
                    //check if the email has an "@" in it
                    if(email.getText().contains("@")) {
                        //check if it is a student
                        if (userTypeChoiceBox.getSelectionModel().getSelectedItem() == "Student") {
                            //check if user is student chose very old session date
                            List<String> studentSessions = sessionsChoiceBox.getItems().subList(maxYearsAllowed - minStudentEnrollmentYear, maxYearsAllowed);
                            if (!(studentSessions.contains(session))) {
                                errMsg.setText("Students Year must be at min from " + studentSessions.get(0) + " and max " + studentSessions.get(studentSessions.size() - 1) + " !");
                            } else {
                                Student stu = new Student();
                                int year = Integer.parseInt(session.substring(0, 4));
                                stu.set_date_enrolled(year, 1, 1);
                                stu.setBirthdate(birthdate.getValue());
                                stu.setFname(fname.getText());
                                stu.setLname(lname.getText());
                                stu.setPassword(password.getText());
                                stu.setCountry(country.getText());
                                stu.setEmail(email.getText());
                                stu.setSchool_name(schoolChoice);
                                stu.setUni_ID(id);
                                db.students.put(id, stu);
                                db.dataTransporter.put("student id", Integer.toString(id));
                                swicthToHome(e, "student");

                            }
                        } else {//User is a lecturer
                            lecturer lec = new lecturer();
                            int year = Integer.parseInt(session.substring(0, 4));
                            lec.set_hired_date(year, 1, 1);
                            lec.setBirthdate(birthdate.getValue());
                            lec.setFname(fname.getText());
                            lec.setLname(lname.getText());
                            lec.setPassword(password.getText());
                            lec.setCountry(country.getText());
                            lec.setEmail(email.getText());
                            lec.setSchool_name(schoolChoice);
                            lec.setUni_ID(id);
                            db.lecturers.put(id, lec);
                            db.dataTransporter.put("lecturer id", Integer.toString(id));
                            swicthToHome(e, "lecturer");
                        }
                    }else//email has no "@"
                    {
                        errMsg.setText("Please enter a valid email!");
                    }
                }
            }
            //The ID  wasn't an integer
            catch (NumberFormatException nfe){
                errMsg.setText("The ID must be an Integer");
            }
        }
        else
            errMsg.setText("All fields must be filled!");
    }

    private void swicthToHome(ActionEvent e, String type) {
        String title = "", fxml_path = "";
        if(type.equals("student")) {
            title = "Student Home Page";
            fxml_path = "Student HomePage(Scene3).fxml";
        } else if(type.equals("lecturer")) {
            title = "Lecturer Home Page";
            fxml_path = "Lecturer Home Page(Scene7).fxml";
        }
        Parent homePage = null;
        try {
            homePage = FXMLLoader.load(StudentInspectCourseController.class.getResource(fxml_path));
            Scene homeScene = new Scene(homePage);
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            window.setScene(homeScene);
            window.setTitle(title);
            window.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void switchToLoginScreen(ActionEvent e){
        Parent loginPage = null;
        try {
            loginPage = FXMLLoader.load(StudentInspectCourseController.class.getResource("Login(Scene1).fxml"));
            Scene loginScene = new Scene(loginPage);
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            window.setScene(loginScene);
            window.setTitle("Login Page");
            window.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
