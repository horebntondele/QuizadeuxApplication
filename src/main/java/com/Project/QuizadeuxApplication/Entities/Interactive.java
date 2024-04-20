package com.Project.QuizadeuxApplication.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Interactive")
public class Interactive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interactId")
    private int interactId;
    @Basic
    @Column(name = "type")
    private String type;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "messages", referencedColumnName = "messagesid")
    private messages messages;
    @OneToOne(mappedBy = "interactive", cascade = CascadeType.ALL)
    private  Reply button_reply;

    @OneToOne(mappedBy = "interactive",cascade = CascadeType.ALL)
    private Reply list_reply;

    public int getInteractId() {
        return interactId;
    }

    public void setInteractId(int interactId) {
        this.interactId = interactId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public com.Project.QuizadeuxApplication.Entities.messages getMessages() {
        return messages;
    }

    public void setMessages(com.Project.QuizadeuxApplication.Entities.messages messages) {
        this.messages = messages;
    }

    public Reply getButton_reply() {
        return button_reply;
    }

    public void setButton_reply(Reply button_reply) {
        this.button_reply = button_reply;
    }

    public Reply getList_reply() {
        return list_reply;
    }

    public void setList_reply(Reply list_reply) {
        this.list_reply = list_reply;
    }
}
