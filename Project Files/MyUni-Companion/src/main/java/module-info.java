module org.companion.myunicompanion {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires javafx.graphics;


    opens org.companion.myunicompanion to javafx.fxml;
    exports org.companion.myunicompanion;
}