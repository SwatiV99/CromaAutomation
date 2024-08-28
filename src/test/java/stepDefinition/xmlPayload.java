package stepDefinition;

import static Constants.cromaConstants.ORDER_NUMBER;

public class xmlPayload {
    public static String getReturnOrderDetails(String orderNo){
        return "<Order DocumentType=\"0001\" EnterpriseCode=\"CROMA\" OrderNo=\""+ORDER_NUMBER+"\">\n" +
                "</Order>";
    }
}
