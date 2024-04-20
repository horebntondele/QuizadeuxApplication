package com.Project.QuizadeuxApplication.Entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Partie")
public class Partie {

    public Partie() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PartieId")
    private int Partieid;
    @Basic
    @Column(name = "Participant")
    private String Participant;
    @Basic
    @Column(name = "PartieStatus")
    private boolean PartieStatus;
    @Basic
    @Column(name = "Conjoint")
    private String Conjoint;
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "sessionId", referencedColumnName = "SessionId", nullable = false)
    private SessionManagement sessionManagement;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "PartiesquestionsList",
            joinColumns = {@JoinColumn(name = "PartieId")},
            inverseJoinColumns = {@JoinColumn(name = "QuestionID")})
    private  List<QuestionsList> questionsList;
    @ElementCollection
    private List<String> ParticipantResponse;
    @ElementCollection
    private  List<String> ConjointResponse;

    public String getParticipant() {
        return Participant;
    }

    public void setParticipant(String participant) {
        Participant = participant;
    }

    public String getConjoint() {
        return Conjoint;
    }

    public void setConjoint(String conjoint) {
        Conjoint = conjoint;
    }

    public int getPartieid() {
        return Partieid;
    }
    public void setPartieid(int partieid) {
        Partieid = partieid;
    }

    public boolean isPartieStatus() {
        return PartieStatus;
    }

    public void setPartieStatus(boolean partieStatus) {
        PartieStatus = partieStatus;
    }

    public List<QuestionsList> getQuestionsList() {
        return questionsList;
    }

    public void setQuestionsList(List<QuestionsList> questionsList) {
        this.questionsList = questionsList;
    }

    public List<String> getParticipantResponse() {
        return ParticipantResponse;
    }

    public void setParticipantResponse(List<String> participantResponse) {
        ParticipantResponse = participantResponse;
    }

    public List<String> getConjointResponse() {
        return ConjointResponse;
    }

    public void setConjointResponse(List<String> conjointResponse) {
        ConjointResponse = conjointResponse;
    }

    public SessionManagement getSessionManagement() {
        return sessionManagement;
    }

    public void setSessionManagement(SessionManagement sessionManagement) {
        this.sessionManagement = sessionManagement;
    }
}
