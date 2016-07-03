package Pojo;

import android.util.Log;

/**
 * Created by JIMMY on 26-03-2016.
 */
public class Inventoryproductmodelwithpo
{
    String ProductName;
    String ProductId;
    String mrp;
    String Sprice;
    String pprice;
    String measure;
    String expdate;
    String batchno;
    int productQuantity;
    float Total;
    float GrandTotal;

    public float getGrandTotal() {
        return GrandTotal;
    }
    public void setGrandTotal(float grandTotal) {
        GrandTotal = grandTotal;
    }

    public float getTotal() {
        return Total;
    }
    public void setTotal(float total) {
        Total=total;
        Total = productQuantity* Float.parseFloat(mrp);
    }

    public float getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;

        try {
            Total = productQuantity * Float.parseFloat(mrp);
        } catch( Exception e) {

        }

        Log.i("&&&&&&&&&", "set productQuantity to " + productQuantity + " for " + ProductName);
    }


    public String getMrp() {
        return mrp;
    }

    public void setMrp(String Mrp) {

        mrp=Mrp;
        try {
            Total = productQuantity * Float.parseFloat(mrp);
        } catch (Exception e) {
        }

    }

    public String getSprice() {
        return Sprice;
    }

    public void setSprice(String sprice) {
        Sprice = sprice;
    }

    public String getPprice() {
        return pprice;
    }

    public void setPprice(String pprice) {
        this.pprice = pprice;
    }

    public String getBatchno() {
        return batchno;
    }

    public void setBatchno(String batchno) {

        this.batchno = batchno;
    }

    public String getExpdate() {
        return expdate;
    }

    public void setExpdate(String expdate) {
        this.expdate = expdate;


    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }



    public Inventoryproductmodelwithpo() {
        Log.i("&&&&&&&&", "Constructor of PurchaseProductModelwithpo called ");
        this.productQuantity = 1;


    }

    @Override
    public String toString() {
        return "Name:" + ProductName + " , Quantity:" + productQuantity   ;
    }
}
