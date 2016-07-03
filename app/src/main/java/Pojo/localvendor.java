package Pojo;

/**
 * Created by rspl-nishant on 10/3/16.
 */
public class localvendor {

    String localvendorname;
    String localvendorcontactname;
    String localvendoraddress;
    String localvendorcity;
    String localvendorcountry;
    String localvendorzip;
    String localvendortele;
    String localvendormobile;
    String localvendorinventory;
    String localvendorid;
    String localactive;



    public localvendor(){}

    public localvendor(String localvendorname, String localvendorcontactname, String localvendoraddress, String localvendorcity, String localvendorcountry, String localvendorzip, String localvendortele, String localvendormobile, String localvendorinventory, String localvendorid,String localactive) {
        this.localvendorname = localvendorname;
        this.localvendorcontactname = localvendorcontactname;
        this.localvendoraddress = localvendoraddress;
        this.localvendorcity = localvendorcity;
        this.localvendorcountry = localvendorcountry;
        this.localvendorzip = localvendorzip;
        this.localvendortele = localvendortele;
        this.localvendormobile = localvendormobile;
        this.localvendorinventory = localvendorinventory;
        this.localvendorid = localvendorid;
        this.localactive = localactive;
    }







    public String getLocalvendorid() {
        return localvendorid;
    }

    public void setLocalvendorid(String localvendorid) {
        this.localvendorid = localvendorid;
    }



    public String getLocalvendorname() {
        return localvendorname;
    }

    public void setLocalvendorname(String localvendorname) {
        this.localvendorname = localvendorname;
    }

    public String getLocalvendorcontactname() {
        return localvendorcontactname;
    }

    public void setLocalvendorcontactname(String localvendorcontactname) {
        this.localvendorcontactname = localvendorcontactname;
    }

    public String getLocalvendoraddress() {
        return localvendoraddress;
    }

    public void setLocalvendoraddress(String localvendoraddress) {
        this.localvendoraddress = localvendoraddress;
    }

    public String getLocalvendorcity() {
        return localvendorcity;
    }

    public void setLocalvendorcity(String localvendorcity) {
        this.localvendorcity = localvendorcity;
    }

    public String getLocalvendorcountry() {
        return localvendorcountry;
    }

    public void setLocalvendorcountry(String localvendorcountry) {
        this.localvendorcountry = localvendorcountry;
    }

    public String getLocalvendorzip() {
        return localvendorzip;
    }

    public void setLocalvendorzip(String localvendorzip) {
        this.localvendorzip = localvendorzip;
    }

    public String getLocalvendortele() {
        return localvendortele;
    }

    public void setLocalvendortele(String localvendortele) {
        this.localvendortele = localvendortele;
    }

    public String getLocalvendormobile() {
        return localvendormobile;
    }

    public void setLocalvendormobile(String localvendormobile) {
        this.localvendormobile = localvendormobile;
    }

    public String getLocalvendorinventory() {
        return localvendorinventory;
    }

    public void setLocalvendorinventory(String localvendorinventory) {
        this.localvendorinventory = localvendorinventory;
    }

    public String getLocalactive() {
        return localactive;
    }

    public void setLocalactive(String localactive) {
        this.localactive = localactive;
    }
    @Override
    public String toString() {
        return localvendorname;
    }
}
