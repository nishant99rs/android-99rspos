package Pojo;

import android.util.Log;

/**
 * Created by rspl-rahul on 10/3/16.
 */
public class PurchaseProductModel {
    String ProductName;
    String ProductId;
    float ProductPrice;
    String Uom;
    String MRP;
    String VendorName;
    int productQuantity;
    float Total;
    float GrandTotal;
    String Barcode;
    String Profit_Margin;
    String Conversionfactor;
    String TotalQty;

    public String getTotalQty() {
        return TotalQty;
    }

    public void setTotalQty(String totalQty) {
        TotalQty = totalQty;
        try {
            Total = productQuantity * ProductPrice;
            TotalQty=String.valueOf(productQuantity*Integer.parseInt(Conversionfactor));


        } catch( Exception e) {

        }
    }


    public String getIndusteryname() {
        return industeryname;
    }

    public void setIndusteryname(String industeryname) {
        this.industeryname = industeryname;
    }

    public String getConversionfactor() {
        return Conversionfactor;
    }

    public void setConversionfactor(String conversionfactor) {
        Conversionfactor = conversionfactor;
        try {

            TotalQty=String.valueOf(productQuantity*Integer.parseInt(Conversionfactor));

        } catch( Exception e) {

        }
    }

    String industeryname;

    public String getProfit_Margin() {
        return Profit_Margin;
    }

    public void setProfit_Margin(String profit_Margin) {
        Profit_Margin = profit_Margin;
    }

    public String getBarcode() {
        return Barcode;

    }

    public void setBarcode(String barcode) {
        Barcode = barcode;
    }

    public String getSellingPrice() {
        return SellingPrice;
    }

    public void setSellingPrice(String sellingPrice) {
        SellingPrice = sellingPrice;
    }

    public String getMRP() {
        return MRP;
    }

    public void setMRP(String MRP) {
        this.MRP = MRP;
    }

    String SellingPrice;

    public String getVendorName() {
        return VendorName;
    }

    public void setVendorName(String vendorName) {
        VendorName = vendorName;
    }


    public String getUom() {
        return Uom;
    }

    public void setUom(String uom) {
        Uom = uom;
    }

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
        Total = productQuantity*ProductPrice;

    }
    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
        try {
            Total = productQuantity * ProductPrice;
            TotalQty=String.valueOf(productQuantity*Integer.parseInt(Conversionfactor));

        } catch( Exception e) {

        }
    }
    //  Log.i("&&&&&&&&&", "set productQuantity to " + productQuantity + " for "  + ProductName );

    public float getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(float productPrice) {
        ProductPrice=productPrice;
        try {
            Total = productQuantity *productPrice;
            //   Total = productPrice*productQuantity;
        } catch (Exception e) {
        }
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

    public PurchaseProductModel() {
        Log.i("&&&&&&&&", "Constructor of PurchaseProductModel called " );
        this.productQuantity = 1;
        this.ProductPrice=0.0F;
    }

    @Override
    public String toString() {
        //return "Name:" + ProductName + " , Quantity:" + productQuantity   ;
        return  ProductName ;
    }
}