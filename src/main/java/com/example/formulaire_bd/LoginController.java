package com.example.formulaire_bd;

import com.example.formulaire_bd.Util.Outils;
import com.example.javafxjdbc.DB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.w3c.dom.events.Event;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private Button btnConnexion;

    @FXML
    private Button btnFermer;

    @FXML
    private TextField txtLogin;

    @FXML
    private PasswordField txtPassword;
    @FXML
    protected void Connexion(ActionEvent e ) throws IOException, SQLException, ClassNotFoundException {
        DB db=new DB();
        String requette= "select * from Utilisateurs where login=? and password=?";
        db.initPrepare(requette);
        db.getPstm().setString(1,txtLogin.getText());
        db.getPstm().setString(2,txtPassword.getText());
        ResultSet rs=db.execeutSelect();
        if (rs.next()){
            Outils.load(e,"Page Accueil","/com/example/formulaire_bd/Home.fxml");
        }else {
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("INFO CONNEXION");
            alert.setContentText("Login ou Password Incorrecte");
            alert.showAndWait();
        }

    }
    @FXML
    protected void Reset() {
        txtLogin.setText("");
        txtPassword.setText("");
    }
}