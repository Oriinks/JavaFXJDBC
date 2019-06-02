package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class LogowanieController {


    @FXML
    private TextField txtlogin;

    @FXML
    private PasswordField txtpass;

    public void Login(ActionEvent actionEvent) throws IOException {
        if(txtlogin.getText().equals("projekt") && txtpass.getText().equals("java")){
            Stage primaryStage = new Stage();
            //wyłączanie poprzedniej sceny
            ((Node)actionEvent.getSource()).getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
            primaryStage.setTitle("Projekt");
            primaryStage.setScene(new Scene(root, 1280, 720));
            primaryStage.show();
        }
        else{
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("Komunikat.fxml"));
            primaryStage.setTitle("Błąd");
            primaryStage.setScene(new Scene(root, 400, 116));
            primaryStage.show();
            txtpass.clear();
            txtlogin.clear();
        }
    }
}
