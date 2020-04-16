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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.classes.Cookies;
import sample.classes.signedInAccount;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;

public class teacherHomeController implements Initializable {

    //database information
    final String DB_URL = "jdbc:sqlserver://COT-CIS3365-09;database=SKDB;";
    final String user = "sa";
    final String pass = "549657Ll";

    public Label numOfListsLabel;
    public Label numStudentLabel;
    public Label helloLabel;
    public Label nameLabel;
    public Label schoolLabel;
    public Label emailLabel;
    public Label accountIdCookie;
    public ComboBox gradeCombo;
    public AnchorPane chooseGradePane;

    int cookieAccountID;


    public void passData(int cookieAccountID) {
        this.cookieAccountID=cookieAccountID;

        try {
            Connection conn = DriverManager.getConnection(DB_URL, user, pass);

            ResultSet rs4teacherInfo = null;
            PreparedStatement stmt = null;
            stmt = conn.prepareStatement("SELECT * FROM [Teacher.Accounts] WHERE AccountID = ?");
            stmt.setInt(1, cookieAccountID);
            rs4teacherInfo = stmt.executeQuery();
            while (rs4teacherInfo.next()) {
                nameLabel.setText(rs4teacherInfo.getString("First_Name"));
                schoolLabel.setText(rs4teacherInfo.getString("School"));
                emailLabel.setText(rs4teacherInfo.getString("Email"));
                accountIdCookie.setText(String.valueOf(rs4teacherInfo.getInt("AccountID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

//    public void editAccount(ActionEvent actionEvent) {
//    }
//
//    public void viewList(ActionEvent actionEvent) {
//    }
//
//    public void pendingApproval(ActionEvent actionEvent) {
//    }

    public void createList(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("pickGrades4List.fxml"));
        Parent GUI = loader.load();
        Scene scene = new Scene(GUI);
        pickGradesController controller = loader.getController();
        controller.passData(Integer.parseInt(accountIdCookie.getText()));
        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void approvedList(ActionEvent actionEvent) {
    }

    public void editAccount(ActionEvent actionEvent) {
    }

    public void pendingApproval(ActionEvent actionEvent) {
    }

    public void viewList(ActionEvent actionEvent) {
    }
}
