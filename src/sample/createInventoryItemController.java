package sample;

import sample.classes.*;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class createInventoryItemController implements Initializable {
    public TextField nameTxt;
    public TextArea descriptionTxt;
    public Spinner qtySpinner;
    public ComboBox categoryCombo;
    public ComboBox deptCombo;
    public ComboBox brandCombo;
    public Button uploadBtn;
    public TextField urlTxt;
    public TextArea notesTxt;
    public JFXButton addItemBtn;
    public ImageView imageView;

    final String DB_URL = "jdbc:sqlserver://COT-CIS3365-09;database=SKDB;";
    final String user = "sa";
    final String pass = "549657Ll";

    public FileInputStream dbImage;
    public Image image;
    public FileChooser fileChooser;
    public File file;
    public Desktop desktop = Desktop.getDesktop();

    public ObservableList<String> categories = FXCollections.observableArrayList();
    public ObservableList<String> departments = FXCollections.observableArrayList();
    public ObservableList<String> brands = FXCollections.observableArrayList();


    public supplyItem supplyItem = new supplyItem();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Fill combo boxes with array list of items from respective tables
        categoryCombo.setItems(categories);
        deptCombo.setItems(departments);
        brandCombo.setItems(brands);

        //Fill qtySpinner object with integers
        SpinnerValueFactory<Integer> qtyValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,999);
        this.qtySpinner.setValueFactory(qtyValueFactory);
        qtySpinner.setEditable(true);

        try {
            Connection conn = DriverManager.getConnection(DB_URL, user, pass);
            ResultSet rs = null;

            //Fill categories combo box from categories table in database
            PreparedStatement stmt1 = null;
            stmt1 = conn.prepareStatement("SELECT * FROM DBO.[SchoolKits.Categories]");
            rs = stmt1.executeQuery();
            while (rs.next()) {
                categories.addAll(rs.getString("CATEGORY"));
            }

            //Fill brands combo boc from brands table in database
            PreparedStatement stmt2 = null;
            stmt2 = conn.prepareStatement("SELECT * FROM DBO.[SchoolKits.BRANDS]");
            rs = stmt2.executeQuery();
            while (rs.next()) {
                brands.addAll(rs.getString("BRAND"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void uploadImage(ActionEvent actionEvent) {

        /**
         * function to get image from users computer
         */
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        fileChooser = new FileChooser();
        String userDirectory = System.getProperty("user.home") + "\\Pictures";
        File user = new File(userDirectory);
        if (!user.canRead()) { user = new File ("c:/"); }
        fileChooser.setInitialDirectory(user);
        this.file = fileChooser.showOpenDialog(stage);
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            supplyItem.setImage(image);
            imageView.setImage(supplyItem.getImage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addItem(ActionEvent actionEvent) {
        /**
         * Function to add a supply item into the database
         */
        String name = nameTxt.getText();
        String description = descriptionTxt.getText();
        int quantity = (int) qtySpinner.getValue();
        String category = (String) deptCombo.getValue();
        String brand = (String) brandCombo.getValue();
        Image image = imageView.getImage();
        String url = urlTxt.getText();
        String notes = notesTxt.getText();
        supplyItem = new supplyItem(name, description, quantity, category, brand, image, url, notes);

        try {
            Connection conn = DriverManager.getConnection(DB_URL, user, pass);

            PreparedStatement stmt = null;
            stmt = conn.prepareStatement("INSERT INTO DBO.[SchoolKits.SupplyInventory] (Name, Descreption, Quantity, Category, Brand, Image, URL, NOTES) VALUES (?,?,?,?,?,?,?,?)");
            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setInt(3, quantity);
            stmt.setString(4, category);
            stmt.setString(5, brand);
            dbImage = new FileInputStream(file);
            stmt.setBinaryStream(6, dbImage, file.length());
            stmt.setString(7, url);
            stmt.setString(8, notes);
            stmt.executeUpdate();
            System.out.println("Record inserted into table");

        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void fillDepartments(MouseEvent mouseEvent) {
        String category = (String) categoryCombo.getValue();
        try {
            Connection conn = DriverManager.getConnection(DB_URL, user, pass);

            //Fill department combo box from category departments table in database
            PreparedStatement stmt = null;
            stmt = conn.prepareStatement("SELECT Department FROM DBO.[SchoolKits.CategoryDepartments] WHERE category = ?");
            stmt.setString(1, category);
            ResultSet rs = stmt.executeQuery();
            departments.clear();
            while (rs.next()) {
                departments.addAll(rs.getString("DEPARTMENT"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
