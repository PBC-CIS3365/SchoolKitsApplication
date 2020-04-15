package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.classes.selectedSupplyItem;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class viewItemController implements Initializable {

    public JFXTextField supplyNameTxt;
    public JFXTextArea notesTxt;
    public JFXTextArea urlTxt;
    public JFXTextField priceTxt;
    public JFXTextArea descTxt;
    public Label supplyIdLabel;
    public Spinner qtySpinner;
    public Label totalValueLabel;
    public ImageView imageView;
    public JFXButton updateItemBtn;
    public JFXButton uploadImageBtn;
    public JFXButton backBtn;
    public Label catLabel;
    public Label brandLabel;
    public Label vendorLabel;
    public Label currentInventoryLabel;

    //database information
    final String DB_URL = "jdbc:sqlserver://COT-CIS3365-09;database=SKDB;";
    final String user = "sa";
    final String pass = "549657Ll";

    public ObservableList<String> categories = FXCollections.observableArrayList();
    public ObservableList<String> departments = FXCollections.observableArrayList();
    public ObservableList<String> brands = FXCollections.observableArrayList();
    public ObservableList<String> vendors = FXCollections.observableArrayList();


    //components to gather image
    public FileInputStream dbImage;
    public FileChooser fileChooser;
    public File file;
    public Image image;

    public selectedSupplyItem ssi;


    public void passData(selectedSupplyItem ssi) {
        this.ssi = ssi;

        try {
            Connection conn = DriverManager.getConnection(DB_URL, user, pass);

            //Get selected supplyItem data
            ResultSet rs = null;
            PreparedStatement stmt = null;
            stmt = conn.prepareStatement("SELECT *, " +
                    "[SchoolKits.SupplyInventory].Quantity*[SchoolKits.SupplyItemPrice].Price AS [TOTAL_VALUE] " +
                    "FROM " +
                    "[SchoolKits.SupplyInventory] " +
                    "INNER JOIN " +
                    "[SchoolKits.SupplyItemPrice] " +
                    "ON [SchoolKits.SupplyInventory].SupplyID=[SchoolKits.SupplyItemPrice].Supply_ID " +
                    "INNER JOIN " +
                    "[SchoolKits.Vendors] " +
                    "ON [SchoolKits.Vendors].VendorID=[SchoolKits.SupplyItemPrice].Vendor_ID " +
                    "WHERE [SchoolKits.SupplyInventory].SupplyID = ?");
            stmt.setInt(1, ssi.getSupplyID());
            rs = stmt.executeQuery();

            while(rs.next()) {
                supplyIdLabel.setText(String.valueOf(rs.getInt("SupplyID")));
                supplyNameTxt.setText(rs.getString("Supply_Name"));
                descTxt.setText(rs.getString("Description"));
                catLabel.setText(rs.getString("Category"));
                brandLabel.setText(rs.getString("Brand"));
                urlTxt.setText(rs.getString("URL"));
                notesTxt.setText(rs.getString("Notes"));
                vendorLabel.setText(rs.getString("Vendor_Name"));
                priceTxt.setText(String.valueOf(rs.getFloat("Price")));
                currentInventoryLabel.setText(String.valueOf(rs.getInt("Quantity")));
                qtySpinner.setPromptText(String.valueOf(ssi.getQty()));
                totalValueLabel.setText(String.valueOf(rs.getFloat("TOTAL_VALUE")));

                //set image view with invetory item image
                InputStream is = rs.getBinaryStream("Image");
                OutputStream os = new FileOutputStream(new File("photo.jpg"));
                byte[] content = new byte[1024];
                int size = 0;
                while((size = is.read(content)) != -1 ) {
                    os.write(content, 0, size);
                }
                os.close();
                is.close();

                Image image = new Image("file:photo.jpg", imageView.getFitWidth(), imageView.getFitHeight(),true, true);
                imageView.setImage(image);
            }

        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //fill vendor combo box with vendor information
//        vendorCombo.setItems(vendors);

        //Fill qtySpinner object with integers
        SpinnerValueFactory<Integer> qtyValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,999);
        this.qtySpinner.setValueFactory(qtyValueFactory);
        qtySpinner.setEditable(true);

    }

    /**
     * Function to update supply item into the database
     */
    public void updateItem(ActionEvent actionEvent) throws IOException {

        //store user input in variables to pass into parameters for SQL statement
        int supplyID = Integer.parseInt(supplyIdLabel.getText());
        String supplyName = supplyNameTxt.getText();
        String description = descTxt.getText();
        int quantity;

        //check if quantity is 0 then use current inventory label for quantity
        int check0 = (int) qtySpinner.getValue();
        if(check0==0)
            quantity= Integer.parseInt(currentInventoryLabel.getText());
        else
            quantity= (int) qtySpinner.getValue();

        Image image = imageView.getImage();
        String url = urlTxt.getText();
        String notes = notesTxt.getText();
        Float getPrice = Float.valueOf(priceTxt.getText());
        double price = (double) Math.round(getPrice*100)/100;
        String vendor = vendorLabel.getText();

        try {
            Connection conn = DriverManager.getConnection(DB_URL, user, pass);

            PreparedStatement stmt = null;
            stmt = conn.prepareStatement("UPDATE DBO.[SchoolKits.SupplyInventory] " +
                    "SET Supply_Name = ?, " +
                    "Description = ?, " +
                    "Quantity = ?, " +
                    "URL = ?, " +
                    "Notes = ? " +
                    "WHERE SupplyID = ?");
            stmt.setString(1, supplyName);
            stmt.setString(2, description);
            stmt.setInt(3, quantity);
            stmt.setString(4, url);
            stmt.setString(5, notes);
            stmt.setInt(6, supplyID);
            stmt.executeUpdate();

            int vendorID = 0;
            ResultSet rs4vendor = null;
            PreparedStatement stmt1 = conn.prepareStatement("SELECT VendorID FROM [SCHOOLKITS.VENDORS] WHERE VENDOR_NAME = ?");
            stmt1.setString(1, vendor);
            rs4vendor = stmt1.executeQuery();
            while (rs4vendor.next()) {
                vendorID = rs4vendor.getInt("VendorID");
            }

            PreparedStatement stmt2 = conn.prepareStatement("UPDATE DBO.[SchoolKits.SupplyItemPrice] " +
                    "SET PRICE = ? " +
                    "WHERE Supply_ID = ? AND Vendor_ID = ?");
            stmt2.setDouble(1, price);
            stmt2.setInt(2, supplyID);
            stmt2.setInt(3, vendorID);
            stmt2.executeUpdate();

            System.out.println("Record named " + supplyNameTxt.getText() + " updated into supply inventory table");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        backToViewInventoryItems(actionEvent);
    }

    /**
     * function to get image from users computer
     */
    public void updateImage(ActionEvent actionEvent) {

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        fileChooser = new FileChooser();
        String userDirectoryString = System.getProperty("user.home") + "\\Pictures";
        File userDirectory = new File(userDirectoryString);

        if (!userDirectory.canRead()) { userDirectory = new File ("c:/"); }
        fileChooser.setInitialDirectory(userDirectory);
        this.file = fileChooser.showOpenDialog(stage);
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imageView.setImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Connection conn = DriverManager.getConnection(DB_URL, user, pass);

            PreparedStatement stmt = null;
            stmt = conn.prepareStatement("UPDATE DBO.[SchoolKits.SupplyInventory] " +
                    "SET Image = ? " +
                    "WHERE SupplyID = ?");
            dbImage = new FileInputStream(file);
            stmt.setBinaryStream(1, dbImage, file.length());
            stmt.setInt(2, Integer.parseInt(supplyIdLabel.getText()));
            stmt.executeUpdate();

            System.out.println("Image for record " + supplyNameTxt.getText() + " updated successfully into supply inventory table");

        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void backToViewInventoryItems(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("viewInventoryItems.fxml"));

        Parent GUI = loader.load();
        Scene scene = new Scene(GUI);

        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }
}
