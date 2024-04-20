package com.Project.QuizadeuxApplication.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Payement")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Payment_id")
    private int Payment_id;
    @Basic
    @Column(name = "Payment_Type")
    private String Payment_type;
    @Basic
    @Column(name = "Payment_Msisdn")
    private String Payment_MSISDN;
    @Basic
    @Column(name = "Payment_Amount")
    private String Payment_Amount;

    @Basic
    @Column(name = "Payment_Status")
    private String Payment_Status;
    @Basic
    @Column(name = "LocaltransactionID")
    private String LocaltransactionID;

    @Basic
    @Column(name = "Transaction_Id")
    private String Transaction_id;
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "interaction", referencedColumnName = "interactionId", nullable = false)
    private Interaction interaction;

    public Payment(String payment_type, String payment_MSISDN, String payment_Amount) {
        Payment_type = payment_type;
        Payment_MSISDN = payment_MSISDN;
        Payment_Amount = payment_Amount;
    }

    public int getPayment_id() {
        return Payment_id;
    }

    public void setPayment_id(int payment_id) {
        Payment_id = payment_id;
    }

    public String getPayment_type() {
        return Payment_type;
    }

    public void setPayment_type(String payment_type) {
        Payment_type = payment_type;
    }

    public String getPayment_MSISDN() {
        return Payment_MSISDN;
    }

    public void setPayment_MSISDN(String payment_MSISDN) {
        Payment_MSISDN = payment_MSISDN;
    }

    public String getPayment_Amount() {
        return Payment_Amount;
    }

    public void setPayment_Amount(String payment_Amount) {
        Payment_Amount = payment_Amount;
    }

    public String getPayment_Status() {
        return Payment_Status;
    }

    public void setPayment_Status(String payment_Status) {
        Payment_Status = payment_Status;
    }

    public String getTransaction_id() {
        return Transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        Transaction_id = transaction_id;
    }

    public Interaction getInteraction() {
        return interaction;
    }

    public void setInteraction(Interaction interaction) {
        this.interaction = interaction;
    }

    public String getLocaltransactionID() {
        return LocaltransactionID;
    }

    public void setLocaltransactionID(String localtransactionID) {
        LocaltransactionID = localtransactionID;
    }
}
