package Pojo;

/**
 * Created by rspl-nishant on 28/3/16.
 */
public class Store {

    String StoreId;
    String StoreName;
    String StoreTele;
    String Storezip;
    String Storecontactname;
    String StoreAddress;
    String Storecity;
    String Storecountry;
    String Storeemail;
    public Store(){}

    public Store(String storeId, String storeName, String storeTele, String storezip, String storecontactname, String storeAddress, String storecity, String storecountry, String storeemail) {
        StoreId = storeId;
        StoreName = storeName;
        StoreTele = storeTele;
        Storezip = storezip;
        Storecontactname = storecontactname;
        StoreAddress = storeAddress;
        Storecity = storecity;
        Storecountry = storecountry;
        Storeemail = storeemail;
    }


    public String getStoreId() {
        return StoreId;
    }

    public void setStoreId(String storeId) {
        StoreId = storeId;
    }

    public String getStoreName() {
        return StoreName;
    }

    public void setStoreName(String storeName) {
        StoreName = storeName;
    }

    public String getStoreTele() {
        return StoreTele;
    }

    public void setStoreTele(String storeTele) {
        StoreTele = storeTele;
    }

    public String getStorezip() {
        return Storezip;
    }

    public void setStorezip(String storezip) {
        Storezip = storezip;
    }

    public String getStorecontactname() {
        return Storecontactname;
    }

    public void setStorecontactname(String storecontactname) {
        Storecontactname = storecontactname;
    }

    public String getStoreAddress() {
        return StoreAddress;
    }

    public void setStoreAddress(String storeAddress) {
        StoreAddress = storeAddress;
    }

    public String getStorecity() {
        return Storecity;
    }

    public void setStorecity(String storecity) {
        Storecity = storecity;
    }

    public String getStorecountry() {
        return Storecountry;
    }

    public void setStorecountry(String storecountry) {
        Storecountry = storecountry;
    }

    public String getStoreemail() {
        return Storeemail;
    }

    public void setStoreemail(String storeemail) {
        Storeemail = storeemail;
    }







}



