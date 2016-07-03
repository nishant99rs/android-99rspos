package Pojo;

import android.util.Log;

/**
 * Created by rspl-nishant on 5/4/16.
 */
public class Salesreturndetail {

    String SaleTransid;
    String SaleBillno;
    String Salebatchno;
    String Salemrp;
    float Salestockqty;
    public float Saleqty;
    float Saletotal;
    float GrandTotal;
    String SaleDate;
    String Reasons;
    String SaleProdid;
    String Saleuom;
    float Salesellingprice;
    String Saleproductname;
    String Saleexpiry;
    String conversionfactorreturn;

    public String getConversionfactorreturn() {
        return conversionfactorreturn;
    }

    public void setConversionfactorreturn(String conversionfactorreturn) {
        this.conversionfactorreturn = conversionfactorreturn;
        try {
            Saletotal= (Salesellingprice / Float.parseFloat(conversionfactorreturn)) * Saleqty;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }







    public String getSaleProdid() {
        return SaleProdid;
    }

    public void setSaleProdid(String saleProdid) {
        SaleProdid = saleProdid;
    }



    public String getSaleDate() {
        return SaleDate;
    }

    public void setSaleDate(String saleDate) {
        SaleDate = saleDate;
    }



    public float getSalestockqty() {
        return Salestockqty;
    }

    public void setSalestockqty(float salestockqty) {
        Salestockqty = salestockqty;
    }

    public String getReasons() {
        return Reasons;
    }

    public void setReasons(String reasons) {
        Reasons = reasons;
    }

    public float getGrandTotal() {
        return GrandTotal;
    }

    public void setGrandTotal(float grandTotal) {
        GrandTotal = grandTotal;
    }

    public  Salesreturndetail(){

        this.Saleqty = 1;

        this.Salebatchno="";
    }

    public String getSaleTransid() {
        return SaleTransid;
    }

    public void setSaleTransid(String saleTransid) {
        SaleTransid = saleTransid;
    }

    public String getSaleBillno() {
        return SaleBillno;
    }

    public void setSaleBillno(String saleBillno) {
        SaleBillno = saleBillno;
    }

    public String getSalebatchno() {
        return Salebatchno;
    }

    public void setSalebatchno(String salebatchno) {
        Salebatchno = salebatchno;
    }

    public String getSalemrp() {
        return Salemrp;
    }

    public void setSalemrp(String salemrp) {
        Salemrp = salemrp;
    }

    public float getSaleqty() {
        return Saleqty;
    }

    public void setSaleqty(float saleqty) {

        Saleqty = saleqty;



    }

    public float getSaletotal() {
        return Saletotal;
    }

    public void setSaletotal(float saletotal)
    {
        Saletotal = saletotal;
        try {
            Saletotal= (Salesellingprice / Float.parseFloat(conversionfactorreturn)) * Saleqty;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getSaleuom() {
        return Saleuom;
    }

    public void setSaleuom(String saleuom) {
        Saleuom = saleuom;
    }

    public float getSalesellingprice() {
        return Salesellingprice;
    }

    public void setSalesellingprice(float salesellingprice) {
        Salesellingprice = salesellingprice;
        try {
            Saletotal= (Salesellingprice / Float.parseFloat(conversionfactorreturn)) * Saleqty;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getSaleproductname() {
        return Saleproductname;
    }

    public void setSaleproductname(String saleproductname) {
        Saleproductname = saleproductname;
    }

    public String getSaleexpiry() {
        return Saleexpiry;
    }

    public void setSaleexpiry(String saleexpiry) {
        Saleexpiry = saleexpiry;
    }


    @Override
    public String toString() {
        return Reasons;
    }

}