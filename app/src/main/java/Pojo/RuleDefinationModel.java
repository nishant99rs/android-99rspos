package Pojo;

/**
 * Created by rspl-nishant on 12/3/16.
 */
public class RuleDefinationModel {
    String StoreID;
    String Sno;
    String CARD_TYPE;
    Float BASEVALUE;
    Float PerTONPOINTS;

    public String getStoreID() {
        return StoreID;
    }

    public void setStoreID(String storeID) {
        StoreID = storeID;
    }

    public String getSno() {
        return Sno;
    }

    public void setSno(String sno) {
        Sno = sno;
    }

    public String getCARD_TYPE() {
        return CARD_TYPE;
    }

    public void setCARD_TYPE(String CARD_TYPE) {
        this.CARD_TYPE = CARD_TYPE;
    }

    public Float getBASEVALUE() {
        return BASEVALUE;
    }

    public void setBASEVALUE(Float BASEVALUE) {
        this.BASEVALUE = BASEVALUE;
    }

    public Float getPerTONPOINTS() {
        return PerTONPOINTS;
    }

    public void setPerTONPOINTS(Float perTONPOINTS) {
        PerTONPOINTS = perTONPOINTS;
    }



}
