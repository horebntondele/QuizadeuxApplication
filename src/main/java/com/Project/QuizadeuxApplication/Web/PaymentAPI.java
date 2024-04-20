package com.Project.QuizadeuxApplication.Web;
import com.Project.QuizadeuxApplication.Entities.Payment;
import com.Project.QuizadeuxApplication.Mpesa.C2BAPI;
import com.Project.QuizadeuxApplication.Mpesa.GenerateSessionID;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/C2BPayement")
@CrossOrigin("*")
public class PaymentAPI {


//    public void MpesaC2BPayement(@RequestBody PaymentBody paymentBody) throws Exception {
//        C2BAPI PaymentRequest = new C2BAPI();
//        PaymentRequest.C2PAPICall(paymentBody);
//
//    }
    @GetMapping
    public void SessionID() throws Exception {
        GenerateSessionID generateSessionID=new GenerateSessionID();
        generateSessionID.GenerateSessionID();
    }
//    @PostMapping
//    public Payment MpesaC2BPayementAPI(@RequestBody Payment payment) throws Exception {
//        C2BAPI PaymentRequest = new C2BAPI();
//       return PaymentRequest.C2BAPICall(payment);
//
//    }
    @GetMapping("/test")
    public  Payment GetPayment (){
        Payment payment= new Payment("M-Pesa", "243814444784", "1");
        return payment;
    }
}
