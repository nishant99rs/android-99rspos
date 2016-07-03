package Pojo;

public class ReportVendorReturnModel {

    String VendrNm;
    String VendrId;
    String ProdctNm;
    String BatchNo;
    String ExpDate;
    String PPrice;
    String Qty;
    String Reason;
    String Uom;
    String Total;

    public String getVendrNm() {
        return VendrNm;
    }

    public void setVendrNm(String vendrNm) {
        VendrNm = vendrNm;
    }

    public String getVendrId() {
        return VendrId;
    }

    public void setVendrId(String vendrId) {
        VendrId = vendrId;
    }

    public String getProdctNm() {
        return ProdctNm;
    }

    public void setProdctNm(String prodctNm) {
        ProdctNm = prodctNm;
    }

    public String getBatchNo() {
        return BatchNo;
    }

    public void setBatchNo(String batchNo) {
        BatchNo = batchNo;
    }

    public String getExpDate() {
        return ExpDate;
    }

    public void setExpDate(String expDate) {
        ExpDate = expDate;
    }

    public String getPPrice() {
        return PPrice;
    }

    public void setPPrice(String PPrice) {
        this.PPrice = PPrice;
    }

    public String getQty() {
        return Qty;
    }

    public void setQty(String qty) {
        Qty = qty;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public String getUom() {
        return Uom;
    }

    public void setUom(String uom) {
        Uom = uom;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    @Override
    public String toString() {
        return VendrId;
    }
}
