package Pojo;

/**
 * Created by rspl-gourav on 20/6/16.
 */
public class Inventorygrnmodel {
    String flag;
    String InventoryOrderNo;
    String LastUpdate;



    public String getInventoryOrderNo() {
        return InventoryOrderNo;
    }

    public void setInventoryOrderNo(String inventoryOrderNo) {
        InventoryOrderNo = inventoryOrderNo;
    }

    public String getLastUpdate() {
        return LastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        LastUpdate = lastUpdate;
    }


    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }


}