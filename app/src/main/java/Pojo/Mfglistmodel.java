package Pojo;

/**
 * Created by Rahul on 5/5/2016.
 */
public class Mfglistmodel {


    public String getMfgname() {
        return mfgname;
    }

    public void setMfgname(String mfgname) {
        this.mfgname = mfgname;
    }

    public String getTargetamount() {
        return targetamount;
    }

    public void setTargetamount(String targetamount) {
        this.targetamount = targetamount;
    }

    public String getBTLdesc() {
        return BTLdesc;
    }

    public void setBTLdesc(String BTLdesc) {
        this.BTLdesc = BTLdesc;
    }

    public String getTargetvalue() {
        return targetvalue;
    }

    public void setTargetvalue(String targetvalue) {
        this.targetvalue = targetvalue;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    String mfgname;
    String targetamount;
    String BTLdesc;
    String targetvalue;
    String startdate;
    String enddate;

}
