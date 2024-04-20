package com.Project.QuizadeuxApplication.Entities;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "Statuses")
public class Statuses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_statuses")
    private int id_statuses;
    @Basic
    @Column(name = "id")
    private String id;
    @Basic
    @Column(name = "status")
    private String status;
    @Basic
    @Column(name = "timestamp")
    private String timestamp;
    @Basic
    @Column(name = "recipient_id")
    private String recipient_id;
    @OneToOne(mappedBy = "statuses", cascade = CascadeType.ALL)
    private Conversation conversation;
    @OneToOne(mappedBy = "statuses", cascade = CascadeType.ALL)
    private  Pricing pricing;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "value", referencedColumnName = "valueid")
    private value value;

    public Statuses() {
    }

    public int getId_statuses() {
        return id_statuses;
    }

    public void setId_statuses(int id_statuses) {
        this.id_statuses = id_statuses;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getRecipient_id() {
        return recipient_id;
    }

    public void setRecipient_id(String recipient_id) {
        this.recipient_id = recipient_id;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public Pricing getPricing() {
        return pricing;
    }

    public void setPricing(Pricing pricing) {
        this.pricing = pricing;
    }

    public com.Project.QuizadeuxApplication.Entities.value getValue() {
        return value;
    }

    public void setValue(com.Project.QuizadeuxApplication.Entities.value value) {
        this.value = value;
    }
}
