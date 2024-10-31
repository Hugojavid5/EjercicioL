module org.hugo.ejerciciol {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;

    opens org.hugo.ejerciciol to javafx.fxml;
    exports org.hugo.ejerciciol;
    opens Model to javafx.base;
    exports Model;
}