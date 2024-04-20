package com.Project.QuizadeuxApplication.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "conversation")
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conversationid")
    private int conversationid;
    @Basic
    @Column(name = "id")
    private String id;

    @Basic
    @Column(name = "expiration_timestamp")
    private String expiration_timestamp;
    @OneToOne (mappedBy = "conversation", cascade = CascadeType.ALL)
    private Origin origin;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "statuses", referencedColumnName = "id_statuses")
    private Statuses statuses;

    public Conversation() {
    }

    public String getExpiration_timestamp() {
        return expiration_timestamp;
    }

    public void setExpiration_timestamp(String expiration_timestamp) {
        this.expiration_timestamp = expiration_timestamp;
    }

    public int getConversationid() {
        return conversationid;
    }

    public void setConversationid(int conversationid) {
        this.conversationid = conversationid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public Statuses getStatuses() {
        return statuses;
    }

    public void setStatuses(Statuses statuses) {
        this.statuses = statuses;
    }
}
