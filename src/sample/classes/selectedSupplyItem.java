package sample.classes;

public class selectedSupplyItem extends supplyItem {

    int supplyID;
    String vendor;
    float price;
    float totalValue;

    public selectedSupplyItem(){}
    public selectedSupplyItem(String name, int qty, String category,  String brand,int supplyID, String vendor, float price, float totalValue){
        super(name, qty, category, brand);
        this.supplyID=supplyID;
        this.vendor=vendor;
        this.price=price;
        this.totalValue=totalValue;
    }

    public int getSupplyID() {
        return supplyID;
    }

    public void setSupplyID(int supplyID) {
        this.supplyID = supplyID;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        vendor = vendor;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(float totalValue) {
        this.totalValue = totalValue;
    }
}
