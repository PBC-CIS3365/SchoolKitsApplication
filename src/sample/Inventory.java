package sample;

import javafx.beans.property.SimpleStringProperty;

public class Inventory {
    private SimpleStringProperty itemName;
    private SimpleStringProperty itemDescription;
    private SimpleStringProperty itemBrand;

    public Inventory(){
        this.itemName = new SimpleStringProperty();
        this.itemDescription = new SimpleStringProperty();
        this.itemBrand = new SimpleStringProperty();

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
