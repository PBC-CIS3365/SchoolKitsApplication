package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Inventory {
    private SimpleStringProperty itemName;
    private SimpleStringProperty itemDescription;
    private SimpleStringProperty itemBrand;
    private SimpleIntegerProperty supplyID;



    public Inventory(){
        this.itemName = new SimpleStringProperty();
        this.itemDescription = new SimpleStringProperty();
        this.itemBrand = new SimpleStringProperty();
        this.supplyID = new SimpleIntegerProperty();

    }
    //SupplyID
    public int getSupplyID() {
        return supplyID.get();
    }

    public SimpleIntegerProperty supplyIDProperty() {
        return supplyID;
    }

    public void setSupplyID(int supplyID) {
        this.supplyID.set(supplyID);
    }


    /////Name
    public String getItemName() {
        return itemName.get();
    }

    public SimpleStringProperty itemNameProperty() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName.set(itemName);
    }

    /////////Description

    public String getItemDescription() {
        return itemDescription.get();
    }

    public SimpleStringProperty itemDescriptionProperty() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription.set(itemDescription);
    }

    //////Brand

    public String getItemBrand() {
        return itemBrand.get();
    }

    public SimpleStringProperty itemBrandProperty() {
        return itemBrand;
    }

    public void setItemBrand(String itemBrand) {
        this.itemBrand.set(itemBrand);
    }

}
