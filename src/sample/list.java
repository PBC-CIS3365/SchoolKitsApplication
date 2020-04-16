package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class list {
    private SimpleIntegerProperty listNum;
    private SimpleStringProperty itemName;
    private SimpleStringProperty itemDescription;
    private SimpleIntegerProperty quantity;

    public list(){
        this.listNum = new SimpleIntegerProperty();
        this.itemName = new SimpleStringProperty();
        this.itemDescription = new SimpleStringProperty();
        this.quantity = new SimpleIntegerProperty();
    }

    public int getListNum() {
        return listNum.get();
    }

    public SimpleIntegerProperty listNumProperty() {
        return listNum;
    }

    public void setListNum(int listNum) {
        this.listNum.set(listNum);
    }

    public String getItemName() {
        return itemName.get();
    }

    public SimpleStringProperty itemNameProperty() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName.set(itemName);
    }

    public String getItemDescription() {
        return itemDescription.get();
    }

    public SimpleStringProperty itemDescriptionProperty() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription.set(itemDescription);
    }

    public int getQuantity() {
        return quantity.get();
    }

    public SimpleIntegerProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }
}
