module org.example.studentdemo {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;

    opens org.example.studentdemo to javafx.graphics,javafx.fxml;
    opens org.example.studentdemo.studentController to javafx.graphics,javafx.fxml;
    exports org.example.studentdemo;
}