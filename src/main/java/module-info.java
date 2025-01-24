module com.example.formulaire_bd {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.formulaire_bd to javafx.fxml;
    exports com.example.formulaire_bd;
    exports com.example.javafxjdbc;
    opens com.example.javafxjdbc to javafx.fxml;
}