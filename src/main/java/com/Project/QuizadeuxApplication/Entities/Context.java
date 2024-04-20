package com.Project.QuizadeuxApplication.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Context")
public class Context {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contextid")
    private int contextid;
    @Basic
    @Column(name = "Contextfrom")
    private  String from;
    @Basic
    @Column(name = "idcontext")
    private String id;
    @OneToOne
    @JoinColumn(name = "messages", referencedColumnName = "messagesid")
    private messages messages;

    public Context() {
    }

    public int getContextid() {
        return contextid;
    }

    public void setContextid(int contextid) {
        this.contextid = contextid;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public com.Project.QuizadeuxApplication.Entities.messages getMessages() {
        return messages;
    }

    public void setMessages(com.Project.QuizadeuxApplication.Entities.messages messages) {
        this.messages = messages;
    }
}
