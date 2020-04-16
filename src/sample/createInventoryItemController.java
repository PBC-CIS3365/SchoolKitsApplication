package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
    public ComboBox vendorCombo;
    public TextField priceTxt;
    public JFXButton submitBtn;
    public Label enterItemInfoLabel;

    //database information
    final String DB_URL = "jdbc:sqlserver://COT-CIS3365-09;database=SKDB;";
    final String user = "sa";
    final String pass = "549657Ll";

    //components to gather image
    public FileInputStream dbImage;
    public FileChooser fileChooser;
    public File file;
    public Image image;

    //observable lists to populate the combo boxes
    public ObservableList<String> categories = FXCollections.observableArrayList();
    public ObservableList<String> departments = FXCollections.observableArrayList();
    public ObservableList<String> brands = FXCollections.observableArrayList();
    public ObservableList<String> vendors = FXCollections.observableArrayList();
    public JFXButton backButton;

    //initial resultset variables
    ResultSet rs4vendor = null;
    ResultSet rs4supplyID = null;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Fill combo boxes with array list of items from respective tables
        categoryCombo.setItems(categories);
        deptCombo.setItems(departments);
        brandCombo.setItems(brands);
        vendorCombo.setItems(vendors);

        //Fill qtySpinner object with integers
        SpinnerValueFactory<Integer> qtyValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,999);
        this.qtySpinner.setValueFactory(qtyValueFactory);
        qtySpinner.setEditable(true);

        try {
            Connection conn = DriverManager.getConnection(DB_URL, user, pass);
            ResultSet rs = null;
            PreparedStatement stmt1 = null;

            //Fill categories combo box from categories table in database
            stmt1 = conn.prepareStatement("SELECT * FROM DBO.[SchoolKits.CATEGORIES]");
            rs = stmt1.executeQuery();
            while (rs.next()) {
                categories.addAll(rs.getString("CATEGORY"));
            }

            //Fill brands combo box from brands table in database
            PreparedStatement stmt2 = null;
            stmt2 = conn.prepareStatement("SELECT * FROM DBO.[SchoolKits.BRANDS]");
            rs = stmt2.executeQuery();
            while (rs.next()) {
                brands.addAll(rs.getString("BRAND"));
            }

            ResultSet rs1 = null;
            //Fill vendor combo box with vendor data from database
            PreparedStatement stmt = null;
            stmt = conn.prepareStatement("SELECT * FROM DBO.[SCHOOLKITS.VENDORS]");
            rs1 = stmt.executeQuery();
            while (rs1.next()) {
                vendors.addAll(rs1.getString("VENDOR_NAME"));
            }
        } catch (SQLException e) {
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
            imageView.setImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void submitItem(ActionEvent actionEvent) {

        /**
         * Function to add a supply item into the database
         */

        //store user input in variables to pass into parameters for SQL statement
        String name = nameTxt.getText();
        String description = descriptionTxt.getText();
        int quantity = (int) qtySpinner.getValue();
        String category = (String) deptCombo.getValue();
        String brand = (String) brandCombo.getValue();
        Image image = imageView.getImage();
        String url = urlTxt.getText();
        String notes = notesTxt.getText();

        DataValidation.getSupplyName(name);

        try {
            Connection conn = DriverManager.getConnection(DB_URL, user, pass);

            PreparedStatement stmt = null;
            stmt = conn.prepareStatement("INSERT INTO DBO.[SchoolKits.SupplyInventory] (supply_name, Description, Quantity, Category, Brand, Image, URL, NOTES) VALUES (?,?,?,?,?,?,?,?)");
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
            System.out.println("Record inserted into supply inventory table");

        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }

        //set components to collect vendor information to true
        addItemBtn.setVisible(true);
        submitBtn.setDisable(true);
        vendorCombo.setVisible(true);
        priceTxt.setVisible(true);
        enterItemInfoLabel.setVisible(true);

        //disable everything else
        nameTxt.setDisable(true);
        descriptionTxt.setDisable(true);
        qtySpinner.setDisable(true);
        categoryCombo.setDisable(true);
        deptCombo.setDisable(true);
        brandCombo.setDisable(true);
        uploadBtn.setDisable(true);
        urlTxt.setDisable(true);
        notesTxt.setDisable(true);
    }

    public void addItem(ActionEvent actionEvent) throws IOException {

        /**
         * function to insert price for supplyID into schoolkits.supplyitemprice
         */
        float getPrice = Float.parseFloat(priceTxt.getText());
        double price = (double) Math.round(getPrice*100)/100;
        String vendor = (String)vendorCombo.getValue();

        try {
            Connection conn = DriverManager.getConnection(DB_URL, user, pass);

            int vendorID = 0;
            int supplyID = 0;

            PreparedStatement stmt = conn.prepareStatement("SELECT VendorID FROM [SCHOOLKITS.VENDORS] WHERE VENDOR_NAME = ?");
            stmt.setString(1, vendor);
            rs4vendor = stmt.executeQuery();
            while (rs4vendor.next()) {
                vendorID = rs4vendor.getInt("VendorID");
            }

            PreparedStatement stmt1 = conn.prepareStatement("SELECT TOP 1 SupplyID FROM DBO.[SchoolKits.SupplyInventory] ORDER BY SupplyID DESC");
            rs4supplyID = stmt1.executeQuery();
            while (rs4supplyID.next()) {
                supplyID = rs4supplyID.getInt(1);
            }

            PreparedStatement stmt2 = null;
            stmt2 = conn.prepareStatement("INSERT INTO [SCHOOLKITS.SUPPLYITEMPRICE] (SUPPLY_ID, VENDOR_ID, PRICE) VALUES(?,?,?)");
            stmt2.setInt(1, supplyID);
            stmt2.setInt(2, vendorID);
            stmt2.setDouble(3, price);
            stmt2.executeUpdate();

            System.out.println("Inserted price record with supplyID: " + supplyID);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //open scene for table view to view all inventory items
        goBackToViewInventory(actionEvent);
    }

    public void goBackToViewInventory(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("viewInventoryItems.fxml"));
        Parent GUI = loader.load();
        Scene scene = new Scene(GUI);

        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }
}
