package Pojo;

/**
 * Created by rspl-nishant on 18/5/16.
 */
public class CreditNote {




        String ReturnCreatedon;
        String ReturnInvoiceno;
        String Creditnotevalue;

        public String getCreditnotevalue() {
            return Creditnotevalue;
        }

        public void setCreditnotevalue(String creditnotevalue) {
            Creditnotevalue = creditnotevalue;
        }



        public String getReturnCreatedon() {
            return ReturnCreatedon;
        }

        public void setReturnCreatedon(String returnCreatedon) {
            ReturnCreatedon = returnCreatedon;
        }

        public String getReturnInvoiceno() {
            return ReturnInvoiceno;
        }

        public void setReturnInvoiceno(String returnInvoiceno) {
            ReturnInvoiceno = returnInvoiceno;
        }

    @Override
    public String toString() {
        return ReturnInvoiceno;
    }
}



