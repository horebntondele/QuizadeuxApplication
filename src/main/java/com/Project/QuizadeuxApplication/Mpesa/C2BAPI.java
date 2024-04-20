package com.Project.QuizadeuxApplication.Mpesa;

import com.Project.QuizadeuxApplication.Entities.C2BToken;
import com.Project.QuizadeuxApplication.Entities.Payment;
import com.Project.QuizadeuxApplication.Service.C2BTokenService;
import mu.prevoir.sdk.APIContext;
import mu.prevoir.sdk.APIMethodType;
import mu.prevoir.sdk.APIRequest;
import mu.prevoir.sdk.APIResponse;

import java.util.Map;
import java.util.regex.Pattern;

import static com.Project.QuizadeuxApplication.Constant.Environment.*;

public class C2BAPI {
        public Payment C2BAPICall(Payment payment, String sessionID) throws Exception{

            String LocaltransactionID= System.currentTimeMillis()+ ""+payment.getPayment_id();

        // Create Context with API to request a Session ID
        APIContext context = APIContext.builder()
                // Session key
                .apiKey(sessionID)
                // Public key
                .publicKey(Mpesa_Publickey)
                // Use ssl/https
                .ssl(true)
                // Method type (can be GET/POST/PUT)
                .apiMethodType(APIMethodType.POST)
                // API address
                .address(Mpesa_Server)
                // API Port
                .port(Mpesa_Port)
                // API Path
                .path(Mpesa_C2Burl)
                .build();

        // Parameters can be added to the call as well that on POST will be in JSON format and on GET will be URL parameters
        // context.addParameter("key", "value");
        context.addParameter("input_Amount", payment.getPayment_Amount());
        context.addParameter("input_Country", "DRC");
        context.addParameter("input_Currency", "USD");
        context.addParameter("input_CustomerMSISDN", payment.getPayment_MSISDN());
        context.addParameter("input_ServiceProviderCode", "000000");
        context.addParameter("input_ThirdPartyConversationID", LocaltransactionID);
        context.addParameter("input_TransactionReference", "T1234C");
        context.addParameter("input_PurchasedItemsDesc", "Good");

        // Create a request object
        APIRequest request = new APIRequest(context);
        payment.setLocaltransactionID(LocaltransactionID);

        // Add/update headers
        context.addHeader("Origin", "*");

        // Do the API call and put result in a response packet
        APIResponse response = null;

        try {
            response = request.execute();
        } catch (Exception e) {
            System.out.println("Call failed: " + e.getMessage());
        }

        // Display results
        if(response != null) {
            System.out.println(response.getStatusCode() + " - " + response.getReason());
            System.out.println(response.getResult());
            payment.setPayment_Status(response.getResult());
            for(Map.Entry<String, String> entry: response.getBody().entrySet()){
                System.out.println(entry.getKey() + ":" + response.getBody().get(entry.getKey()));
                String checkedkey=entry.getKey();
                if(checkedkey.equals("output_ResponseDesc")){
                    payment.setPayment_Status(response.getBody().get(entry.getKey()));
                } else if (checkedkey.equals("output_ConversationID")) {
                    payment.setTransaction_id(response.getBody().get(entry.getKey()));

                }
            }
        } else {
            throw new Exception("API call failed to get result. Please check.");
        }
        return payment;
    }
    public void C2PAPICall(Payment payment) throws Exception{


// Public key on the API listener used to encrypt keys

        // Create Context with API to request a Session ID
        APIContext context = APIContext.builder()
                // Session key
                .apiKey(Mpesa_APIKey)
                // Public key
                .publicKey(Mpesa_Publickey)
                // Use ssl/https
                .ssl(true)
                // Method type (can be GET/POST/PUT)
                .apiMethodType(APIMethodType.POST)
                // API address
                .address(Mpesa_Server)
                // API Port
                .port(Mpesa_Port)
                // API Path
                .path(Mpesa_C2Burl)
                .build();

        // Parameters can be added to the call as well that on POST will be in JSON format and on GET will be URL parameters
        // context.addParameter("key", "value");
        context.addParameter("input_Amount", payment.getPayment_Amount());
        context.addParameter("input_Country", "DRC");
        context.addParameter("input_Currency", "USD");
        context.addParameter("input_CustomerMSISDN", payment.getPayment_MSISDN());
        context.addParameter("input_ServiceProviderCode", "000000");
        context.addParameter("input_ThirdPartyConversationID",System.currentTimeMillis()+""+ payment.getTransaction_id());
        context.addParameter("input_TransactionReference", "T1234C");
        context.addParameter("input_PurchasedItemsDesc", "payment Pour Quiz à deux");

        // Create a request object
        APIRequest request = new APIRequest(context);

        // Add/update headers
        context.addHeader("Origin", "*");

        // Do the API call and put result in a response packet
        APIResponse response = null;

        try {
            response = request.execute();
        } catch (Exception e) {
            System.out.println("Call failed: " + e.getMessage());
        }

        // Display results
        if(response != null) {
            System.out.println(response.getStatusCode() + " - " + response.getReason());
            System.out.println(response.getResult());
            payment.setPayment_Status(response.getResult());
            for(Map.Entry<String, String> entry: response.getBody().entrySet()){
                System.out.println(entry.getKey() + ":" + response.getBody().get(entry.getKey()));
            }
        } else {
            throw new Exception("API call failed to get result. Please check.");
        }

    }

    public String MsisdnFormatCheck(String MSISDN, String Operateur){
        String resultat ="";
        if(Operateur.equals("M-Pesa")){
            String pattern="^2438[123]\\d{7}";
            if(Pattern.matches(pattern,MSISDN)){
                resultat= "true";
            }else {
                resultat= "false";
            }
        }
        return resultat;
    }


}
