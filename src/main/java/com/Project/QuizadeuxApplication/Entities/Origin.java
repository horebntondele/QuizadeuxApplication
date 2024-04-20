package com.Project.QuizadeuxApplication.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "origin")
public class Origin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "originId")
    private int originId;
    @Basic
    @Column(name = "type")
    private  String type;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "conversation", referencedColumnName = "conversationid")
    private Conversation conversation;

    public Origin() {
    }

    public int getOriginId() {
        return originId;
    }

    public void setOriginId(int originId) {
        this.originId = originId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }
}
