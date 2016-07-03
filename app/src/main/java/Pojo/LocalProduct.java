package Pojo;

/**
 * Created by w7 on 1/30/2016.
 */
public class LocalProduct {

    String Storeid;
    String Productid;
    String Productname;
    String Barcode;
    String MRP;
    String SellingPrice;
    String PurchasePrice;
    String Taxid;
    String Brand;
    String PackUnit1;
    String Pharmarel;
    String Batch;
    String Active;


    String Margin;

    public LocalProduct(){}
    public LocalProduct(String storeid, String productid, String productname, String barcode, String MRP, String sellingPrice, String purchasePrice, String taxid, String brand, String packUnit1, String pharmarel, String batch,String active) {
        Storeid = storeid;
        Productid = productid;
        Productname = productname;
        Barcode = barcode;
        this.MRP = MRP;
        SellingPrice = sellingPrice;
        PurchasePrice = purchasePrice;
        Taxid = taxid;
        Brand = brand;
        PackUnit1 = packUnit1;
        Pharmarel = pharmarel;
        Batch = batch;
        Active = active;
    }

    public String getStoreid() {
        return Storeid;
    }

    public void setStoreid(String storeid) {
        Storeid = storeid;
    }
    public String getMargin() {
        return Margin;
    }

    public void setMargin(String margin) {
        Margin = margin;
    }



    public String getProductid() {
        return Productid;
    }

    public void setProductid(String productid) {
        Productid = productid;
    }

    public String getProductname() {
        return Productname;
    }

    public void setProductname(String productname) {
        Productname = productname;
    }

    public String getBarcode() {
        return Barcode;
    }

    public void setBarcode(String barcode) {
        Barcode = barcode;
    }

    public String getMRP() {
        return MRP;
    }

    public void setMRP(String MRP) {
        this.MRP = MRP;
    }

    public String getSellingPrice() {
        return SellingPrice;
    }

    public void setSellingPrice(String sellingPrice) {
        SellingPrice = sellingPrice;
    }

    public String getPurchasePrice() {
        return PurchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
        PurchasePrice = purchasePrice;
    }

    public String getTaxid() {
        return Taxid;
    }

    public void setTaxid(String taxid) {
        Taxid = taxid;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getPackUnit1() {
        return PackUnit1;
    }

    public void setPackUnit1(String packUnit1) {
        PackUnit1 = packUnit1;
    }

    public String getPharmarel() {
        return Pharmarel;
    }

    public void setPharmarel(String pharmarel) {
        Pharmarel = pharmarel;
    }

    public String getBatch() {
        return Batch;
    }

    public void setBatch(String batch) {
        Batch = batch;
    }
    public String getActive() {
        return Active;
    }

    public void setActive(String active) {
        Active = active;
    }


    @Override
    public String toString() {
        return Productname;
    }
}
