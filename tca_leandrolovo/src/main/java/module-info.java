module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens com.controller to javafx.fxml;
    //opens com.view to javafx.fxml;

    exports com;
}
