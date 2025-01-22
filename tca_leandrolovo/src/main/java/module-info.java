module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires java.sql;
    requires java.desktop;

    opens com.controller to javafx.fxml;
    opens com.model to javafx.base;
    // opens com.view to javafx.fxml;

    exports com;
}
