package Pojo;

/**
 * Created by rspl-rahul on 10/3/16.
 */
public class VendorModel {

    String VendorName;
    String InvoiceNo;

    public VendorModel(){}
    public VendorModel(String vendorName) {
        this.VendorName = vendorName;
    }

    public String getVendorName() {
        return VendorName;
    }

    public void setVendorName(String vendorName) {
        VendorName = vendorName;
    }


    public String getInvoiceNo() {
        return InvoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        InvoiceNo = invoiceNo;
    }
    @Override
    public String toString() {
        return VendorName;
    }


}
