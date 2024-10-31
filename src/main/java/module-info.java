module org.hugo.ejerciciol {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.hugo.ejerciciol to javafx.fxml;
    exports org.hugo.ejerciciol;
}