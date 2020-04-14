package sample;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.*;
import javafx.scene.control.*;

public class MylistandSearch implements Initializable {


    public JFXButton b1;
    public JFXButton b2;
    public AnchorPane one_pane;
    public AnchorPane two_pane;

    public TableView item_table;
    public TableColumn<Inventory, String> itemName_col;
    public TableColumn<Inventory, String> itemDes_col;
    public TableColumn<Inventory, String> itemBrand_col;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        itemName_col.setCellValueFactory(cellData -> cellData.getValue().itemNameProperty());
        itemDes_col.setCellValueFactory(cellData -> cellData.getValue().itemDescriptionProperty());
        itemBrand_col.setCellValueFactory(cellData -> cellData.getValue().itemBrandProperty());

        try{
            ObservableList<Inventory> inList = InventoryDOA.getAllRecords();
            populateTable(inList);
            System.out.println("ok");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }




    }

    private void populateTable(ObservableList<Inventory> inList) {
        item_table.setItems(inList);
    }


    public void list_action(MouseEvent mouseEvent) {
       if(one_pane.isVisible() && !(two_pane.isVisible())){
           System.out.println("good");
       }else{
           one_pane.setVisible(true);
           two_pane.setVisible(false);
       }

    }

    public void searchAction(MouseEvent mouseEvent) {
        if(two_pane.isVisible() && !(one_pane.isVisible())){
            System.out.println("good2");
        }else{
            one_pane.setVisible(false);
            two_pane.setVisible(true);
        }

    }
}
