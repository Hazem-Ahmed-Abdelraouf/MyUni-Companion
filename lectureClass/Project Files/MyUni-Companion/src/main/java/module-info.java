module org.companion.myunicompanion {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;


    opens org.companion.myunicompanion to javafx.fxml;
    exports org.companion.myunicompanion;
}