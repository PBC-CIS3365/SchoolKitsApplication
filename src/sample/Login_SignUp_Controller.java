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


public class Login_SignUp_Controller implements Initializable {
    private boolean isMyComboBoxEmpty;
    private int User_ID;
    private int MIN = 10000, MAX = 100000;
    private String S_Email;
    private String S_Password;
    private String S_School;
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
    @FXML
    private JFXComboBox Combo_Box_Security_Q1;
    @FXML
    private JFXComboBox Combo_Box_Security_Q2;
    @FXML
    private JFXComboBox Profile_S_Q1;
    @FXML
    private JFXComboBox Profile_S_Q2;
    @FXML
    private JFXComboBox Profile_Combo_Box_Security_Q1;
    @FXML
    private JFXComboBox Profile_Combo_Box_Security_Q2;

    ObservableList<String> School = FXCollections.observableArrayList(); // Used for School combo box list
    ObservableList<String> Sec_Q1 = FXCollections.observableArrayList(); // Used for Password Reset combo box
    ObservableList<String> Sec_Q2 = FXCollections.observableArrayList(); // Used for Password Reset combo box
    ObservableList<String> Profile_Q1 = FXCollections.observableArrayList();
    ObservableList<String> Profile_Q2 = FXCollections.observableArrayList();
    @FXML
    private Button button_button;
    @FXML
    private AnchorPane Forgot_Password_Pane;
    @FXML
    private JFXTextField Email_Recovery;
    @FXML
    private JFXPasswordField Q1_Input_Field;
    @FXML
    private JFXPasswordField Q2_Input_Field;
    @FXML
    private Label Recovery_Password;
    final String DB_URL = "jdbc:sqlserver://cis3365db.c4wtgp04ii93.us-east-1.rds.amazonaws.com;database=PBC";
    final String user = "chaldunUH";
    final String pass = "549657Ll";
    @FXML
    private AnchorPane Account_Creation_Pane;
    @FXML
    private JFXTextField Profile_F_Name;
    @FXML
    private JFXTextField Profile_L_Name;
    @FXML
    private JFXTextField Profile_Area_Code;
    @FXML
    private JFXTextField Profile_Phone_Number;
    @FXML
    private JFXPasswordField Profile_QAnswer1;
    @FXML
    private JFXPasswordField Profile_QAnswer2;
    @FXML
    private ProgressBar Progress_Bar;
    @FXML
    private AnchorPane Page_Holder_Pane;
    @FXML
    private ImageView Profile_Progress;
    @FXML
    private JFXTextField Admin_Login_Email;
    @FXML
    private JFXPasswordField Admin_Login_Password;
    @FXML
    private AnchorPane Admin_Login_Pane;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
    }


    @FXML
    private void Load_SignUp(ActionEvent event) throws Exception {
        AnchorPane S_pane = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Login_Pane.getChildren().setAll(S_pane);
    }

    @FXML
    private void Load_Login(ActionEvent event) throws Exception {
        AnchorPane L_pane = FXMLLoader.load(getClass().getResource("Teacher_Login.fxml"));
        Sign_Pane.getChildren().setAll(L_pane);
    }

    @FXML
    private void Login_Action(ActionEvent event) throws Exception {
        Login_Progress.setVisible(true);
        PauseTransition whis = new PauseTransition();
        whis.setDuration(Duration.seconds(3));
        whis.setOnFinished(dbz -> {
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Success!");
            tray.setMessage("Welcome " + Login_Email.getText());
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.seconds(2));

            AnchorPane S_pane = null;
            try {
                S_pane = FXMLLoader.load(getClass().getResource("Place_Holder.fxml"));  //needs the fxml for the homepage
            } catch (IOException e) {
                e.printStackTrace();
            }
            Login_Pane.getChildren().setAll(S_pane);
        });

        try {
            DataValidation.Validator(Login_Email.getText().toUpperCase(), Login_Password.getText());
            String Email    = Login_Email.getText();
            String Password = Login_Password.getText();
            Connection conn = DriverManager.getConnection(DB_URL,user,pass);
            ResultSet resultSet = conn.createStatement().executeQuery("SELECT * FROM Test.Credentials WHERE Email =('"+Email+"') AND Password_field =('"+Password+"')");

            int count = 0;

            while (resultSet.next())
            {
               count=count+1;
            } 

            if (count == 1)
            {
                whis.play();
            }
            else {Login_Progress.setVisible(false);
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.SLIDE;
                tray.setAnimationType(type);
                tray.setTitle("Unsuccessful ");
                tray.setMessage("Credentials Incorrect");
                tray.setNotificationType(NotificationType.NOTICE);
                tray.showAndDismiss(Duration.seconds(5));}


        } catch (Exception e) {
            Login_Progress.setVisible(false);
        }

    }

    @FXML
    private void SignUp_Action(ActionEvent event)
    {
        Random People_ID = new Random();
        do {
            User_ID = People_ID.nextInt((MAX - MIN) + MIN);}while (User_ID < 10000);
        DataValidation.setID(User_ID);


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

            AnchorPane Profile_pane = null;
            try{
                Profile_pane = FXMLLoader.load((getClass().getResource("Profile_Page.fxml")));
            }catch (IOException e) { e.printStackTrace();}
            Sign_Pane.getChildren().setAll(Profile_pane);
        });

        try {
            if (isMyComboBoxEmpty = Combo_Box_School.getSelectionModel().isEmpty())
            {
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;
                tray.setAnimationType(type);
                tray.setTitle("Unsuccessful");
                tray.setMessage("Please Choose a School");
                tray.setNotificationType(NotificationType.ERROR);
                tray.showAndDismiss(Duration.seconds(5));
                throw new IllegalArgumentException();
            }
            DataValidation.SignUP_Validator(SignUp_Email.getText().toUpperCase(), SignUp_Password.getText(), SignUp_Confirm_Password.getText());
            S_Email = SignUp_Email.getText().toUpperCase();
            S_Password = SignUp_Password.getText();
            S_School = Combo_Box_School.getValue().toString();

            Connection conn = DriverManager.getConnection(DB_URL,user,pass);
            PreparedStatement stmt = null;

            stmt = conn.prepareStatement("INSERT INTO Test.Credentials(Email,Password_field,School,ID) " +
                                                "VALUES (?,?,?,?)");
            stmt.setString(1, S_Email);
            stmt.setString(2, S_Password);
            stmt.setString(3, S_School);
            stmt.setInt(4, User_ID);
            stmt.executeUpdate();

            conn.commit();
            stmt.close();
            conn.close();

            whis.play();
        } catch (Exception e) { SignUp_Progress.setVisible(false);
        e.printStackTrace();}
    }

    @FXML
    private void School_Choice(ActionEvent event)
    {

    }

    @FXML
    private void School_List(MouseEvent mouseEvent)
    {
        Combo_Box_School.setItems(School);
        Combo_Box_School.setStyle("-fx-background-color: a0a2ab;");
        School.clear();
        School.addAll("UH", "UT");
    }

    @FXML
    private void Forgot_Password(ActionEvent event)
    {
        AnchorPane Forgot_Password = null;
        try{
            Forgot_Password = FXMLLoader.load(getClass().getResource("Password_Recovery.fxml"));
        } catch (IOException e) { e.printStackTrace();}
        Login_Pane.getChildren().setAll(Forgot_Password);

    }

    @FXML
    private void Secuirty_Q1(ActionEvent event)
    {

    }

    @FXML
    private void Security_Q1_List(MouseEvent mouseEvent)
    {
        Combo_Box_Security_Q1.setItems(Sec_Q1);
        Combo_Box_Security_Q1.setStyle("-fx-background-color: a0a2ab;");
        Sec_Q1.clear();
        Sec_Q1.setAll("In what city or town was your first job?", "What is the name of your first pet?", "What are the last five digits of your drivers licence number?");
    }

    @FXML
    private void Security_Q2(ActionEvent event) {
    }

    @FXML
    private void Security_Q2_List(MouseEvent mouseEvent)
    {
        Combo_Box_Security_Q2.setItems(Sec_Q2);
        Combo_Box_Security_Q2.setStyle("-fx-background-color: a0a2ab;");
        Sec_Q2.clear();
        Sec_Q2.setAll("In what city or town was your first job?", "What is the name of your first pet?", "What are the last five digits of your drivers licence number?");
    }

    @FXML
    private void Submit_Recovery(ActionEvent event)
    {
        if (isMyComboBoxEmpty = Combo_Box_Security_Q1.getSelectionModel().isEmpty() || Combo_Box_Security_Q2.getSelectionModel().isEmpty())
        {
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Unsuccessful");
            tray.setMessage("Please Choose two Security Questions");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
            throw new IllegalArgumentException();
        }
        try {
        DataValidation.Password_Recovery_Validator(Email_Recovery.getText(),
                                                   Combo_Box_Security_Q1.getValue().toString(),
                                                   Q1_Input_Field.getText(),
                                                   Combo_Box_Security_Q2.getValue().toString(),
                                                   Q2_Input_Field.getText());


        String Recovery_Email = Email_Recovery.getText().toUpperCase();
        String Q1 = Combo_Box_Security_Q1.getValue().toString();
        String Q1_Answer = Q1_Input_Field.getText();
        String Q2 = Combo_Box_Security_Q2.getValue().toString();
        String Q2_Answer = Q2_Input_Field.getText();

        Connection conn = null;
        conn = DriverManager.getConnection(DB_URL,user,pass);

        ResultSet resultSet = conn.createStatement().executeQuery("SELECT Password_field FROM PBC.Test.Credentials WHERE Email =('"+Recovery_Email+"')AND Security_Question_1 =('"+Q1+"') AND Q1_Answer =('"+Q1_Answer+"') AND Q2_Answer =('"+Q2_Answer+"') AND Security_Question_2 =('"+Q2+"')");

            int count = 0;

            while (resultSet.next())
            {
                count=count+1;
                if (count == 1)
                {
                    Recovery_Password.setText(resultSet.getString("Password_field"));
                }
            }
            if (count == 0)
            {
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;
                tray.setAnimationType(type);
                tray.setTitle("Unsuccessful");
                tray.setMessage("No Records found");
                tray.setNotificationType(NotificationType.ERROR);
                tray.showAndDismiss(Duration.seconds(5));
            }

    } catch (SQLException e) { e.printStackTrace(); }
    }

    @FXML
    private void Load_Login_From_Recovery(ActionEvent event)
    {
        AnchorPane L_pane = null;
        try {
            L_pane = FXMLLoader.load(getClass().getResource("Teacher_Login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Forgot_Password_Pane.getChildren().setAll(L_pane);
    }

    @FXML
    private void Profile_Submit(ActionEvent event)
    {
        Profile_Progress.setVisible(true);
        PauseTransition whis = new PauseTransition();
        whis.setDuration(Duration.seconds(3));
        whis.setOnFinished(dbz -> {
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Successfully Finished Account!");
            tray.setMessage("Welcome " + Profile_F_Name.getText() + " " + Profile_L_Name.getText());
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.seconds(1.5));

            AnchorPane Place_Holder_pane = null;
            try{
                Place_Holder_pane = FXMLLoader.load((getClass().getResource("Place_Holder.fxml")));
            }catch (IOException e) { e.printStackTrace();}
            Account_Creation_Pane.getChildren().setAll(Place_Holder_pane);
        });

        try {
            if (isMyComboBoxEmpty = Profile_Combo_Box_Security_Q1.getSelectionModel().isEmpty() || Profile_Combo_Box_Security_Q2.getSelectionModel().isEmpty())
            {
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;
                tray.setAnimationType(type);
                tray.setTitle("Unsuccessful");
                tray.setMessage("Please Choose two Security Questions");
                tray.setNotificationType(NotificationType.ERROR);
                tray.showAndDismiss(Duration.seconds(5));
                throw new IllegalArgumentException();
            }
            DataValidation.Profile_Validator(Profile_F_Name.getText().toUpperCase(), Profile_L_Name.getText().toUpperCase(),
                                            Profile_Area_Code.getText(), Profile_Phone_Number.getText(),
                                            Profile_Combo_Box_Security_Q1.getValue().toString(), Profile_Combo_Box_Security_Q2.getValue().toString(),
                                            Profile_QAnswer1.getText(), Profile_QAnswer2.getText());

            Connection conn = DriverManager.getConnection(DB_URL,user,pass);
            PreparedStatement stmt = null;


            stmt = conn.prepareStatement("Update Test.Credentials SET First_Name=?, Last_Name=?, Area_Code=?, Phone_Number=?, Security_Question_1=?, Security_Question_2=?, Q1_Answer=?, Q2_Answer=? WHERE ID=?");


            stmt.setString(1, Profile_F_Name.getText().toUpperCase());
            stmt.setString(2, Profile_L_Name.getText().toUpperCase());
            stmt.setString(3, Profile_Area_Code.getText());
            stmt.setString(4, Profile_Phone_Number.getText());
            stmt.setString(5, Profile_Combo_Box_Security_Q1.getValue().toString());
            stmt.setString(6, Profile_Combo_Box_Security_Q2.getValue().toString());
            stmt.setString(7, Profile_QAnswer1.getText());
            stmt.setString(8, Profile_QAnswer2.getText());
            stmt.setInt(9, DataValidation.getID());

            stmt.executeUpdate();

            conn.commit();
            stmt.close();
            conn.close();

            DataValidation.setID(0);
            whis.play();
        } catch (Exception e) {
            Profile_Progress.setVisible(false);
            e.printStackTrace();
        }
    }

    @FXML
    private void P_Secuirty_Q1(ActionEvent event)
    {
    }

    @FXML
    private void P_Secuirty_Q1_List(MouseEvent mouseEvent)
    {
        Profile_Combo_Box_Security_Q1.setItems(Profile_Q1);
        Profile_Combo_Box_Security_Q1.setStyle("-fx-background-color: a0a2ab;");
        Profile_Q1.clear();
        Profile_Q1.setAll("In what city or town was your first job?", "What is the name of your first pet?", "What are the last five digits of your drivers licence number?");
    }

    @FXML
    private void P_Secuirty_Q2(ActionEvent event) {
    }

    @FXML
    private void P_Secuirty_Q2_List(MouseEvent mouseEvent)
    {
        Profile_Combo_Box_Security_Q2.setItems(Profile_Q2);
        Profile_Combo_Box_Security_Q2.setStyle("-fx-background-color: a0a2ab;");
        Profile_Q2.clear();
        Profile_Q2.setAll("In what city or town was your first job?", "What is the name of your first pet?", "What are the last five digits of your drivers licence number?");
    }

    @FXML
    private void Admin_Login_Action(ActionEvent event)
    {
        Login_Progress.setVisible(true);
        PauseTransition whis = new PauseTransition();
        whis.setDuration(Duration.seconds(3));
        whis.setOnFinished(dbz -> {
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Success!");
            tray.setMessage("Welcome " + Admin_Login_Email.getText());
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.seconds(2));

            AnchorPane S_pane = null;
            try {
                S_pane = FXMLLoader.load(getClass().getResource("Place_Holder.fxml"));  //needs the fxml for the homepage
            } catch (IOException e) {
                e.printStackTrace();
            }
            Admin_Login_Pane.getChildren().setAll(S_pane);
        });

        try {
            DataValidation.Validator(Admin_Login_Email.getText().toUpperCase(), Admin_Login_Password.getText());
            String Email    = Admin_Login_Email.getText().toUpperCase();
            String Password = Admin_Login_Password.getText();
            Connection conn = DriverManager.getConnection(DB_URL,user,pass);
            ResultSet resultSet = conn.createStatement().executeQuery("SELECT * FROM Test.Credentials WHERE Email =('"+Email+"') AND Password_field =('"+Password+"')");

            int count = 0;

            while (resultSet.next())
            {
                count=count+1;
            }

            if (count == 1)
            {
                whis.play();
            }
            else {Login_Progress.setVisible(false);
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.SLIDE;
                tray.setAnimationType(type);
                tray.setTitle("Unsuccessful ");
                tray.setMessage("Credentials Incorrect");
                tray.setNotificationType(NotificationType.NOTICE);
                tray.showAndDismiss(Duration.seconds(5));}


        } catch (Exception e) {
            Login_Progress.setVisible(false);
        }
    }

    @FXML
    private void Admin_Forgot_Password(ActionEvent event) {
    }

    @FXML
    private void Teacher_Login_Prortal(ActionEvent event)
    {
        AnchorPane L_pane = null;
        try {
            L_pane = FXMLLoader.load(getClass().getResource("Teacher_Login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Admin_Login_Pane.getChildren().setAll(L_pane);
    }

    @FXML
    private void Load_Admin_Login(ActionEvent event)
    {
        AnchorPane admin_pane = null;
        try {
            admin_pane = FXMLLoader.load(getClass().getResource("Admin_Login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Login_Pane.getChildren().setAll(admin_pane);
    }
}
