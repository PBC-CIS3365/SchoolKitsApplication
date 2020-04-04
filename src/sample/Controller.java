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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public Button button1;
    @FXML
    private JFXTextField Email;
    @FXML
    private JFXPasswordField Password;
    @FXML
    private AnchorPane Login_Pane;
    @FXML
    private AnchorPane Sign_Pane;
    @FXML
    private JFXButton Login_Button;
    @FXML
    private ImageView Login_Progress;
    @FXML
    private ImageView SignUp_Progress;
    @FXML
    private JFXButton SignUp_Button;
    @FXML
    private AnchorPane next_pane;
    @FXML
    private JFXTextField Login_Email;
    @FXML
    private JFXPasswordField Login_Password;
    @FXML
    private JFXTextField SignUp_Email;
    @FXML
    private JFXPasswordField SignUp_Password;
    @FXML
    private JFXPasswordField SignUp_Confirm_Password;
    @FXML
    private JFXComboBox Combo_Box_School;
    ObservableList<String> School = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        final String DB_URL = "jdbc:sqlserver://COT-CIS3365-09;database=SKDB";
    }


    @FXML
    private void Load_SignUp(ActionEvent event) throws IOException {
        AnchorPane S_pane = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Login_Pane.getChildren().setAll(S_pane);
    }

    @FXML
    private void Load_Login(ActionEvent event) throws IOException {
        AnchorPane L_pane = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Sign_Pane.getChildren().setAll(L_pane);
    }

    @FXML
    private void Login_Action(ActionEvent event) throws IOException {
        Login_Progress.setVisible(true);
        PauseTransition whis = new PauseTransition();
        whis.setDuration(Duration.seconds(3));
        whis.setOnFinished(dbz -> {
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Success!");
            tray.setMessage("Welcome User");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.seconds(1.5));

            AnchorPane S_pane = null;
            try {
                S_pane = FXMLLoader.load(getClass().getResource("afterlogin.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Login_Pane.getChildren().setAll(S_pane);
        });


        try {
            DataValidation.Validator(Login_Email.getText().toUpperCase(), Login_Password.getText());
            whis.play();
        } catch (Exception e) {Login_Progress.setVisible(false);}

        String Email    = Login_Email.getText().toUpperCase();
        String Password = Login_Password.getText();
    }

    @FXML
    private void SignUp_Action(ActionEvent event)
    {
        SignUp_Progress.setVisible(true);
        PauseTransition whis = new PauseTransition();
        whis.setDuration(Duration.seconds(3));
        whis.setOnFinished(dbz -> {
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Successfully signed up!");
            tray.setMessage("Welcome " + SignUp_Email.getText());
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.seconds(1.5));

            AnchorPane A_pane = null;
            try{
                A_pane = FXMLLoader.load(getClass().getResource("afterlogin.fxml"));
            }catch (IOException e) { e.printStackTrace();}
            Sign_Pane.getChildren().setAll(A_pane);
        });

        try {
            DataValidation.SignUP_Validator(SignUp_Email.getText().toUpperCase(), SignUp_Password.getText(), SignUp_Confirm_Password.getText(), Combo_Box_School.getValue().toString());
            String S_Email = SignUp_Email.getText().toUpperCase();
            String S_Password = SignUp_Password.getText();
            String S_School = Combo_Box_School.getValue().toString();
            whis.play();
        } catch (Exception e) {SignUp_Progress.setVisible(false); }

    }

    @FXML
    private void School_Choice(ActionEvent event)
    {

    }

    @FXML
    private void School_List(MouseEvent mouseEvent)
    {
        Combo_Box_School.setItems(School);
        Combo_Box_School.setStyle("-fx-background-color: a0a2ab");
        School.clear();
        School.addAll("UH", "UT");
    }
}
