module org.hugo.ejerciciol {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.hugo.ejerciciol to javafx.fxml;
    exports org.hugo.ejerciciol;
}