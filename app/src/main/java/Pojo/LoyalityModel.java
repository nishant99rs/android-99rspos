package Pojo;

/**
 * Created by rspl-nishant on 12/3/16.
 */
public class LoyalityModel {
    String Cust_id;
    String Mobile_No;
    String Name;
    Float Points_Earned;
    Float Points_Redeemed;
    Float Points_Available;

    public String getCust_id() {
        return Cust_id;
    }

    public void setCust_id(String cust_id) {
        Cust_id = cust_id;
    }

    public String getMobile_No() {
        return Mobile_No;
    }

    public void setMobile_No(String mobile_No) {
        Mobile_No = mobile_No;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Float getPoints_Earned() {
        return Points_Earned;
    }

    public void setPoints_Earned(Float points_Earned) {
        Points_Earned = points_Earned;
    }

    public Float getPoints_Redeemed() {
        return Points_Redeemed;
    }

    public void setPoints_Redeemed(Float points_Redeemed) {
        Points_Redeemed = points_Redeemed;
    }

    public Float getPoints_Available() {
        return Points_Available;
    }

    public void setPoints_Available(Float points_Available) {
        Points_Available = points_Available;
    }
}
