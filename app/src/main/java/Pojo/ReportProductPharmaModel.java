package Pojo;



public class ReportProductPharmaModel
{

    String Prod_Id;
    String Prod_Nm;
    String MRP;
    String S_Price;

    public String getP_Price() {
        return P_Price;
    }

    public void setP_Price(String p_Price) {
        P_Price = p_Price;
    }

    public String getBarCode() {
        return BarCode;
    }

    public void setBarCode(String barCode) {
        BarCode = barCode;
    }

    public String getMargin() {
        return Margin;
    }

    public void setMargin(String margin) {
        Margin = margin;
    }

    String P_Price;
    String BarCode;
    String Margin;
    String Active;

    public String getProd_Id() {
        return Prod_Id;
    }

    public void setProd_Id(String prod_Id) {
        Prod_Id = prod_Id;
    }



    public ReportProductPharmaModel() {
    }

    public String getProd_Nm() {
        return Prod_Nm;
    }

    public void setProd_Nm(String prod_Nm) {
        Prod_Nm = prod_Nm;
    }

    public String getMRP() {
        return MRP;
    }

    public void setMRP(String MRP) {
        this.MRP = MRP;
    }

    public String getS_Price() {
        return S_Price;
    }

    public void setS_Price(String s_Price) {
        S_Price = s_Price;
    }

    public String getActive() {
        return Active;
    }

    public void setActive(String active) {
        Active = active;
    }
    @Override
    public String toString() {
        return Prod_Nm;
    }

}

