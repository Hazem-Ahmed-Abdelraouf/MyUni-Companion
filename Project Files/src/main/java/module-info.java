module org.companion.myunicompanion {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.companion.myunicompanion to javafx.fxml;
    exports org.companion.myunicompanion;
    exports org.companion.myunicompanion.classes;
    opens org.companion.myunicompanion.classes to javafx.fxml;
}