package com.Project.QuizadeuxApplication.Entities;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "value")
public class value {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "valueid")
    private int valueid;

    @Basic
    @Column(name = "messaging_product")
    private  String messaging_product;
    @OneToOne(mappedBy = "value", cascade = CascadeType.ALL)
    private  metadata metadata;
    @OneToMany(mappedBy = "value", cascade = CascadeType.ALL)
    private messages[] messages;
    @OneToMany(mappedBy = "value", cascade = CascadeType.ALL)
    private contacts[] contacts;

    @OneToMany(mappedBy = "value", cascade = CascadeType.ALL)
    private Statuses[] statuses;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "changes", referencedColumnName = "changesid")
    private changes changes;

    public value() {
    }
    public Statuses[] getStatuses() {
        return statuses;
    }

    public void setStatuses(Statuses[] statuses) {
        this.statuses = statuses;
    }

    public int getValueid() {
        return valueid;
    }

    public void setValueid(int valueid) {
        this.valueid = valueid;
    }

    public com.Project.QuizadeuxApplication.Entities.contacts[] getContacts() {
        return contacts;
    }

    public void setContacts(com.Project.QuizadeuxApplication.Entities.contacts[] contacts) {
        this.contacts = contacts;
    }

    public com.Project.QuizadeuxApplication.Entities.messages[] getMessages() {
        return messages;
    }

    public void setMessages(com.Project.QuizadeuxApplication.Entities.messages[] messages) {
        this.messages = messages;
    }

    public String getMessaging_product() {
        return messaging_product;
    }

    public void setMessaging_product(String messaging_product) {
        this.messaging_product = messaging_product;
    }

    public com.Project.QuizadeuxApplication.Entities.metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(com.Project.QuizadeuxApplication.Entities.metadata metadata) {
        this.metadata = metadata;
    }

    public com.Project.QuizadeuxApplication.Entities.changes getChanges() {
        return changes;
    }

    public void setChanges(com.Project.QuizadeuxApplication.Entities.changes changes) {
        this.changes = changes;
    }
}
