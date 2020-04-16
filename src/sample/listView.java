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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class listView implements Initializable {
    public TableView listView_table;
    public TableColumn<list, Integer> list_num;
    public TableColumn<list, String> item_name;
    public TableColumn<list, String> item_des;
    public TableColumn<list, Integer> quan;
    public Button b1;
    public JFXButton back_button;


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
}
