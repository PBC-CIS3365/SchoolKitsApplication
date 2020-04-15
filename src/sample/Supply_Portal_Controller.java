package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Random;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;



public class Supply_Portal_Controller implements Initializable{
    @FXML
    private AnchorPane School_Kits_Portal_Pane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

    @FXML
    private void Supply_Portal_Sign_Out(ActionEvent event)
    {
        AnchorPane Teacger_Login_pane = null;
        try {
            Teacger_Login_pane = FXMLLoader.load(getClass().getResource("Teacher_Login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        School_Kits_Portal_Pane.getChildren().setAll(Teacger_Login_pane);
    }

    @FXML
    private void Supply_Portal_Close_App(ActionEvent event)
    {
        System.exit(0);
    }
}
