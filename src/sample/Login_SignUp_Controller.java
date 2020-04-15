package sample;

import com.jfoenix.controls.*;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import sample.classes.Cookies;
import sample.classes.DataValidation;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;


public class Login_SignUp_Controller implements Initializable {
    private boolean isMyComboBoxEmpty;
    private boolean RememberMe;
    private int User_ID;
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
    final String DB_URL = "jdbc:sqlserver://COT-CIS3365-09;database=SKDB";
    final String user = "sa";
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
    @FXML
    private JFXTextField Sign_Up_F_Name;
    @FXML
    private JFXTextField Sign_Up_L_Name;
    @FXML
    private JFXTextField Sign_Up_Phone_Number;
    @FXML
    private JFXPasswordField Profile_Password;
    @FXML
    private JFXPasswordField Profile_Confirm_Password;
    private String S_F_Name;
    private String S_L_Name;
    private String S_Phone;
    private String S_Date;
    @FXML
    private JFXTextField Admin_Email_Recovery;
    @FXML
    private AnchorPane Forgot_Admin_Password_Pane;
    @FXML
    private Label Cookie;
    @FXML
    private JFXCheckBox Remember_Me_Check;

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
            if (Remember_Me_Check.isSelected())
            {
                RememberMe = true;
            }
        });


        try {
            DataValidation.Validator(Login_Email.getText().toUpperCase(), Login_Password.getText());
            String Email    = Login_Email.getText().toUpperCase();
            String Password = Login_Password.getText();
            Connection conn = DriverManager.getConnection(DB_URL,user,pass);
            ResultSet resultSet = conn.createStatement().executeQuery(" SELECT DBO.[Teacher.Accounts].AccountID, DBO.[Teacher.AccountPassword].AccountID, DBO.[Teacher.Accounts].Email, DBO.[Teacher.AccountPassword].Password" +
                                                                            " FROM ((DBO.[Teacher.Accounts] INNER JOIN DBO.[Teacher.AccountPassword] ON DBO.[Teacher.Accounts].AccountID = DBO.[Teacher.AccountPassword].AccountID)) " +
                                                                            "WHERE Email =('"+Email+"') AND Password =('"+Password+"')");
            int count = 0;

            while (resultSet.next())
            {
                count=count+1;
                Cookie.setText(resultSet.getString("AccountID"));
            }
            String ID = Cookie.getText();
            ResultSet User_Set = conn.createStatement().executeQuery("SELECT DBO.[Teacher.Accounts].First_Name, DBO.[Teacher.Accounts].Last_Name, DBO.[Teacher.Accounts].Account_Type FROM DBO.[Teacher.Accounts]" +
                                                                        "WHERE AccountID =('"+ID+"')");

            if (count == 1)
            {
                while (User_Set.next())
                {
                    Cookies.setUser(Email, Integer.parseInt(ID), User_Set.getString("First_Name"), User_Set.getString("Last_Name"), User_Set.getString("Account_Type"));
                }
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
            Login_Progress.setVisible(false); e.printStackTrace();
        }
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

            DataValidation.SignUP_Validator(SignUp_Email.getText().toUpperCase(), Sign_Up_F_Name.getText(), Sign_Up_L_Name.getText(), Sign_Up_Phone_Number.getText());
            S_F_Name = Sign_Up_F_Name.getText().toUpperCase();
            S_L_Name = Sign_Up_L_Name.getText().toUpperCase();
            S_Email = SignUp_Email.getText().toUpperCase();
            S_Phone = Sign_Up_Phone_Number.getText();
            S_School = Combo_Box_School.getValue().toString();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();
            S_Date = dtf.format(now);


            Connection conn = DriverManager.getConnection(DB_URL,user,pass);
            PreparedStatement stmt = null;

            stmt = conn.prepareStatement("INSERT INTO dbo.[Teacher.Accounts] (First_Name,Last_Name,Email,School,Phone_Number,Date_Created,Account_Type)" +
                                                "VALUES (?,?,?,?,?,?,?)");
            stmt.setString(1, S_F_Name);
            stmt.setString(2, S_L_Name);
            stmt.setString(3, S_Email);
            stmt.setString(4, S_School);
            stmt.setString(5, S_Phone);
            stmt.setString(6, S_Date);
            stmt.setString(7, "Teacher");
            stmt.executeUpdate();

            conn.commit();
            stmt.close();
            conn.close();

            DataValidation.setFName(S_F_Name);
            DataValidation.setLname(S_L_Name);
            DataValidation.setEmail(S_Email);
            String Type = "Teacher";

            Cookies.setUser(S_F_Name,S_L_Name,S_Email, Type);

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
        try {
            Connection conn = DriverManager.getConnection(DB_URL, user, pass);
            ResultSet resultSet = conn.createStatement().executeQuery("SELECT * FROM DBO.[SchoolKits.SchoolList]");

            while (resultSet.next()){
            School.addAll(resultSet.getString("Name"));
            }
        } catch (SQLException e) { e.printStackTrace();}
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



        ResultSet resultSet = conn.createStatement().executeQuery("SELECT DBO.[Teacher.Accounts].AccountID,DBO.[Teacher.Security_Validation].AccountID,DBO.[Teacher.AccountSecurityQuestions].AccountID,DBO.[Teacher.AccountPassword].AccountID," +
                "       DBO.[Teacher.Accounts].Email," +
                "       DBO.[Teacher.Security_Validation].Security_Q1," +
                "       DBO.[Teacher.Security_Validation].Security_Q2," +
                "       dbo.[Teacher.AccountSecurityQuestions].Sq1Answer," +
                "       DBO.[Teacher.AccountSecurityQuestions].Sq2Answer," +
                "       DBO.[Teacher.AccountPassword].Password FROM ((DBO.[Teacher.Accounts] INNER JOIN DBO.[Teacher.Security_Validation] ON DBO.[Teacher.Accounts].AccountID = DBO.[Teacher.Security_Validation].AccountID)" +
                                              "INNER JOIN [Teacher.AccountSecurityQuestions] ON DBO.[Teacher.Accounts].AccountID = DBO.[Teacher.AccountSecurityQuestions].AccountID" +
                "                  INNER JOIN [Teacher.AccountPassword] ON DBO.[Teacher.Accounts].AccountID = DBO.[Teacher.AccountPassword].AccountID)" +
                "WHERE Email =('"+Recovery_Email+"')" +
                "  AND Security_Q1 =('"+Q1+"')"+
                "  AND Security_Q2 =('"+Q2+"')" +
                "  AND Sq1Answer =('"+Q1_Answer+"')"+
                "  AND Sq2Answer =('"+Q2_Answer+"')");

            int count = 0;

            while (resultSet.next())
            {
                count=count+1;
                if (count == 1)
                {
                    Recovery_Password.setText(resultSet.getString("Password"));
                    Recovery_Password.setVisible(false);
                    Alert Box = new Alert(Alert.AlertType.INFORMATION);
                    Box.setTitle("Recovered Password");
                    Box.setHeaderText(Recovery_Password.getText());
                    Box.show();
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
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        S_Date = dtf.format(now);

        Profile_Progress.setVisible(true);
        PauseTransition whis = new PauseTransition();
        whis.setDuration(Duration.seconds(3));
        whis.setOnFinished(dbz -> {
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Successfully Finished Account!");
            tray.setMessage("Welcome " + DataValidation.getFName() + " " + DataValidation.getLName());
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.seconds(1.5));

            AnchorPane Place_Holder_pane = null;
            try{
                Place_Holder_pane = FXMLLoader.load((getClass().getResource("Grade_NumStudents.fxml"))); ///// CONTINUE HERE FOR SCENE CHANGE
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
            DataValidation.Profile_Validator(Profile_Combo_Box_Security_Q1.getValue().toString(), Profile_Combo_Box_Security_Q2.getValue().toString(),
                                            Profile_QAnswer1.getText(), Profile_QAnswer2.getText(), Profile_Password.getText());

            Connection conn = DriverManager.getConnection(DB_URL,user,pass);

            ResultSet resultSet_Select = conn.createStatement().executeQuery("SELECT TOP 1 AccountID FROM DBO.[Teacher.Accounts] WHERE First_Name =('"+DataValidation.getFName()+"') AND Last_Name =('"+DataValidation.getLName()+"') AND Email =('"+DataValidation.getEMAIL()+"') ORDER BY AccountID DESC");
           resultSet_Select.next();
            int Account_ID_Cookie = resultSet_Select.getInt("AccountID");
            DataValidation.setAccount_ID_Cookie(Account_ID_Cookie);
            Cookies.setAccountId(Account_ID_Cookie);

            PreparedStatement Insert_Questions = null;
            PreparedStatement Insert_Q_Answer = null;
            PreparedStatement Insert_Passwords = null;

            Insert_Questions = conn.prepareStatement("INSERT INTO dbo.[Teacher.Security_Validation] (Security_Q1,Security_Q2,AccountID)" +
                    "VALUES (?,?,?)");
            Insert_Questions.setString(1, Profile_Combo_Box_Security_Q1.getValue().toString());
            Insert_Questions.setString(2, Profile_Combo_Box_Security_Q2.getValue().toString());
            Insert_Questions.setInt(3, Account_ID_Cookie);
            Insert_Questions.executeUpdate();
            conn.commit();
            Insert_Questions.close();

            Insert_Q_Answer = conn.prepareStatement("INSERT INTO dbo.[Teacher.AccountSecurityQuestions] (Sq1Answer,Sq2Answer,AccountID)" +
                    "VALUES (?,?,?)");
            Insert_Q_Answer.setString(1, Profile_QAnswer1.getText());
            Insert_Q_Answer.setString(2, Profile_QAnswer2.getText());
            Insert_Q_Answer.setInt(3, Account_ID_Cookie);
            Insert_Q_Answer.executeUpdate();
            conn.commit();
            Insert_Q_Answer.close();


            Insert_Passwords = conn.prepareStatement("INSERT INTO DBO.[Teacher.AccountPassword] (AccountID, Password, Date_Created)" +
                     "VALUES (?,?,?)");
            Insert_Passwords.setInt(1,Account_ID_Cookie);
            Insert_Passwords.setString(2, Profile_Password.getText());
            Insert_Passwords.setString(3, S_Date);
            Insert_Passwords.executeUpdate();
            conn.commit();
            Insert_Passwords.close();
            conn.close();


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

            AnchorPane School_Kits_Portal_pane = null;
            try {
                School_Kits_Portal_pane = FXMLLoader.load(getClass().getResource("School_Kits_Supply_Portal.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Admin_Login_Pane.getChildren().setAll(School_Kits_Portal_pane);
        });

        try {
            DataValidation.Validator(Admin_Login_Email.getText().toUpperCase(), Admin_Login_Password.getText());
            String Email    = Admin_Login_Email.getText().toUpperCase();
            String Password = Admin_Login_Password.getText();

            Connection conn = DriverManager.getConnection(DB_URL,user,pass);

            ResultSet resultSet = conn.createStatement().executeQuery("SELECT DBO.[SchoolKits.AdminAccount].AdminAccount_ID,DBO.[SchoolKits.AdminAccount].First_Name,DBO.[SchoolKits.AdminAccount].Last_Name,DBO.[SchoolKits.AdminAccount].First_Name,DBO.[SchoolKits.AdminAccount].Email,DBO.[SchoolKits.AdminAccount].Account_Type " +
                    "FROM DBO.[SchoolKits.AdminAccount] INNER JOIN DBO.[SchoolKits.AccountPasswords] ON DBO.[SchoolKits.AdminAccount].AdminAccount_ID = DBO.[SchoolKits.AccountPasswords].AdminAccount_ID WHERE Email =('"+Email+"') AND Password =('"+Password+"')");

            int count = 0;

            while (resultSet.next())
            {
                count=count+1;
                Cookie.setText(resultSet.getString("AdminAccount_ID"));
                String ID = Cookie.getText();
                Cookies.setUser(Email, Integer.parseInt(ID), resultSet.getString("First_Name"), resultSet.getString("Last_Name"), resultSet.getString("Account_Type"));
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
            e.printStackTrace(); Login_Progress.setVisible(false);
        }
    }

    @FXML
    private void Admin_Forgot_Password(ActionEvent event)
    {
        AnchorPane Forgot_Password = null;
        try{
            Forgot_Password = FXMLLoader.load(getClass().getResource("Admin_Password_Recovery.fxml"));
        } catch (IOException e) { e.printStackTrace();}
       Admin_Login_Pane.getChildren().setAll(Forgot_Password);
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

    @FXML
    private void Close_app(ActionEvent event)
    {
        System.exit(0);
    }

    @FXML
    private void Load_Login_From_Admin_Recovery(ActionEvent event)
    {
        AnchorPane Admin_F_Pass_Pane = null;
        try {
            Admin_F_Pass_Pane = FXMLLoader.load(getClass().getResource("Admin_Login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Forgot_Admin_Password_Pane.getChildren().setAll(Admin_F_Pass_Pane);
    }

    @FXML
    private void Submit_Admin_Recovery(ActionEvent event)
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
            DataValidation.Password_Recovery_Validator(Admin_Email_Recovery.getText(),
                    Combo_Box_Security_Q1.getValue().toString(),
                    Q1_Input_Field.getText(),
                    Combo_Box_Security_Q2.getValue().toString(),
                    Q2_Input_Field.getText());


            String Recovery_Email = Admin_Email_Recovery.getText().toUpperCase();
            String Q1 = Combo_Box_Security_Q1.getValue().toString();
            String Q1_Answer = Q1_Input_Field.getText();
            String Q2 = Combo_Box_Security_Q2.getValue().toString();
            String Q2_Answer = Q2_Input_Field.getText();

            Connection conn = null;
            conn = DriverManager.getConnection(DB_URL,user,pass);

            ResultSet resultSet = conn.createStatement().executeQuery("SELECT DBO.[SchoolKits.AdminAccount].AdminAccount_ID,DBO.[SchoolKits.Security_Validation].AdminAccount_ID,DBO.[SchoolKits.AccountSecurityQuestions].AdminAccount_ID,DBO.[SchoolKits.AccountPasswords].AdminAccount_ID," +
                    "       DBO.[SchoolKits.AdminAccount].Email," +
                    "       DBO.[SchoolKits.Security_Validation].Security_Q1," +
                    "       DBO.[SchoolKits.Security_Validation].Security_Q2," +
                    "       DBO.[SchoolKits.AccountSecurityQuestions].Sq1Answer," +
                    "       DBO.[SchoolKits.AccountSecurityQuestions].Sq2Answer," +
                    "       DBO.[SchoolKits.AccountPasswords].Password  FROM ((DBO.[SchoolKits.AdminAccount] INNER JOIN DBO.[SchoolKits.Security_Validation] ON DBO.[SchoolKits.AdminAccount].AdminAccount_ID = DBO.[SchoolKits.Security_Validation].AdminAccount_ID)" +
                    "INNER JOIN [SchoolKits.AccountSecurityQuestions] ON DBO.[SchoolKits.AdminAccount].AdminAccount_ID = DBO.[SchoolKits.AccountSecurityQuestions].AdminAccount_ID" +
                    "                 INNER JOIN [SchoolKits.AccountPasswords] ON DBO.[SchoolKits.AdminAccount].AdminAccount_ID = DBO.[SchoolKits.AccountPasswords].AdminAccount_ID)" +
                    "WHERE Email =('"+Recovery_Email+"')" +
                    "  AND Security_Q1 =('"+Q1+"')"+
                    "  AND Security_Q2 =('"+Q2+"')" +
                    "  AND Sq1Answer =('"+Q1_Answer+"')"+
                    "  AND Sq2Answer =('"+Q2_Answer+"')");

            int count = 0;

            while (resultSet.next())
            {
                count=count+1;
                if (count == 1)
                {
                    Recovery_Password.setText(resultSet.getString("Password"));
                    Recovery_Password.setVisible(false);
                    Alert Box = new Alert(Alert.AlertType.INFORMATION);
                    Box.setTitle("Recovered Password");
                    Box.setHeaderText(Recovery_Password.getText());
                    Box.show();
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
}
