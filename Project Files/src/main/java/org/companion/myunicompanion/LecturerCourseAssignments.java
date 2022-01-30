package org.companion.myunicompanion;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.companion.myunicompanion.classes.Assignment;

public class LecturerCourseAssignments {

    @FXML
    private Button addAsgnBtn;

    @FXML
    private TableColumn<Assignment,String> asgnName;

    @FXML
    private TableColumn<Assignment,String> asgnQuestion;

    @FXML
    private TableColumn<Assignment,String> asgnType;

    @FXML
    private Button gradeAsgnBtn;

    @FXML
    private Button removeAsgnBtn;

    @FXML
    private Button returnBtn;

    @FXML
    private TableView<?> table;

}
