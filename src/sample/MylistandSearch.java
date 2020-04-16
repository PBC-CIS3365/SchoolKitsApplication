package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.scene.control.inputmap.InputMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.awt.event.ActionEvent;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.*;
import java.util.function.Predicate;

import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.classes.Cookies;
import sample.classes.selectedSupplyItem;
import sample.classes.selectedSupplyItem;

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
    public TableColumn<Inventory, Integer> suppyId_col;

    public TextField search_text;

    final ObservableList<Inventory> inList = FXCollections.observableArrayList();
    public JFXButton addToLost_button;
    public Label noList_label;

            ////new new new
    public JFXComboBox quantity_combo;
    public Text info2_field;
    public JFXButton refresh_button1;

    public ImageView image_view;

    String accountID;
    //get account ID from cookies or something, will take accountID # from tables temporarily
    String itemName;
    String itemDes;
    String itemB;
    int supplyId;
    int supplyId_cookie;

    int quantity = 0;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        quantity_combo.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);






        itemName_col.setCellValueFactory(cellData -> cellData.getValue().itemNameProperty());
        itemDes_col.setCellValueFactory(cellData -> cellData.getValue().itemDescriptionProperty());
        itemBrand_col.setCellValueFactory(cellData -> cellData.getValue().itemBrandProperty());
        suppyId_col.setCellValueFactory(cellData -> cellData.getValue().supplyIDProperty().asObject());

    /*
        try{
            ObservableList<Inventory> inList = InventoryDOA.getAllRecords();
            populateTable(inList);

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

                populateTable(sd);

            });



            System.out.println("ok");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }



*/
    }


    private void populateTable(ObservableList<Inventory> inList) {
        item_table.setItems(inList);
    }


    public void list_action(MouseEvent mouseEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("listView.fxml"));
        Parent GUI = loader.load();
        Scene scene = new Scene(GUI);
        Stage window = (Stage)((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
      /* if(one_pane.isVisible() && !(two_pane.isVisible())){
           System.out.println("good");
       }else{
           one_pane.setVisible(true);
           two_pane.setVisible(false);
       }*/

    }

    public void searchAction(MouseEvent mouseEvent) {
        if(two_pane.isVisible() && !(one_pane.isVisible())){
            System.out.println("good2");
        }else{
            one_pane.setVisible(false);
            two_pane.setVisible(true);
        }

    }

    public void addToListAction(MouseEvent mouseEvent) throws SQLException {
        final String DB_URL = "jdbc:sqlserver://COT-CIS3365-09;database=SKDB;";
        final String user = "sa";
        final String pass = "549657Ll";
        Connection con = DriverManager.getConnection(DB_URL, user, pass);
        //
        Inventory in = (Inventory) item_table.getSelectionModel().getSelectedItem();
        if(in == null){
            //noList_label.setVisible(true);
            info2_field.setText("Nothing is Selected! \n Please select a Item");
        }else{

            if(quantity_combo.getValue() == null){
                info2_field.setText("Please Select Quantity!");
            }else{
                quantity = (Integer) quantity_combo.getValue();
                info2_field.setText("");
                itemName = in.getItemName();
                itemDes = in.getItemDescription();
                itemB = in.getItemBrand();
                supplyId = in.getSupplyID();




                System.out.println(itemName + " " + itemDes + " " + itemB + " "+ supplyId);
                System.out.println(supplyId_cookie);
                int lis = 25;

                try{
                    int accountID = Cookies.getAccountId();

                    String sql3 = "SELECT List_ID from [Teacher.SupplyList] left join [Teacher.Accounts] on [Teacher.SupplyList].Account_ID=[Teacher.Accounts].AccountID where AccountID ="+ accountID;
                    Statement stmt3;
                    stmt3 = con.createStatement();
                    ResultSet rs = stmt3.executeQuery(sql3);
                    while (rs.next()){
                        lis = rs.getInt(1);


                    }


                    if(supplyId_cookie == supplyId){
                        //String sql2 = "UPDATE [Teacher.SupplyListItems] SET Quantity=? WHERE Supply_ID=?";
                        //String sql2 = "UPDATE [Teacher.SupplyListItems] SET Quantity=? WHERE Supply_ID=" + supplyId;
                        String sql2 = "UPDATE [Teacher.SupplyListItems] SET Quantity=? WHERE Supply_ID=" + supplyId +" AND List_ID=" + lis;
                        //sql2 += " AND List_ID=" + lis;
                        PreparedStatement stmt2 = con.prepareStatement(sql2);
                        stmt2.setInt(1, quantity);
                        //stmt2.setInt(2,supplyId);
                        stmt2.executeUpdate();
                        System.out.println("item Updated");

                    }else{
                        String sql = "INSERT INTO [Teacher.SupplyListItems](List_ID, Supply_ID, Quantity) VALUES(?,?,?)";


                        PreparedStatement stmt = con.prepareStatement(sql);


                        stmt.setInt(1,lis);
                        stmt.setInt(2,supplyId);
                        stmt.setInt(3, quantity);
                        supplyId_cookie = supplyId;
                        stmt.executeUpdate();
                        info2_field.setText("Item Added!");
                    }





                }catch (SQLException e){
                    e.printStackTrace();
                }









                item_table.getSelectionModel().select(null);
                quantity_combo.valueProperty().setValue(null);

            }


        }
        /*if(quantity_combo.getValue()==null){
            info2_field.setText("Please Select Quantity!");
        }else {
            quantity_combo.valueProperty().setValue(null);
        }*/





    }

    public void handleRefresh(MouseEvent mouseEvent) {
        try{
            ObservableList<Inventory> inList = InventoryDOA.getAllRecords();
            populateTable(inList);

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

                populateTable(sd);

            });



            System.out.println("ok");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }




    }



}
