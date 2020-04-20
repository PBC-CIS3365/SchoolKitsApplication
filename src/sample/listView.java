package sample;

import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.classes.Cookies;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class listView implements Initializable {

    final String DB_URL = "jdbc:sqlserver://COT-CIS3365-09;database=SKDB;";
    final String user = "sa";
    final String pass = "549657Ll";

    public TableView listView_table;
    public TableColumn<list, Integer> list_num;
    public TableColumn<list, String> item_name;
    public TableColumn<list, String> item_des;
    public TableColumn<list, Integer> quan;
    public Button b1;
    public JFXButton back_button;
    public ImageView image_view;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        list_num.setCellValueFactory(cellData -> cellData.getValue().listNumProperty().asObject());
        item_name.setCellValueFactory(cellData -> cellData.getValue().itemNameProperty());
        item_des.setCellValueFactory(cellData -> cellData.getValue().itemDescriptionProperty());
        quan.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());



    }
    private void populateTable(ObservableList<list> inList) {
        listView_table.setItems(inList);
    }

    public void handleClick(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        try{
            ObservableList<list> inList = listDOA.getAllRecordslist();

            populateTable(inList);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void handleBack(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MylistandSearch.fxml"));
        Parent GUI = loader.load();
        Scene scene = new Scene(GUI);
        Stage window = (Stage)((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void backToHome(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("teacherHomePage.fxml"));
        Parent GUI = loader.load();
        teacherHomeController controller = loader.getController();
        controller.passData(Cookies.getAccountId());
        Scene scene = new Scene(GUI);
        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void selectItem(MouseEvent mouseEvent) {
        if (listView_table.getSelectionModel().getSelectedItem() == null) {
            System.out.println("Nothing selected");
        } else {
            list list = (list) listView_table.getSelectionModel().getSelectedItem();
            //int id = in.getSupplyID();
            String itemName = list.getItemName();

            try {
                ResultSet rs = null;
                Connection conn = DriverManager.getConnection(DB_URL, user, pass);
                PreparedStatement statement = conn.prepareStatement("SELECT * FROM [SCHOOLKITS.SUPPLYINVENTORY] WHERE SUPPLY_NAME = ?");
                statement.setString(1, itemName);
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

                    Image image = new Image("file:photo.jpg", image_view.getFitWidth(), image_view.getFitHeight(), true, true);
                    image_view.setImage(image);
                }
                rs.close();
                conn.close();
            } catch (Exception ex) {
                var msg = ex.getMessage();
                System.out.println(msg);
            }
        }
    }
}

