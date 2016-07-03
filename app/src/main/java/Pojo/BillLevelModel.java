package Pojo;

/**
 * Created by rspl-abhigyan on 21/6/16.
 */



public class BillLevelModel {

    String mBillName;
    String mBillType;
    String lastupdate;

    public String getmBillName() {
        return mBillName;
    }

    public void setmBillName(String mBillName) {
        this.mBillName = mBillName;
    }

    public String getmBillType() {
        return mBillType;
    }

    public void setmBillType(String mBillType) {
        this.mBillType = mBillType;
    }



    /*public String getmVId() {
        return mBillName;
    }

    public void setmVId(String mVId) {
        this.mBillName = mVId;
    }

    public String getmVReason() {
        return mBillType;
    }

    public void setmVReason(String mVReason) {
        this.mBillType = mVReason;
    }

    public String getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(String lastupdate) {
        this.lastupdate = lastupdate;
    }*/

    public BillLevelModel() {
        this.mBillName = mBillName;
        this.mBillType = mBillType;
        this.lastupdate = lastupdate;
    }
}
