package Pojo;

/**
 * Created by Rahul on 3/25/2016.
 */
public class Inventorymodel {



    @Override
    public String toString() {
        return VendorName;
    }

    String VendorName;


    public Inventorymodel (String vendorName) {
        this.VendorName = vendorName;
    }

    public String getVendorName() {
        return VendorName;
    }

    public void setVendorName(String vendorName) {
        VendorName = vendorName;
    }


}
