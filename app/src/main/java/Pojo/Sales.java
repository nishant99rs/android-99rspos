




        package Pojo;

/**
 * Created by shilpa on 18/3/16.
 */
public class
Sales {
    String ProductName;
    String BatchNo;
    String Expiry;
    String PPrice;
    float SPrice;
    int Quantity;
    String Bill_No;
    float Mrp;
    String Amount;
    String Uom;
    String Prodid;
    float Stockquant;
    float Total;
    float GrandTotal;
    String Trans_id;
    String holdtotal;
    float holdstock;
    String topproductName;
    String storeid;
    String ProductshortName;
    String ProductId;
    Float Conversionfacter;

    public Float getConversionfacter() {
        return Conversionfacter;
    }

    public void setConversionfacter(Float conversionfacter) {
        Conversionfacter = conversionfacter;
        try {

           // Stockquant=Stockquant*Conversionfacter;
            Total = (SPrice / (Conversionfacter)) * Quantity;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public int getResstock() {
        return Resstock;
    }

    public void setResstock(int resstock) {
        Resstock = resstock;
    }

    int Resstock;


    public String getTopproductName() {
        return topproductName;
    }

    public void setTopproductName(String topproductName) {
        this.topproductName = topproductName;
    }




    public String getStoreid() {
        return storeid;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }



    public String getProductshortName() {
        return ProductshortName;
    }

    public void setProductshortName(String productshortName) {
        ProductshortName = productshortName;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }







    public float getHoldstock() {
        return holdstock;
    }
    public void setHoldstock(float holdstock) {
        this.holdstock = holdstock;
    }
    public String getBill_No() {
        return Bill_No;
    }
    public String getTrans_id() {
        return Trans_id;
    }
    public String getHoldtotal() {
        return holdtotal;
    }

    public void setHoldtotal(String holdtotal) {
        this.holdtotal = holdtotal;
    }



    public void setTrans_id(String trans_id) {
        Trans_id = trans_id;
    }

    public void setBill_No(String bill_No) {
        Bill_No = bill_No;
    }

    public float getGrandTotal() {
        return GrandTotal;
    }

    public void setGrandTotal(float grandTotal) {
        GrandTotal = grandTotal;
    }

    public Sales (){

        this.Quantity=1;
        this.SPrice=1.0F;
    }
    public Sales(String productName,String batchNo,String expiry,Float Sprice,int quantity,float mrp,String amount,String Uom,Float grandtotal,Float conversionfacter) {
        ProductName = productName;
        BatchNo = batchNo;
        Expiry = expiry;
        PPrice = PPrice;
        SPrice = Sprice;
        Quantity = quantity;
        Mrp = mrp;
        Amount = amount;
        this.Uom=Uom;
        GrandTotal=grandtotal;
        Conversionfacter=conversionfacter;
    }

    public float getTotal() {
        return Total;
    }
    public String getProdid() {
        return Prodid;
    }

    public void setProdid(String prodid) {
        Prodid = prodid;
    }

    public void setTotal(float total) {
        Total = total;
        try {
            Total = (SPrice /(Conversionfacter)) * Quantity;
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getBatchNo() {
        return BatchNo;
    }

    public void setBatchNo(String batchNo) {
        BatchNo = batchNo;
    }

    public String getExpiry() {
        return Expiry;
    }

    public void setExpiry(String expiry) {
        Expiry = expiry;
    }

    public String getPPrice() {
        return PPrice;
    }

    public void setPPrice( String PPrice) {
        this.PPrice = PPrice;
    }

    public float getSPrice() {

        return SPrice;
    }
    public float getStockquant() {
        return Stockquant;
    }

    public void setStockquant(float stockquant) {

        Stockquant = stockquant;
        try{
            //Stockquant=Stockquant*Conversionfacter;

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void setSPrice(float SPrice) {
        this.SPrice = SPrice;
        try {
            Total = (SPrice /(Conversionfacter))* Quantity;
        } catch( Exception e) {

        }
    }

    public  int getQuantity() {

        return Quantity;
    }

    public void setQuantity( int quantity) {

        Quantity = quantity;
        try {
            Total = (SPrice / (Conversionfacter)) * Quantity;
        } catch( Exception e) {

        }
    }


    public  float getMrp() {
        return Mrp;
    }

    public void setMrp( float mrp) {
        Mrp = mrp;
    }

    public  String getAmount() {
        return Amount;
    }

    public void setAmount( String amount) {
        Amount = amount;
    }

    public String getUom() {
        return Uom;
    }

    public void setUom(String uom) {
        Uom = uom;
    }

    @Override
    public String toString() {
        return BatchNo;
    }
}


