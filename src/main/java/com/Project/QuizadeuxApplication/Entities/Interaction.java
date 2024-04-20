package com.Project.QuizadeuxApplication.Entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "interaction")
public class Interaction {
    public Interaction() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interactionId" )
    private int interactionId;
    @Basic
    @Column(name ="MSISDN")
    private  String MSISDN;
    @Basic
    @Column(name = "Name")
    private String Name;

    @Basic
    @Column(name = "interactive_type")
    private String interactive_type;
    @Basic
    @Column(name = "interactiontype")
    private String interactiontype;
    @Basic
    @Column(name = "messagetext")
    private String messagetext;

    @Basic
    @Column(name = "replyID")
    private String replyID;
    @Basic
    @Column(name = "interaction_Date")
    private Date interaction_Datetime;
    @Basic
    @Column(name = "Score")
    private int Score;
    @Basic
    @Column(name = "Category")
    private String Category;
    @OneToOne(mappedBy = "interaction")
    private  Payment payement;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sessionManagement", referencedColumnName = "SessionId")
    private SessionManagement sessionManagement;

    public Interaction(String MSISDN, String name, Date interaction_Datetime) {
        this.MSISDN = MSISDN;
        Name = name;
        this.interaction_Datetime = interaction_Datetime;
    }

    public int getInteractionId() {
        return interactionId;
    }

    public void setInteractionId(int interactionId) {
        this.interactionId = interactionId;
    }

    public String getMSISDN() {
        return MSISDN;
    }

    public void setMSISDN(String MSISDN) {
        this.MSISDN = MSISDN;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Date getInteraction_Datetime() {
        return interaction_Datetime;
    }

    public void setInteraction_Datetime(Date interaction_Datetime) {
        this.interaction_Datetime = interaction_Datetime;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public Payment getPayement() {
        return payement;
    }

    public void setPayement(Payment payement) {
        this.payement = payement;
    }

    public String getInteractiontype() {
        return interactiontype;
    }

    public void setInteractiontype(String interactiontype) {
        this.interactiontype = interactiontype;
    }

    public String getMessagetext() {
        return messagetext;
    }

    public void setMessagetext(String messagetext) {
        this.messagetext = messagetext;
    }

    public String getReplyID() {
        return replyID;
    }

    public void setReplyID(String replyID) {
        this.replyID = replyID;
    }

    public String getInteractive_type() {
        return interactive_type;
    }

    public void setInteractive_type(String interactive_type) {
        this.interactive_type = interactive_type;
    }
    //    public SessionManagement getSessionManagement() {
//        return sessionManagement;
//    }
//
//    public void setSessionManagement(SessionManagement sessionManagement) {
//        this.sessionManagement = sessionManagement;
//    }
}
