package com.example.formulaire_bd;

import com.example.formulaire_bd.Util.Outils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

import java.io.IOException;

public class HomeController {
    @FXML
    private MenuItem btnAFFicheMedecin;

    @FXML
    private MenuItem btnAFFicheSecretaire;

    @FXML
    private MenuItem btnCloseHome;

    @FXML
    private MenuItem btnCreerUsers;
    @FXML
    protected void CreationUser(ActionEvent e ) throws IOException {
        Outils.loadandwait(e,"Page Utilisateur","/com/example/formulaire_bd/Users.fxml");

    }
    @FXML
    protected void Affichage_Medecin(ActionEvent e ) throws IOException {
        Outils.loadandwait(e,"Page Medecin","/com/example/formulaire_bd/Medecin.fxml");

    }
    @FXML
    protected void Affichage_Secretaire(ActionEvent e ) throws IOException {
        Outils.loadandwait(e,"Page Secretaire","/com/example/formulaire_bd/Secretaire.fxml");

    }
    @FXML
    protected void close(ActionEvent e ) throws IOException {
        Outils.loadandwait(e,"Page Secretaire","/com/example/formulaire_bd/Login.fxml");

    }
}
