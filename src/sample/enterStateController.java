package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class enterStateController implements Initializable {

    public JFXTextField stateTxt;
    public JFXButton stateBtn;

    final String DB_URL = "jdbc:sqlserver://COT-CIS3365-09;database=SKDB;";
    final String user = "sa";
    final String pass = "549657Ll";


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    public void enterState(ActionEvent actionEvent) {
        String state = stateTxt.getText();

        try {
            Connection conn = DriverManager.getConnection(DB_URL, user, pass);

            //Fill department combo box from category departments table in database
            PreparedStatement stmt = null;
            stmt = conn.prepareStatement("INSERT INTO DBO.[Validation.State](STATE) VALUES (?)");
            stmt.setString(1, state);
            ResultSet rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
