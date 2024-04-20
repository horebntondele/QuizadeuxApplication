package com.Project.QuizadeuxApplication.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "text")
public class text {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "textid")
    private  int textid;
    @Basic
    @Column(name = "body")
    private String body;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "messages", referencedColumnName = "messagesid")
    private messages messages;

    public text() {
    }

    public int getTextid() {
        return textid;
    }

    public void setTextid(int textid) {
        this.textid = textid;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public com.Project.QuizadeuxApplication.Entities.messages getMessages() {
        return messages;
    }

    public void setMessages(com.Project.QuizadeuxApplication.Entities.messages messages) {
        this.messages = messages;
    }
}
