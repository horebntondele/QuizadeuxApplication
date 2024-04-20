package com.Project.QuizadeuxApplication.Entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "QuestionsList")
public class QuestionsList {
    public QuestionsList() {
    }

    public QuestionsList(String question, String reponse1, String reponse2, String reponse3, String reponse4, String bonneReponse) {
        Question = question;
        Reponse1 = reponse1;
        Reponse2 = reponse2;
        Reponse3 = reponse3;
        Reponse4 = reponse4;
        BonneReponse = bonneReponse;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QuestionID")
    private  int QuestionID;

    @Basic
    @Column(name = "Question")
    private  String Question;

    @Basic
    @Column(name = "Reponse1")
    private  String Reponse1;

    @Basic
    @Column(name = "Reponse2")
    private  String Reponse2;

    @Basic
    @Column(name = "Reponse3")
    private  String Reponse3;

    @Basic
    @Column(name = "Reponse4")
    private  String Reponse4;

    @Basic
    @Column(name = "BonneReponse")
    private  String BonneReponse;
    @ManyToMany(mappedBy = "questionsList", cascade = CascadeType.ALL)
    private List<Partie> parties;

    public int getQuestionID() {
        return QuestionID;
    }

    public void setQuestionID(int questionID) {
        QuestionID = questionID;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getReponse1() {
        return Reponse1;
    }

    public void setReponse1(String reponse1) {
        Reponse1 = reponse1;
    }

    public String getReponse2() {
        return Reponse2;
    }

    public void setReponse2(String reponse2) {
        Reponse2 = reponse2;
    }

    public String getReponse3() {
        return Reponse3;
    }

    public void setReponse3(String reponse3) {
        Reponse3 = reponse3;
    }

    public String getReponse4() {
        return Reponse4;
    }

    public void setReponse4(String reponse4) {
        Reponse4 = reponse4;
    }

    public String getBonneReponse() {
        return BonneReponse;
    }

    public void setBonneReponse(String bonneReponse) {
        BonneReponse = bonneReponse;
    }

    public List<Partie> getParties() {
        return parties;
    }

    public void setParties(List<Partie> parties) {
        this.parties = parties;
    }
}
