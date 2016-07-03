package Pojo;

import android.util.Log;

/**
 * Created by rspl-nishant on 10/3/16.
 */
public class Product {


    String StoreId;
    String ProductBarcode;
    String ProductName;
    String MRP;
    String  Sellingprice;
    String  Purchaseprice;
    String  Taxid;
    String  Manuf;
    String  PackingUnit1;
    String  MeasureUnit;
    String  Measure;
    String  Strength;
    String  ProductId;
    String  Internet;
    String  Internetrelevant;
    String Active;
    String Margin;



    public Product(){}

    public Product(String storeId, String productBarcode, String productName, String MRP, String sellingprice, String purchaseprice, String taxid, String manuf, String packingUnit1, String measureUnit, String measure, String strength, String productId, String internet, String internetrelevant,String active,String margin) {
        StoreId = storeId;
        ProductBarcode = productBarcode;
        ProductName = productName;
        this.MRP = MRP;
        Sellingprice = sellingprice;
        Purchaseprice = purchaseprice;
        Taxid = taxid;
        Manuf = manuf;
        PackingUnit1 = packingUnit1;
        MeasureUnit = measureUnit;
        Measure = measure;
        Strength = strength;
        ProductId = productId;
        Internet = internet;
        Internetrelevant = internetrelevant;
        Active = active;
        Margin = margin;
    }
    public String getStoreId() {
        return StoreId;
    }

    public void setStoreId(String storeId) {
        StoreId = storeId;
    }

    public String getProductBarcode() {
        return ProductBarcode;
    }

    public void setProductBarcode(String productBarcode) {
        ProductBarcode = productBarcode;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
        Log.d("ProductName :",ProductName.toString());

    }
    public String getMargin() {
        return Margin;
    }

    public void setMargin(String margin) {
        Margin = margin;
    }


    public String getMRP() {
        return MRP;
    }

    public void setMRP(String MRP) {
        this.MRP = MRP;
    }

    public String getSellingprice() {
        return Sellingprice;
    }

    public void setSellingprice(String sellingprice) {
        Sellingprice = sellingprice;
    }

    public String getPurchaseprice() {
        return Purchaseprice;
    }

    public void setPurchaseprice(String purchaseprice) {
        Purchaseprice = purchaseprice;
    }

    public String getTaxid() {
        return Taxid;
    }

    public void setTaxid(String taxid) {
        Taxid = taxid;
    }

    public String getManuf() {
        return Manuf;
    }

    public void setManuf(String manuf) {
        Manuf = manuf;
    }

    public String getPackingUnit1() {
        return PackingUnit1;
    }

    public void setPackingUnit1(String packingUnit1) {
        PackingUnit1 = packingUnit1;
    }

    public String getMeasureUnit() {
        return MeasureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        MeasureUnit = measureUnit;
    }

    public String getMeasure() {
        return Measure;
    }

    public void setMeasure(String measure) {
        Measure = measure;
    }

    public String getStrength() {
        return Strength;
    }

    public void setStrength(String strength) {
        Strength = strength;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getInternet() {
        return Internet;
    }

    public void setInternet(String internet) {
        Internet = internet;
    }

    public String getInternetrelevant() {
        return Internetrelevant;
    }

    public void setInternetrelevant(String internetrelevant) {
        Internetrelevant = internetrelevant;
    }
    public String getActive() {
        return Active;
    }

    public void setActive(String active) {
        Active = active;
    }


    @Override
    public String toString() {
        return ProductName;
    }
}
