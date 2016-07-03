package Pojo;



public class ReportDistributorModel
{


    String Dstr_Nm;
    String Active;

    public ReportDistributorModel() {
    }

    public String getDstr_Nm()

    {
        return Dstr_Nm;
    }

    public void setDstr_Nm(String dstr_Nm)

    {
        Dstr_Nm = dstr_Nm;
    }

    public String getActive()

    {
        return Active;
    }

    public void setActive(String active)

    {
        Active = active;
    }

   @Override
    public String toString() {
        return Dstr_Nm;
    }


}

