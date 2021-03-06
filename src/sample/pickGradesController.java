package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.classes.Cookies;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;

public class pickGradesController implements Initializable {

    public AnchorPane chooseGradePane;
    public ComboBox gradeCombo;

    //database information
    final String DB_URL = "jdbc:sqlserver://COT-CIS3365-09;database=SKDB;";
    final String user = "sa";
    final String pass = "549657Ll";

    int cookieAccountID;


    public ObservableList<Integer> teacherGrades = FXCollections.observableArrayList();

    public void passData(int cookieAccountID) {
        this.cookieAccountID = cookieAccountID;

        try {
            Connection conn = DriverManager.getConnection(DB_URL, user, pass);

//            ResultSet rs4teacherInfo = null;
//            PreparedStatement stmt = null;
//            stmt = conn.prepareStatement("SELECT * FROM [Teacher.Accounts] WHERE AccountID = ?");
//            stmt.setInt(1, cookieAccountID);
//            rs4teacherInfo = stmt.executeQuery();

            ResultSet rs4Grades = null;
            PreparedStatement stmt = null;
            stmt = conn.prepareStatement("SELECT Grade from [Teacher.Grades] WHERE AccountID = ?");
            stmt.setInt(1, cookieAccountID);
            rs4Grades = stmt.executeQuery();
            while (rs4Grades.next()) {
                teacherGrades.addAll(rs4Grades.getInt("Grade"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        gradeCombo.setItems(teacherGrades);
    }

        @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void searchSupplies(ActionEvent actionEvent) throws IOException {
        int grade = (int) gradeCombo.getValue();

        int year = Calendar.getInstance().get(Calendar.YEAR);

        //Local date instance
        LocalDate localDate = LocalDate.now();

        //Get LocalDate from SQL date
        java.sql.Date sqlDate = java.sql.Date.valueOf( localDate );

        try {
            Connection conn = DriverManager.getConnection(DB_URL, user, pass);


            ResultSet rs = null;
            PreparedStatement stmt = null;
            stmt = conn.prepareStatement("INSERT INTO [TEACHER.SUPPLYLIST] (School_Year, Date_created, grade, Account_ID) VALUES (?,?,?,?)");
            stmt.setInt(1, year);
            stmt.setDate(2, sqlDate);
            stmt.setInt(3, grade);
            stmt.setInt(4, Cookies.getAccountId());
            stmt.executeUpdate();


            System.out.println("Record inserted in supply list table");

            stmt = conn.prepareStatement("SELECT TOP 1 LIST_ID FROM [TEACHER.SUPPLYLIST] ORDER BY LIST_ID DESC");
            rs = stmt.executeQuery();

            while (rs.next()) {
                int currentListID = rs.getInt("List_ID");
                Cookies.setList_ID(currentListID);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MylistandSearch.fxml"));
        Parent GUI = loader.load();
        Scene scene = new Scene(GUI);
        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
