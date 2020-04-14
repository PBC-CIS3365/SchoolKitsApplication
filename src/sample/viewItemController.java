package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class viewItemController implements Initializable {
    public JFXTextField supplyNameTxt;
    public JFXTextField urlTxt;
    public JFXTextField notesTxt;
    public JFXTextField priceTxt;
    public JFXTextArea descTxt;
    public Label supplyIdLabel;
    public Spinner qtySpinner;
    public JFXComboBox categoryCombo;
    public JFXComboBox deptCombo;
    public JFXComboBox brandCombo;
    public Label totalValueLabel;
    public ImageView imageView;
    public JFXButton updateItemBtn;
    public JFXButton uploadImageBtn;
    public JFXButton backBtn;

    public ObservableList<String> categories = FXCollections.observableArrayList();
    public ObservableList<String> departments = FXCollections.observableArrayList();
    public ObservableList<String> brands = FXCollections.observableArrayList();
    public ObservableList<String> vendors = FXCollections.observableArrayList();
    public JFXButton backButton;
    public JFXComboBox vendorCombo;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoryCombo.setItems(categories);
        deptCombo.setItems(departments);
        brandCombo.setItems(brands);
        vendorCombo.setItems(vendors);
    }

    public void fillDepartments(MouseEvent mouseEvent) {
    }

    public void updateItem(ActionEvent actionEvent) {
    }

    public void uploadImage(ActionEvent actionEvent) {
    }

    public void backToViewInventoryItems(ActionEvent actionEvent) {
    }
}
