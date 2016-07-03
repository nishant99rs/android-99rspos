package Pojo;


public class SalesReturnReportModel {

    String TransId;
    String GrnTotl;

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    String Reason;

    public String getProdNm() {
        return ProdNm;
    }

    public void setProdNm(String prodNm) {
        ProdNm = prodNm;
    }

    public String getBatch() {
        return Batch;
    }

    public void setBatch(String batch) {
        Batch = batch;
    }

    public String getExp() {
        return Exp;
    }

    public void setExp(String exp) {
        Exp = exp;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getQty() {
        return Qty;
    }

    public void setQty(String qty) {
        Qty = qty;
    }

    public String getMrp() {
        return Mrp;
    }

    public void setMrp(String mrp) {
        Mrp = mrp;
    }

    String ProdNm;
    String Batch;
    String Exp;
    String Price;
    String Qty;
    String Mrp;

    public String getUOM() {
        return UOM;
    }

    public void setUOM(String UOM) {
        this.UOM = UOM;
    }

    String UOM;

    public String getTransId() {
        return TransId;
    }

    public void setTransId(String transId) {
        TransId = transId;
    }

    public String getGrnTotl() {
        return GrnTotl;
    }

    public void setGrnTotl(String grnTotl) {
        GrnTotl = grnTotl;
    }

    @Override
    public String toString() {
        return  GrnTotl+" "+Reason+" "+TransId;
    }



}
