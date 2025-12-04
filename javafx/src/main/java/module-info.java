open module com.mvc {
    requires javafx.controls;
    requires javafx.fxml;

    // 'open module' grants reflective access to all packages in this module
    // so FXMLLoader and other JavaFX reflection-based features will work
    // without listing individual packages (useful when package names contain
    // characters that make listing them problematic).
}
