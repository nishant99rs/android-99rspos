package Pojo;

public class IndirectVendorPaymentModel {

    String VendorNm;
    String GrnId;
    String AmountPaid;
    String AmountRcvd;
    String AmountDue;
    String BankName;
    String ChequeNo;
    String ReasonOfPay;
    String LastUpdate;

    public String getReasonOfPay() {
        return ReasonOfPay;
    }

    public void setReasonOfPay(String reasonOfPay) {
        ReasonOfPay = reasonOfPay;
    }

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String bankName) {
        BankName = bankName;
    }

    public String getChequeNo() {
        return ChequeNo;
    }

    public void setChequeNo(String chequeNo) {
        ChequeNo = chequeNo;
    }

    public String getAmountRcvd() {
        return AmountRcvd;
    }

    public void setAmountRcvd(String amountRcvd) {
        AmountRcvd = amountRcvd;
    }

    public String getAmountDue() {
        return AmountDue;
    }

    public void setAmountDue(String amountDue) {
        AmountDue = amountDue;
    }

    public String getAmountPaid() {
        return AmountPaid;
    }

    public void setAmountPaid(String amountPaid) {
        AmountPaid = amountPaid;
    }

    public String getGrnId() {
        return GrnId;
    }

    public void setGrnId(String grnId) {
        GrnId = grnId;
    }

    public String getVendorNm() {
        return VendorNm;
    }

    public void setVendorNm(String vendorNm) {
        VendorNm = vendorNm;
    }

    public String getLastUpdate() {
        return LastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        LastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return GrnId;
    }
}
