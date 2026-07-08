module com.vilhag.javaui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;

    opens com.vilhag.javaui to javafx.fxml;
    exports com.vilhag.javaui;
}