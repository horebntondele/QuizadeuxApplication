package com.Project.QuizadeuxApplication.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "SessionManagement")
public class SessionManagement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SessionId", nullable = false)
    private int SessionId;
    @Basic
    @Column(name = "SessionMSISDN")
    private String SessionMSISDN;

    @Basic
    @Column(name = "SessionType")
    private String SessionType;

    @Basic
    @Column(name = "Category")
    private String category;

    @Basic
    @Column(name = "Status")
    private boolean Status;
    @Basic
    @Column(name = "WorkflowStatus")
    private String WorkflowStatus;

    @Basic
    @Column(name = "ConjointMSISDN")
    private String ConjointMSISDN;
    @Basic
    @Column(name = "Accept_Participation")
    private boolean Accept_Participation;

    @Basic
    @Column(name = "Payement_type")
    private String Payement_type;
    @OneToOne(mappedBy = "sessionManagement")
    private Partie partie;

    @Basic
    @Column(name = "Start")
    private boolean Start;
    @Basic
    @Column(name = "Question_number")
    private int Question=0;

    @Basic
    @Column(name = "ReponseParticipant")
    private int ReponseParticipant=0;

    @Basic
    @Column(name = "ReponseConjoint")
    private int ReponseConjoint=0;
    @OneToMany(mappedBy = "sessionManagement", cascade = CascadeType.ALL)
    private  Interaction [] interaction;
    public SessionManagement() {
    }

    public SessionManagement(String sessionMSISDN, boolean status) {
        SessionMSISDN = sessionMSISDN;
        Status = status;
    }

    public String getSessionMSISDN() {
        return SessionMSISDN;
    }

    public void setSessionMSISDN(String sessionMSISDN) {
        SessionMSISDN = sessionMSISDN;
    }

    public int getSessionId() {
        return SessionId;
    }

    public void setSessionId(int sessionId) {
        SessionId = sessionId;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public boolean isAccept_Participation() {
        return Accept_Participation;
    }

    public void setAccept_Participation(boolean accept_Participation) {
        Accept_Participation = accept_Participation;
    }


    public String getPayement_type() {
        return Payement_type;
    }

    public void setPayement_type(String payement_type) {
        Payement_type = payement_type;
    }

    public Partie getPartie() {
        return partie;
    }

    public void setPartie(Partie partie) {
        this.partie = partie;
    }

    public boolean isStart() {
        return Start;
    }

    public void setStart(boolean start) {
        Start = start;
    }

    public int getQuestion() {
        return Question;
    }

    public void setQuestion(int question) {
        Question = question;
    }


    public Interaction[] getInteraction() {
        return interaction;
    }

    public String getWorkflowStatus() {
        return WorkflowStatus;
    }

    public void setWorkflowStatus(String workflowStatus) {
        WorkflowStatus = workflowStatus;
    }

    public String getConjointMSISDN() {
        return ConjointMSISDN;
    }

    public void setConjointMSISDN(String conjointMSISDN) {
        ConjointMSISDN = conjointMSISDN;
    }

    public void setInteraction(Interaction[] interaction) {
        this.interaction = interaction;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getReponseParticipant() {
        return ReponseParticipant;
    }

    public void setReponseParticipant(int reponseParticipant) {
        ReponseParticipant = reponseParticipant;
    }

    public int getReponseConjoint() {
        return ReponseConjoint;
    }

    public void setReponseConjoint(int reponseConjoint) {
        ReponseConjoint = reponseConjoint;
    }

    public String getSessionType() {
        return SessionType;
    }

    public void setSessionType(String sessionType) {
        SessionType = sessionType;
    }
}
