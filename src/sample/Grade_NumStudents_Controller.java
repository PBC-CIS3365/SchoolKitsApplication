package sample;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import sample.classes.Cookies;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Grade_NumStudents_Controller implements Initializable
{
    private boolean isMyComboBoxEmpty;
    private boolean Ready;
    final String DB_URL = "jdbc:sqlserver://COT-CIS3365-09;database=SKDB";
    final String user = "sa";
    final String pass = "549657Ll";

    @FXML
    private JFXCheckBox One_Check;
    @FXML
    private JFXCheckBox Two_Check;
    @FXML
    private JFXCheckBox Three_Check;
    @FXML
    private JFXCheckBox Four_Check;
    @FXML
    private JFXCheckBox Five_Check;
    @FXML
    private JFXCheckBox Sixth_Check;
    @FXML
    private JFXCheckBox Seven_Check;
    @FXML
    private JFXCheckBox Eight_Check;
    @FXML
    private JFXCheckBox Nine_Check;
    @FXML
    private JFXCheckBox Ten_Check;
    @FXML
    private JFXCheckBox Eleven_Check;
    @FXML
    private JFXCheckBox Twelve_Check;

    ObservableList<String> One = FXCollections.observableArrayList();
    ObservableList<String> Two = FXCollections.observableArrayList();
    ObservableList<String> Three = FXCollections.observableArrayList();
    ObservableList<String> Four = FXCollections.observableArrayList();
    ObservableList<String> Five = FXCollections.observableArrayList();
    ObservableList<String> Six = FXCollections.observableArrayList();
    ObservableList<String> Seven = FXCollections.observableArrayList();
    ObservableList<String> Eight = FXCollections.observableArrayList();
    ObservableList<String> Nine = FXCollections.observableArrayList();
    ObservableList<String> Ten = FXCollections.observableArrayList();
    ObservableList<String> Eleven = FXCollections.observableArrayList();
    ObservableList<String> Twelve = FXCollections.observableArrayList();

    @FXML
    private JFXComboBox Combo_Box_1;
    @FXML
    private JFXComboBox Combo_Box_2;
    @FXML
    private JFXComboBox Combo_Box_3;
    @FXML
    private JFXComboBox Combo_Box_4;
    @FXML
    private JFXComboBox Combo_Box_5;
    @FXML
    private JFXComboBox Combo_Box_6;
    @FXML
    private JFXComboBox Combo_Box_7;
    @FXML
    private JFXComboBox Combo_Box_8;
    @FXML
    private JFXComboBox Combo_Box_9;
    @FXML
    private JFXComboBox Combo_Box_10;
    @FXML
    private JFXComboBox Combo_Box_11;
    @FXML
    private JFXComboBox Combo_Box_12;
    @FXML
    private AnchorPane Grade_Pane;
    @FXML
    private ImageView Grade_Progress;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {}

    @FXML
    private void Close_app(ActionEvent event) 
    {
        System.exit(0);
    }


    @FXML
    private void One_Check(ActionEvent event)
    {
        if (One_Check.isSelected())
        {
            Combo_Box_1.setVisible(true);
            Combo_Box_1.setDisable(false);
        }
        else {Combo_Box_1.setVisible(false); Combo_Box_1.setDisable(true);}
    }

    @FXML
    private void Two_Check(ActionEvent event)
    {
        if (Two_Check.isSelected())
        {
            Combo_Box_2.setVisible(true);
            Combo_Box_2.setDisable(false);
        }
        else {Combo_Box_2.setVisible(false); Combo_Box_2.setDisable(true);}
    }

    @FXML
    private void Three_Check(ActionEvent event)
    {
        if (Three_Check.isSelected())
        {
            Combo_Box_3.setVisible(true);
            Combo_Box_3.setDisable(false);
        }
        else {Combo_Box_3.setVisible(false); Combo_Box_3.setDisable(true);}
    }

    @FXML
    private void Four_Check(ActionEvent event)
    {
        if (Four_Check.isSelected())
        {
            Combo_Box_4.setVisible(true);
            Combo_Box_4.setDisable(false);
        }
        else {Combo_Box_4.setVisible(false); Combo_Box_4.setDisable(true);}
    }

    @FXML
    private void Five_Check(ActionEvent event)
    {
        if (Five_Check.isSelected())
        {
            Combo_Box_5.setVisible(true);
            Combo_Box_5.setDisable(false);
        }
        else {Combo_Box_5.setVisible(false); Combo_Box_5.setDisable(true);}
    }

    @FXML
    private void Sixth_Check(ActionEvent event)
    {
        if (Sixth_Check.isSelected())
        {
            Combo_Box_6.setVisible(true);
            Combo_Box_6.setDisable(false);
        }
        else {Combo_Box_6.setVisible(false); Combo_Box_6.setDisable(true);}
    }

    @FXML
    private void Seven_Check(ActionEvent event)
    {
        if (Seven_Check.isSelected())
        {
            Combo_Box_7.setVisible(true);
            Combo_Box_7.setDisable(false);
        }
        else {Combo_Box_7.setVisible(false); Combo_Box_7.setDisable(true);}
    }

    @FXML
    private void Eight_Check(ActionEvent event)
    {
        if (Eight_Check.isSelected())
        {
            Combo_Box_8.setVisible(true);
            Combo_Box_8.setDisable(false);
        }
        else {Combo_Box_8.setVisible(false); Combo_Box_8.setDisable(true);}
    }

    @FXML
    private void Nine_Check(ActionEvent event)
    {
        if (Nine_Check.isSelected())
        {
            Combo_Box_9.setVisible(true);
            Combo_Box_9.setDisable(false);
        }
        else {Combo_Box_9.setVisible(false); Combo_Box_9.setDisable(true);}
    }

    @FXML
    private void Ten_Check(ActionEvent event)
    {
        if (Ten_Check.isSelected())
        {
            Combo_Box_10.setVisible(true);
            Combo_Box_10.setDisable(false);
        }
        else {Combo_Box_10.setVisible(false); Combo_Box_10.setDisable(true);}
    }

    @FXML
    private void Eleven_Check(ActionEvent event)
    {
        if (Eleven_Check.isSelected())
        {
            Combo_Box_11.setVisible(true);
            Combo_Box_11.setDisable(false);
        }
        else {Combo_Box_11.setVisible(false); Combo_Box_11.setDisable(true);}
    }

    @FXML
    private void Twelve_Check(ActionEvent event)
    {
        if (Twelve_Check.isSelected())
        {
            Combo_Box_12.setVisible(true);
            Combo_Box_12.setDisable(false);
        }
        else {Combo_Box_12.setVisible(false); Combo_Box_12.setDisable(true);}
    }


    @FXML
    private void One_List(MouseEvent mouseEvent)
    {
        Combo_Box_1.setItems(One);
        Combo_Box_1.setStyle("-fx-background-color: a0a2ab;");
        One.clear();
        One.setAll("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15",
                "16","17","18","19","20","21","22","23","24","25","26","27","28","29","30");
    }

    @FXML
    private void Two_List(MouseEvent mouseEvent)
    {
        Combo_Box_2.setItems(Two);
        Combo_Box_2.setStyle("-fx-background-color: a0a2ab;");
        Two.clear();
        Two.setAll("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15",
                "16","17","18","19","20","21","22","23","24","25","26","27","28","29","30");
    }

    @FXML
    private void Three_List(MouseEvent mouseEvent)
    {
        Combo_Box_3.setItems(Three);
        Combo_Box_3.setStyle("-fx-background-color: a0a2ab;");
        Three.clear();
        Three.setAll("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15",
                "16","17","18","19","20","21","22","23","24","25","26","27","28","29","30");
    }

    @FXML
    private void Four_List(MouseEvent mouseEvent)
    {
        Combo_Box_4.setItems(Four);
        Combo_Box_4.setStyle("-fx-background-color: a0a2ab;");
        Four.clear();
        Four.setAll("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15",
                "16","17","18","19","20","21","22","23","24","25","26","27","28","29","30");
    }

    @FXML
    private void Five_List(MouseEvent mouseEvent)
    {
        Combo_Box_5.setItems(Five);
        Combo_Box_5.setStyle("-fx-background-color: a0a2ab;");
        Five.clear();
        Five.setAll("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15",
                "16","17","18","19","20","21","22","23","24","25","26","27","28","29","30");
    }

    @FXML
    private void Six_List(MouseEvent mouseEvent)
    {
        Combo_Box_6.setItems(Six);
        Combo_Box_6.setStyle("-fx-background-color: a0a2ab;");
        Six.clear();
        Six.setAll("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15",
                "16","17","18","19","20","21","22","23","24","25","26","27","28","29","30");
    }

    @FXML
    private void Seven_List(MouseEvent mouseEvent)
    {
        Combo_Box_7.setItems(Seven);
        Combo_Box_7.setStyle("-fx-background-color: a0a2ab;");
        Seven.clear();
        Seven.setAll("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15",
                "16","17","18","19","20","21","22","23","24","25","26","27","28","29","30");
    }

    @FXML
    private void Eight_List(MouseEvent mouseEvent)
    {
        Combo_Box_8.setItems(Eight);
        Combo_Box_8.setStyle("-fx-background-color: a0a2ab;");
        Eight.clear();
        Eight.setAll("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15",
                "16","17","18","19","20","21","22","23","24","25","26","27","28","29","30");
    }

    @FXML
    private void Nine_List(MouseEvent mouseEvent)
    {
        Combo_Box_9.setItems(Nine);
        Combo_Box_9.setStyle("-fx-background-color: a0a2ab;");
        Nine.clear();
        Nine.setAll("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15",
                "16","17","18","19","20","21","22","23","24","25","26","27","28","29","30");
    }

    @FXML
    private void Ten_List(MouseEvent mouseEvent)
    {
        Combo_Box_10.setItems(Ten);
        Combo_Box_10.setStyle("-fx-background-color: a0a2ab;");
        Ten.clear();
        Ten.setAll("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15",
                "16","17","18","19","20","21","22","23","24","25","26","27","28","29","30");
    }

    @FXML
    private void Eleven_List(MouseEvent mouseEvent)
    {
        Combo_Box_11.setItems(Eleven);
        Combo_Box_11.setStyle("-fx-background-color: a0a2ab;");
        Eleven.clear();
        Eleven.setAll("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15",
                "16","17","18","19","20","21","22","23","24","25","26","27","28","29","30");
    }

    @FXML
    private void Twelve_List(MouseEvent mouseEvent)
    {
        Combo_Box_12.setItems(Twelve);
        Combo_Box_12.setStyle("-fx-background-color: a0a2ab;");
        Twelve.clear();
        Twelve.setAll("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15",
                "16","17","18","19","20","21","22","23","24","25","26","27","28","29","30");
    }

    @FXML
    private void Submit_Grades_Num_Students(ActionEvent event) {
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);

        if (!One_Check.isSelected() && !Two_Check.isSelected() && !Three_Check.isSelected() && !Four_Check.isSelected() && !Five_Check.isSelected() && !Sixth_Check.isSelected() &&
                !Seven_Check.isSelected() && !Eight_Check.isSelected() && !Nine_Check.isSelected() && !Ten_Check.isSelected() && !Eleven_Check.isSelected() && !Twelve_Check.isSelected()) {
            tray.setTitle("Unsuccessful");
            tray.setMessage("Please Select a Grade");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(5));
        }
        else {
            Grade_Progress.setVisible(true);
            PauseTransition whis = new PauseTransition();
            whis.setDuration(Duration.seconds(3));
            whis.setOnFinished(dbz -> {
                tray.setTitle("Success!");
                tray.setMessage("Welcome " + Cookies.getFirstName() + " " + Cookies.getLastName());
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.seconds(2));

                AnchorPane Grade_pane = null;
                try {
                    Grade_pane = FXMLLoader.load(getClass().getResource("Place_Holder.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Grade_Pane.getChildren().setAll(Grade_pane);
            });


            if (One_Check.isSelected()) {
                {
                    try {
                        if (isMyComboBoxEmpty = Combo_Box_1.getSelectionModel().isEmpty()) {
                            tray.setTitle("Unsuccessful");
                            tray.setMessage("Please choose a Quantity of Students");
                            tray.setNotificationType(NotificationType.ERROR);
                            tray.showAndDismiss(Duration.seconds(5));
                            throw new IllegalArgumentException();
                        } else {
                            int GRADE = 1;
                            Connection conn = DriverManager.getConnection(DB_URL, user, pass);
                            PreparedStatement stmt = null;

                            stmt = conn.prepareStatement("INSERT INTO dbo.[Teacher.Grades] (AccountID, Grade, NumberOfStudents) VALUES (?,?,?)");
                            stmt.setInt(1, Cookies.getAccountId());
                            stmt.setInt(2, GRADE);
                            stmt.setInt(3, Integer.valueOf(Combo_Box_1.getValue().toString()));
                            stmt.executeUpdate();
                            conn.commit();
                            stmt.close();
                            conn.close();
                            Ready = true;
                        }
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                        Ready = false;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

            if (Two_Check.isSelected()) {
                {
                    try {
                        if (isMyComboBoxEmpty = Combo_Box_2.getSelectionModel().isEmpty()) {
                            tray.setTitle("Unsuccessful");
                            tray.setMessage("Please choose a Quantity of Students");
                            tray.setNotificationType(NotificationType.ERROR);
                            tray.showAndDismiss(Duration.seconds(5));
                            throw new IllegalArgumentException();
                        } else {
                            int GRADE = 2;
                            Connection conn = DriverManager.getConnection(DB_URL, user, pass);
                            PreparedStatement stmt = null;

                            stmt = conn.prepareStatement("INSERT INTO dbo.[Teacher.Grades] (AccountID, Grade, NumberOfStudents) VALUES (?,?,?)");
                            stmt.setInt(1, Cookies.getAccountId());
                            stmt.setInt(2, GRADE);
                            stmt.setInt(3, Integer.valueOf(Combo_Box_2.getValue().toString()));
                            stmt.executeUpdate();
                            conn.commit();
                            stmt.close();
                            conn.close();
                            Ready = true;
                        }
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                        Ready = false;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

            if (Three_Check.isSelected()) {
                {
                    try {
                        if (isMyComboBoxEmpty = Combo_Box_3.getSelectionModel().isEmpty()) {
                            tray.setTitle("Unsuccessful");
                            tray.setMessage("Please choose a Quantity of Students");
                            tray.setNotificationType(NotificationType.ERROR);
                            tray.showAndDismiss(Duration.seconds(5));
                            throw new IllegalArgumentException();
                        } else {
                            int GRADE = 3;
                            Connection conn = DriverManager.getConnection(DB_URL, user, pass);
                            PreparedStatement stmt = null;

                            stmt = conn.prepareStatement("INSERT INTO dbo.[Teacher.Grades] (AccountID, Grade, NumberOfStudents) VALUES (?,?,?)");
                            stmt.setInt(1, Cookies.getAccountId());
                            stmt.setInt(2, GRADE);
                            stmt.setInt(3, Integer.valueOf(Combo_Box_3.getValue().toString()));
                            stmt.executeUpdate();
                            conn.commit();
                            stmt.close();
                            conn.close();
                            Ready = true;
                        }
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                        Ready = false;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

            if (Four_Check.isSelected()) {
                {
                    try {
                        if (isMyComboBoxEmpty = Combo_Box_4.getSelectionModel().isEmpty()) {
                            tray.setTitle("Unsuccessful");
                            tray.setMessage("Please choose a Quantity of Students");
                            tray.setNotificationType(NotificationType.ERROR);
                            tray.showAndDismiss(Duration.seconds(5));
                            throw new IllegalArgumentException();
                        } else {
                            int GRADE = 4;
                            Connection conn = DriverManager.getConnection(DB_URL, user, pass);
                            PreparedStatement stmt = null;

                            stmt = conn.prepareStatement("INSERT INTO dbo.[Teacher.Grades] (AccountID, Grade, NumberOfStudents) VALUES (?,?,?)");
                            stmt.setInt(1, Cookies.getAccountId());
                            stmt.setInt(2, GRADE);
                            stmt.setInt(3, Integer.valueOf(Combo_Box_4.getValue().toString()));
                            stmt.executeUpdate();
                            conn.commit();
                            stmt.close();
                            conn.close();
                            Ready = true;
                        }
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                        Ready = false;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

            if (Five_Check.isSelected()) {
                {
                    try {
                        if (isMyComboBoxEmpty = Combo_Box_5.getSelectionModel().isEmpty()) {
                            tray.setTitle("Unsuccessful");
                            tray.setMessage("Please choose a Quantity of Students");
                            tray.setNotificationType(NotificationType.ERROR);
                            tray.showAndDismiss(Duration.seconds(5));
                            throw new IllegalArgumentException();
                        } else {
                            int GRADE = 5;
                            Connection conn = DriverManager.getConnection(DB_URL, user, pass);
                            PreparedStatement stmt = null;

                            stmt = conn.prepareStatement("INSERT INTO dbo.[Teacher.Grades] (AccountID, Grade, NumberOfStudents) VALUES (?,?,?)");
                            stmt.setInt(1, Cookies.getAccountId());
                            stmt.setInt(2, GRADE);
                            stmt.setInt(3, Integer.valueOf(Combo_Box_5.getValue().toString()));
                            stmt.executeUpdate();
                            conn.commit();
                            stmt.close();
                            conn.close();
                            Ready = true;
                        }
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                        Ready = false;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

            if (Sixth_Check.isSelected()) {
                {
                    try {
                        if (isMyComboBoxEmpty = Combo_Box_6.getSelectionModel().isEmpty()) {
                            tray.setTitle("Unsuccessful");
                            tray.setMessage("Please choose a Quantity of Students");
                            tray.setNotificationType(NotificationType.ERROR);
                            tray.showAndDismiss(Duration.seconds(5));
                            throw new IllegalArgumentException();
                        } else {
                            int GRADE = 6;
                            Connection conn = DriverManager.getConnection(DB_URL, user, pass);
                            PreparedStatement stmt = null;

                            stmt = conn.prepareStatement("INSERT INTO dbo.[Teacher.Grades] (AccountID, Grade, NumberOfStudents) VALUES (?,?,?)");
                            stmt.setInt(1, Cookies.getAccountId());
                            stmt.setInt(2, GRADE);
                            stmt.setInt(3, Integer.valueOf(Combo_Box_6.getValue().toString()));
                            stmt.executeUpdate();
                            conn.commit();
                            stmt.close();
                            conn.close();
                            Ready = true;
                        }
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                        Ready = false;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

            if (Seven_Check.isSelected()) {
                {
                    try {
                        if (isMyComboBoxEmpty = Combo_Box_7.getSelectionModel().isEmpty()) {
                            tray.setTitle("Unsuccessful");
                            tray.setMessage("Please choose a Quantity of Students");
                            tray.setNotificationType(NotificationType.ERROR);
                            tray.showAndDismiss(Duration.seconds(5));
                            throw new IllegalArgumentException();
                        } else {
                            int GRADE = 7;
                            Connection conn = DriverManager.getConnection(DB_URL, user, pass);
                            PreparedStatement stmt = null;

                            stmt = conn.prepareStatement("INSERT INTO dbo.[Teacher.Grades] (AccountID, Grade, NumberOfStudents) VALUES (?,?,?)");
                            stmt.setInt(1, Cookies.getAccountId());
                            stmt.setInt(2, GRADE);
                            stmt.setInt(3, Integer.valueOf(Combo_Box_7.getValue().toString()));
                            stmt.executeUpdate();
                            conn.commit();
                            stmt.close();
                            conn.close();
                        }
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                        Ready = false;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

            if (Eight_Check.isSelected()) {
                {
                    try {
                        if (isMyComboBoxEmpty = Combo_Box_8.getSelectionModel().isEmpty()) {
                            tray.setTitle("Unsuccessful");
                            tray.setMessage("Please choose a Quantity of Students");
                            tray.setNotificationType(NotificationType.ERROR);
                            tray.showAndDismiss(Duration.seconds(5));
                            throw new IllegalArgumentException();
                        } else {
                            int GRADE = 8;
                            Connection conn = DriverManager.getConnection(DB_URL, user, pass);
                            PreparedStatement stmt = null;

                            stmt = conn.prepareStatement("INSERT INTO dbo.[Teacher.Grades] (AccountID, Grade, NumberOfStudents) VALUES (?,?,?)");
                            stmt.setInt(1, Cookies.getAccountId());
                            stmt.setInt(2, GRADE);
                            stmt.setInt(3, Integer.valueOf(Combo_Box_8.getValue().toString()));
                            stmt.executeUpdate();
                            conn.commit();
                            stmt.close();
                            conn.close();
                            Ready = true;
                        }
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                        Ready = false;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

            if (Nine_Check.isSelected()) {
                {
                    try {
                        if (isMyComboBoxEmpty = Combo_Box_9.getSelectionModel().isEmpty()) {
                            ;
                            tray.setMessage("Please choose a Quantity of Students");
                            tray.setNotificationType(NotificationType.ERROR);
                            tray.showAndDismiss(Duration.seconds(5));
                            throw new IllegalArgumentException();
                        } else {
                            int GRADE = 9;
                            Connection conn = DriverManager.getConnection(DB_URL, user, pass);
                            PreparedStatement stmt = null;

                            stmt = conn.prepareStatement("INSERT INTO dbo.[Teacher.Grades] (AccountID, Grade, NumberOfStudents) VALUES (?,?,?)");
                            stmt.setInt(1, Cookies.getAccountId());
                            stmt.setInt(2, GRADE);
                            stmt.setInt(3, Integer.valueOf(Combo_Box_9.getValue().toString()));
                            stmt.executeUpdate();
                            conn.commit();
                            stmt.close();
                            conn.close();
                            Ready = true;
                        }
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                        Ready = false;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

            if (Ten_Check.isSelected()) {
                {
                    try {
                        if (isMyComboBoxEmpty = Combo_Box_10.getSelectionModel().isEmpty()) {
                            tray.setTitle("Unsuccessful");
                            tray.setMessage("Please choose a Quantity of Students");
                            tray.setNotificationType(NotificationType.ERROR);
                            tray.showAndDismiss(Duration.seconds(5));
                            throw new IllegalArgumentException();
                        } else {
                            int GRADE = 10;
                            Connection conn = DriverManager.getConnection(DB_URL, user, pass);
                            PreparedStatement stmt = null;

                            stmt = conn.prepareStatement("INSERT INTO dbo.[Teacher.Grades] (AccountID, Grade, NumberOfStudents) VALUES (?,?,?)");
                            stmt.setInt(1, Cookies.getAccountId());
                            stmt.setInt(2, GRADE);
                            stmt.setInt(3, Integer.valueOf(Combo_Box_10.getValue().toString()));
                            stmt.executeUpdate();
                            conn.commit();
                            stmt.close();
                            conn.close();
                            Ready = true;
                        }
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                        Ready = false;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

            if (Eleven_Check.isSelected()) {
                {
                    try {
                        if (isMyComboBoxEmpty = Combo_Box_11.getSelectionModel().isEmpty()) {
                            tray.setTitle("Unsuccessful");
                            tray.setMessage("Please choose a Quantity of Students");
                            tray.setNotificationType(NotificationType.ERROR);
                            tray.showAndDismiss(Duration.seconds(5));
                            throw new IllegalArgumentException();
                        } else {
                            int GRADE = 11;
                            Connection conn = DriverManager.getConnection(DB_URL, user, pass);
                            PreparedStatement stmt = null;

                            stmt = conn.prepareStatement("INSERT INTO dbo.[Teacher.Grades] (AccountID, Grade, NumberOfStudents) VALUES (?,?,?)");
                            stmt.setInt(1, Cookies.getAccountId());
                            stmt.setInt(2, GRADE);
                            stmt.setInt(3, Integer.valueOf(Combo_Box_11.getValue().toString()));
                            stmt.executeUpdate();
                            conn.commit();
                            stmt.close();
                            conn.close();
                            Ready = true;
                        }
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                        Ready = false;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

            if (Twelve_Check.isSelected()) {
                {
                    try {
                        if (isMyComboBoxEmpty = Combo_Box_12.getSelectionModel().isEmpty()) {
                            tray.setTitle("Unsuccessful");
                            tray.setMessage("Please choose a Quantity of Students");
                            tray.setNotificationType(NotificationType.ERROR);
                            tray.showAndDismiss(Duration.seconds(5));
                            throw new IllegalArgumentException();
                        } else {
                            int GRADE = 12;
                            Connection conn = DriverManager.getConnection(DB_URL, user, pass);
                            PreparedStatement stmt = null;

                            stmt = conn.prepareStatement("INSERT INTO dbo.[Teacher.Grades] (AccountID, Grade, NumberOfStudents) VALUES (?,?,?)");
                            stmt.setInt(1, Cookies.getAccountId());
                            stmt.setInt(2, GRADE);
                            stmt.setInt(3, Integer.valueOf(Combo_Box_12.getValue().toString()));
                            stmt.executeUpdate();
                            conn.commit();
                            stmt.close();
                            conn.close();
                            Ready = true;
                        }
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                        Ready = false;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (Ready) {
                whis.play();
            }
            else {Grade_Progress.setVisible(false);}
        }
    }
}
