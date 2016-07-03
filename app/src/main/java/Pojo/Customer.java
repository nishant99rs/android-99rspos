package Pojo;

/**
 * Created by w7 on 1/26/2016.
 */
public class Customer {


    String customermobileno;
    String customername;
    String customeremail;


    String customercredit;



    public Customer(String customermobileno, String customername,String customeremail,String customercredit) {
        this.customermobileno = customermobileno;
        this.customername = customername;
        this.customeremail=customeremail;
        this.customercredit = customercredit;

    }

    public Customer() {

    }

    public String getCustomermobileno() {
        return customermobileno;
    }

    public void setCustomermobileno(String customermobileno) {
        this.customermobileno = customermobileno;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }


    public String getCustomeremail() {
        return customeremail;
    }

    public void setCustomeremail(String customeremail) {
        this.customeremail = customeremail;
    }
    public String getCustomercredit() {
        return customercredit;
    }

    public void setCustomercredit(String customercredit) {
        this.customercredit = customercredit;
    }

    @Override
    public String toString() {
        return customername;

    }

}
