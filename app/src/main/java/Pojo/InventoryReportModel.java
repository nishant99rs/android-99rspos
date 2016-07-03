package Pojo;


public class InventoryReportModel {

    String Prod_Id;
    String Prod_Nm;
    String Batch;
    String Expiry;
    String Quantity;
    String Days_Left;

    public String getDays_Left() {
        return Days_Left;
    }

    public void setDays_Left(String days_Left) {
        Days_Left = days_Left;
    }

    public String getProd_Id() {
        return Prod_Id;
    }

    public void setProd_Id(String prod_Id) {
        Prod_Id = prod_Id;
    }

    public String getProd_Nm() {
        return Prod_Nm;
    }

    public void setProd_Nm(String prod_Nm) {
        Prod_Nm = prod_Nm;
    }

    public String getBatch() {
        return Batch;
    }

    public void setBatch(String batch) {
        Batch = batch;
    }

    public String getExpiry() {
        return Expiry;
    }

    public void setExpiry(String expiry) {
        Expiry = expiry;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    @Override
    public String toString() {
        return Prod_Id ;
    }
}