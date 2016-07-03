package Pojo;

/**
 * Created by rspl-nishant on 21/5/16.
 */
public class PurchaseInvoiceDropDownModel {
    String PurchaseOrderNo;
    String LastUpdate;
    String Flag;

    public String getFlag() {
        return Flag;
    }

    public void setFlag(String flag) {
        Flag = flag;
    }

    public String getPurchaseOrderNo() {
        return PurchaseOrderNo;
    }

    public void setPurchaseOrderNo(String purchaseOrderNo) {
        PurchaseOrderNo = purchaseOrderNo;
    }

    public String getLastUpdate() {
        return LastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        LastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return PurchaseOrderNo+"   "+LastUpdate;
    }
}