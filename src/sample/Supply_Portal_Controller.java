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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.classes.Cookies;
import sample.classes.DataValidation;
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
    final String DB_URL = "jdbc:sqlserver://COT-CIS3365-09;database=SKDB";
    final String user = "sa";
    final String pass = "549657Ll";
    @FXML
    private Label Num_Students;
    @FXML
    private Label Num_Lists;
    @FXML
    private Label Num_Schools;
    @FXML
    private Label Num_Teachers;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL,user,pass);
            ResultSet resultSet = conn.createStatement().executeQuery(" SELECT COUNT(dbo.[SchoolKits.SchoolList].Name) AS School_List FROM [SchoolKits.SchoolList]");
            while (resultSet.next())
            {
                int List1= resultSet.getInt("School_List");
                Num_Schools.setText(String.valueOf(List1));
            }

            ResultSet resultSet1 = conn.createStatement().executeQuery("SELECT COUNT(dbo.[Teacher.SupplyList].List_ID) As Teacher_List FROM [Teacher.SupplyList]");
            while (resultSet1.next())
            {

                int List2= resultSet1.getInt("Teacher_List");
                Num_Lists.setText(String.valueOf(List2));
            }

            ResultSet resultSet2 = conn.createStatement().executeQuery("SELECT SUM(dbo.[Teacher.Grades].NumberOfStudents) As Num_Students FROM [Teacher.Grades]");
            while (resultSet2.next())
            {

                int List2= resultSet2.getInt("Num_Students");
                Num_Students.setText(String.valueOf(List2));
            }

            ResultSet resultSet3 = conn.createStatement().executeQuery("SELECT COUNT(dbo.[Teacher.Accounts].AccountID) As Num_Teachers FROM [Teacher.Accounts]");
            while (resultSet3.next())
            {

                int List3= resultSet3.getInt("Num_Teachers");
                Num_Teachers.setText(String.valueOf(List3));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void Supply_Portal_Sign_Out(ActionEvent event)
    {
        AnchorPane Admin_Login_pane = null;
        try {
            Admin_Login_pane = FXMLLoader.load(getClass().getResource("Admin_Login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        School_Kits_Portal_Pane.getChildren().setAll(Admin_Login_pane);

    }

    @FXML
    private void Supply_Portal_Close_App(ActionEvent event)
    {
        System.exit(0);
    }

    @FXML
    private void View_Supply_Inventory(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("viewInventoryItems.fxml"));

        Parent GUI = loader.load();
        Scene scene = new Scene(GUI);

        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }
}
