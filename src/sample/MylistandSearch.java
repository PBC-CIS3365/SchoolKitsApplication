package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.scene.control.inputmap.InputMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import java.util.function.Predicate;

import javafx.scene.control.*;

import javax.sound.midi.Soundbank;


public class MylistandSearch implements Initializable {


    public JFXButton b1;
    public JFXButton b2;
    public AnchorPane one_pane;
    public AnchorPane two_pane;

    public TableView item_table;
    public TableColumn<Inventory, String> itemName_col;
    public TableColumn<Inventory, String> itemDes_col;
    public TableColumn<Inventory, String> itemBrand_col;
    public TextField search_text;

    final ObservableList<Inventory> inList = FXCollections.observableArrayList();
    public JFXButton addToLost_button;
    public Label noList_label;

    String itemName;
    String itemDes;
    String itemB;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {






        itemName_col.setCellValueFactory(cellData -> cellData.getValue().itemNameProperty());
        itemDes_col.setCellValueFactory(cellData -> cellData.getValue().itemDescriptionProperty());
        itemBrand_col.setCellValueFactory(cellData -> cellData.getValue().itemBrandProperty());


        try{
            ObservableList<Inventory> inList = InventoryDOA.getAllRecords();
            populateTable(inList);
            //
            FilteredList<Inventory> fd = new FilteredList<>(inList, e -> true);
            search_text.setOnKeyReleased(e -> {
                search_text.textProperty().addListener((observableValue, oldValue, newValue) -> {
                    fd.setPredicate((Predicate<? super Inventory>) inventory->{
                        if(newValue == null || newValue.isEmpty()){
                            return true;
                        }
                        String lowerCaseFilter = newValue.toLowerCase();
                        if(inventory.getItemName().toLowerCase().contains(lowerCaseFilter)){
                            return true;
                        }

                        return false;
                    });
                });
                SortedList<Inventory> sd = new SortedList<>(fd);
                sd.comparatorProperty().bind(item_table.comparatorProperty());
                //item_table.setItems(sd);
                populateTable(sd);

            });


            //populateTable(inList);
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

    public void addToListAction(MouseEvent mouseEvent) {
        Inventory in = (Inventory) item_table.getSelectionModel().getSelectedItem();
        if(in == null){
            noList_label.setVisible(true);
        }else{
            itemName = in.getItemName();
            itemDes = in.getItemDescription();
            itemB = in.getItemBrand();
            System.out.println(itemName + " " + itemDes + " " + itemB);

        }
        noList_label.setVisible(false);



    }
}
