package com.Project.QuizadeuxApplication.Mpesa;

import java.util.HashMap;
import java.util.Map;

public class PaymentBody {
   private String Amount;
   private String MSISDN;
   private String ConversationID;
   private String TransactionRAISON;

   private Map<String, String> APIResult = new HashMap<>();

    public Map<String, String> getAPIResult() {
        return APIResult;
    }

    public void setAPIResult(Map<String, String> APIResult) {
        this.APIResult = APIResult;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getMSISDN() {
        return MSISDN;
    }

    public void setMSISDN(String MSISDN) {
        this.MSISDN = MSISDN;
    }

    public String getConversationID() {
        return ConversationID;
    }

    public void setConversationID(String conversationID) {
        ConversationID = conversationID;
    }

    public String getTransactionRAISON() {
        return TransactionRAISON;
    }

    public void setTransactionRAISON(String transactionRAISON) {
        TransactionRAISON = transactionRAISON;
    }
}
