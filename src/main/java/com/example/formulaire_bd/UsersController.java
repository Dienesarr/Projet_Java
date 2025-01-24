package com.example.formulaire_bd;

import com.example.formulaire_bd.Util.Outils;
import com.example.javafxjdbc.DB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static sun.nio.ch.IOUtil.load;

public class UsersController implements Initializable {
    @FXML
    private TableColumn<Utilisateur, String> ClNom;

    @FXML
    private TableColumn<Utilisateur, String> ClPrenom;

    @FXML
    private TableColumn<Utilisateur, String> ClRole;

    @FXML
    private TableColumn<Utilisateur, Integer> Clid;

    @FXML
    private Button btnAjout;

    @FXML
    private MenuItem btnMedecin;

    @FXML
    private Button btnModif;

    @FXML
    private MenuItem btnSecretaire;

    @FXML
    private Button btnSup;

    @FXML
    private TextField txtLogin;

    @FXML
    private TextField txtNom;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtPrenom;
    @FXML
    private ChoiceBox<String> txtRole;
    @FXML
    private TableView<Utilisateur> tblUtilisateur;

    @FXML
    protected void AjoutUtilisateur () throws SQLException, ClassNotFoundException {
        DB db = new DB();
        String sql = "INSERT INTO utilisateurs(id, prenom, nom, login, password, role) values (NULL, ?, ?, ?, ?, ?)";
        db.initPrepare(sql);
        db.getPstm().setString(1, txtPrenom.getText());
        db.getPstm().setString(2, txtNom.getText());
        db.getPstm().setString(3, txtLogin.getText());
        db.getPstm().setString(4, txtPassword.getText());
        db.getPstm().setString(5, txtRole.getValue());
        int ok = db.executeMaj();
        if (ok == 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Enregistrement");
            alert.setContentText("Personne ajoutée avec succès");
            alert.showAndWait();

            load();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Enregistrement");
            alert.setContentText("Vérifiez les données");
            alert.showAndWait();
        }
    }


    public String[] roles = {"Medecin","Secretaire"};

    public void listerole() throws ClassNotFoundException {
        List<String> listR = new ArrayList<>();
        for (String role : roles) {
            listR.add(role);
        }
        ObservableList listeRole = FXCollections.observableArrayList();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourcesBundle) {
        Clid.setCellValueFactory(new PropertyValueFactory<>("id"));
        ClPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        ClNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        ClRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        txtRole.getItems().addAll(roles);

        try {
            load();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    public void load() throws SQLException, ClassNotFoundException {
        List<Utilisateur> listUser = new ArrayList<>();
        DB db = new DB();
        String sql = "Select id,prenom,nom,role from Utilisateurs";
        db.initPrepare(sql);
        ResultSet rs = db.execeutSelect();

        while (rs.next()) {
           Utilisateur user = new Utilisateur();
            user.setId(rs.getInt(1));
            user.setPrenom(rs.getString(2));
            user.setNom(rs.getString(3));
            user.setRole(rs.getString(4));
            listUser.add(user);
        }
        ObservableList<Utilisateur> listetable = FXCollections.observableArrayList();
        for (Utilisateur p : listUser) {
            listetable.add(p);
        }
        tblUtilisateur.setItems(listetable);

    }

    @FXML
    protected void modifier() throws SQLException, ClassNotFoundException {
        DB db = new DB();
        Utilisateur selectedUtilisateur = tblUtilisateur.getSelectionModel().getSelectedItem();
        if (selectedUtilisateur != null) {
            String sql = "UPDATE utilisateurs SET prenom=?, nom=?, role=? WHERE id=?";
            db.initPrepare(sql);
            db.getPstm().setString(1, txtPrenom.getText());
            db.getPstm().setString(2, txtNom.getText());
            db.getPstm().setString(3, txtRole.getValue());
            db.getPstm().setInt(4, selectedUtilisateur.getId());
            int ok = db.executeMaj();
            if (ok == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Modification");
                alert.setContentText("Utilisateur modifié avec succès");
                alert.showAndWait();
                load();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Modification");
                alert.setContentText("Vérifiez les données");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Modification");
            alert.setContentText("Veuillez sélectionner un utilisateur à modifier");
            alert.showAndWait();
        }
    }

    @FXML
    protected void supprimer() throws SQLException, ClassNotFoundException {
        DB db = new DB();
        Utilisateur selectedUtilisateur = tblUtilisateur.getSelectionModel().getSelectedItem();
        if (selectedUtilisateur != null) {
            String sql = "DELETE FROM utilisateurs WHERE id=?";
            db.initPrepare(sql);
            db.getPstm().setInt(1, selectedUtilisateur.getId());
            int ok = db.executeMaj();
            if (ok == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Suppression");
                alert.setContentText("Utilisateur supprimé avec succès");
                alert.showAndWait();
                load();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Suppression");
                alert.setContentText("Vérifiez les données");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Suppression");
            alert.setContentText("Veuillez sélectionner un utilisateur à supprimer");
            alert.showAndWait();
        }
    }

}