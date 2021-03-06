package sample;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.classes.selectedSupplyItem;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class viewInventoryController implements Initializable {

    public ImageView imageView;
    public JFXButton viewItemBtn;
    public JFXButton deleteItemBtn;
    public JFXButton addItemBtn;
    public TextField searchTxt;
    public JFXButton backBtn;

    //database information
    final String DB_URL = "jdbc:sqlserver://COT-CIS3365-09;database=SKDB;";
    final String user = "sa";
    final String pass = "549657Ll";

    public TableView<selectedSupplyItem> inventoryTable;
    public TableColumn<selectedSupplyItem, Integer> supplyIdCol;
    public TableColumn<selectedSupplyItem, String> productNameCol;
    public TableColumn<selectedSupplyItem, Integer> qtyCol;
    public TableColumn<selectedSupplyItem, String> catCol;
    public TableColumn<selectedSupplyItem, String> brandCol;
    public TableColumn<selectedSupplyItem, String> vendorCol;
    public TableColumn<selectedSupplyItem, Float> priceCol;
    public TableColumn<selectedSupplyItem, Float> totalValueCol;
    public ObservableList<selectedSupplyItem> supplyInventoryList = FXCollections.observableArrayList();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        supplyIdCol.setCellValueFactory(new PropertyValueFactory<>("supplyID"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("qty"));
        catCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        brandCol.setCellValueFactory(new PropertyValueFactory<>("brand"));
        vendorCol.setCellValueFactory(new PropertyValueFactory<>("vendor"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        totalValueCol.setCellValueFactory(new PropertyValueFactory<>("totalValue"));

        inventoryTable.setItems(supplyInventoryList);

        ResultSet rs = null;
        String sql = "SELECT \n" +
                "[SchoolKits.SupplyInventory].SupplyID,\n" +
                "[SchoolKits.SupplyInventory].Supply_Name, \n" +
                "[SchoolKits.SupplyInventory].Quantity,\n" +
                "[SchoolKits.SupplyInventory].Category, \n" +
                "[SchoolKits.SupplyInventory].Brand,\n" +
                "[SchoolKits.Vendors].Vendor_Name, \n" +
                "[SchoolKits.SupplyItemPrice].Price,  \n" +
                "[SchoolKits.SupplyInventory].Quantity*[SchoolKits.SupplyItemPrice].Price AS [TOTAL_VALUE]\n" +
                "FROM \n" +
                "[SchoolKits.SupplyInventory] \n" +
                "FULL JOIN \n" +
                "[SchoolKits.SupplyItemPrice] \n" +
                "ON [SchoolKits.SupplyInventory].SupplyID=[SchoolKits.SupplyItemPrice].Supply_ID\n" +
                "INNER JOIN\n" +
                "[SchoolKits.Vendors]\n" +
                "ON [SchoolKits.Vendors].VendorID=[SchoolKits.SupplyItemPrice].Vendor_ID";
        try {
            Connection conn = DriverManager.getConnection(DB_URL, user, pass);
            PreparedStatement statement = conn.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                supplyInventoryList.add(new selectedSupplyItem(rs.getString("Supply_Name"),rs.getInt("Quantity"),rs.getString("Category"),rs.getString("Brand"), rs.getInt("SupplyID"),rs.getString("Vendor_Name"),rs.getFloat("Price"),rs.getFloat("TOTAL_VALUE")));
                System.out.println();
            }
            rs.close();
            conn.close();
        } catch (Exception ex) {
            var msg = ex.getMessage();
            System.out.println(msg);
        }

        //Filtered list to filter through database
        FilteredList<selectedSupplyItem> filteredList =  new FilteredList<>(supplyInventoryList, b -> true);

        searchTxt.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(selectedSupplyItem -> {

                if (newValue == null||newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
            if (selectedSupplyItem.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                return true;
            } else if (selectedSupplyItem.getCategory().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                return true;
            } else if (selectedSupplyItem.getBrand().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                return true;
            } else if (selectedSupplyItem.getVendor().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
            } else if (String.valueOf(selectedSupplyItem.getSupplyID()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
            } else
                return false;
            });
        });

        SortedList<selectedSupplyItem> sortedData = new SortedList<>(filteredList);
        sortedData.comparatorProperty().bind(inventoryTable.comparatorProperty());
        inventoryTable.setItems(sortedData);
    }

    public void viewItem(ActionEvent actionEvent) throws IOException
    {
        if (inventoryTable.getSelectionModel().getSelectedItem() == null) {
            System.out.println("Nothing selected");
        } else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("viewItemDetail.fxml"));
            Parent GUI = loader.load();
            Scene scene = new Scene(GUI);
            viewItemController controller = loader.getController();
            controller.passData(inventoryTable.getSelectionModel().getSelectedItem());
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
    }


    public void deleteItem(ActionEvent actionEvent) {
        selectedSupplyItem selectedSupplyItem = inventoryTable.getSelectionModel().getSelectedItem();
        int id = selectedSupplyItem.getSupplyID();

        Alert alert = new Alert((Alert.AlertType.CONFIRMATION));
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete this record?");
        Optional <ButtonType> action = alert.showAndWait();

        if(action.get()==ButtonType.OK) {
            try {
                Connection conn = DriverManager.getConnection(DB_URL, user, pass);

                ResultSet rs = null;
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM [SCHOOLKITS.SUPPLYINVENTORY] WHERE SupplyID = ?");
                stmt.setInt(1, id);
                stmt. executeUpdate();

                System.out.println("Record named " + selectedSupplyItem.getName() + " deleted from supply inventory table");

            } catch (SQLException e) {
                e.printStackTrace();
            }
            supplyInventoryList.remove(inventoryTable.getSelectionModel().getSelectedItem());
            inventoryTable.refresh();
            imageView.setImage(null);
        }
    }

    public void addItem(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("createInventoryItem.fxml"));

        Parent GUI = loader.load();
        Scene scene = new Scene(GUI);

        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    public void selectItem(MouseEvent mouseEvent) throws SQLException {

        if (inventoryTable.getSelectionModel().getSelectedItem() == null) {
            System.out.println("Nothing selected");
        } else {
            selectedSupplyItem selectedSupplyItem = inventoryTable.getSelectionModel().getSelectedItem();
            int id = selectedSupplyItem.getSupplyID();

            try {
                ResultSet rs = null;
                Connection conn = DriverManager.getConnection(DB_URL, user, pass);
                PreparedStatement statement = conn.prepareStatement("SELECT * FROM [SCHOOLKITS.SUPPLYINVENTORY] WHERE SUPPLYID = ?");
                statement.setInt(1, id);
                rs = statement.executeQuery();

                while (rs.next()) {
                    InputStream is = rs.getBinaryStream("Image");
                    OutputStream os = new FileOutputStream(new File("photo.jpg"));
                    byte[] content = new byte[1024];
                    int size = 0;
                    while ((size = is.read(content)) != -1) {
                        os.write(content, 0, size);
                    }
                    os.close();
                    is.close();

                    Image image = new Image("file:photo.jpg", imageView.getFitWidth(), imageView.getFitHeight(), true, true);
                    imageView.setImage(image);
                }
                rs.close();
                conn.close();
            } catch (Exception ex) {
                var msg = ex.getMessage();
                System.out.println(msg);
            }
        }
    }

    public void backToAdminHome(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("School_Kits_Supply_Portal.fxml"));

        Parent GUI = loader.load();
        Scene scene = new Scene(GUI);

        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }
}
