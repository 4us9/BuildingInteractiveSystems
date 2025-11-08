module com.mvc {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mvc to javafx.fxml;
    exports com.mvc;

}
