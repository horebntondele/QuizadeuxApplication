package com.Project.QuizadeuxApplication.Mpesa;

import com.Project.QuizadeuxApplication.Service.C2BTokenService;
import mu.prevoir.sdk.APIContext;
import mu.prevoir.sdk.APIMethodType;
import mu.prevoir.sdk.APIRequest;
import mu.prevoir.sdk.APIResponse;

import java.util.Map;

public class GenerateSessionID {
public String GenerateSessionID() throws Exception {
	String SessionID="";
		// Public key on the API listener used to encrypt keys
	String publicKey = "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEArv9yxA69XQKBo24BaF/D+fvlqmGdYjqLQ5WtNBb5tquqGvAvG3WMFETVUSow/LizQalxj2ElMVrUmzu5mGGkxK08bWEXF7a1DEvtVJs6nppIlFJc2SnrU14AOrIrB28ogm58JjAl5BOQawOXD5dfSk7MaAA82pVHoIqEu0FxA8BOKU+RGTihRU+ptw1j4bsAJYiPbSX6i71gfPvwHPYamM0bfI4CmlsUUR3KvCG24rB6FNPcRBhM3jDuv8ae2kC33w9hEq8qNB55uw51vK7hyXoAa+U7IqP1y6nBdlN25gkxEA8yrsl1678cspeXr+3ciRyqoRgj9RD/ONbJhhxFvt1cLBh+qwK2eqISfBb06eRnNeC71oBokDm3zyCnkOtMDGl7IvnMfZfEPFCfg5QgJVk1msPpRvQxmEsrX9MQRyFVzgy2CWNIb7c+jPapyrNwoUbANlN8adU1m6yOuoX7F49x+OjiG2se0EJ6nafeKUXw/+hiJZvELUYgzKUtMAZVTNZfT8jjb58j8GVtuS+6TM2AutbejaCV84ZK58E2CRJqhmjQibEUO6KPdD7oTlEkFy52Y1uOOBXgYpqMzufNPmfdqqqSM4dU70PO8ogyKGiLAIxCetMjjm6FCMEA3Kc8K0Ig7/XtFm9By6VxTJK1Mg36TlHaZKP6VzVLXMtesJECAwEAAQ==";
		// Create Context with API to request a `Session ID
		APIContext context = APIContext.builder()
				// Api key
				.apiKey("O2vQtJtlJzcoe1bLx8asrnrWryysLt4y")
				// Public key
				.publicKey(publicKey)
				// Use ssl/https
				.ssl(true)
				// Method type (can be GET/POST)
				.apiMethodType(APIMethodType.GET)
				// API address
				.address("openapi.m-pesa.com")
				// API Port
				.port(443)
				// API Path
				.path("/sandbox/ipg/v2/vodacomDRC/getSession/")
				.build();

		// Parameters can be added to the call as well that on POST will be in JSON format and on GET will be URL parameters
		// context.addParameter("key", "value");

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


			for(Map.Entry<String, String> entry: response.getBody().entrySet()){
				System.out.println(entry.getKey() + ":" + response.getBody().get(entry.getKey()));
				if(entry.getKey().equals("output_SessionID")){
					SessionID=response.getBody().get(entry.getKey());
				}
			}
		} else {
			SessionID="Failed";
		 	throw new Exception("SessionKey call failed to get result. Please check.");
		}
		return SessionID;
}

}
