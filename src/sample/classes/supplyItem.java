package sample.classes;

import javafx.scene.image.Image;

public class supplyItem {
    public String name;
    public String description;
    public int qty;
    public String category;
    public String brand;
    public Image image;
    public String url;
    public String notes;

    public supplyItem() {}

    public supplyItem(String name, String description, int qty, String category, String brand, Image image, String url, String notes) {
        this.name = name;
        this.description = description;
        this.qty = qty;
        this.category = category;
        this.brand = brand;
        this.image = image;
        this.url = url;
        this.notes = notes;
    }

    public supplyItem(String name, String description, int qty, String category, String brand, String url, String notes) {
        this.name = name;
        this.description = description;
        this.qty = qty;
        this.category = category;
        this.brand = brand;
        this.url = url;
        this.notes = notes;
    }

    /**
     * Shortened constructor for selectedSupplyItem class
     * @param name
     * @param qty
     * @param category
     * @param brand
     */

    public supplyItem(String name, int qty, String category, String brand) {
        this.name= name;
        this.qty=qty;
        this.category=category;
        this.brand=brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}
