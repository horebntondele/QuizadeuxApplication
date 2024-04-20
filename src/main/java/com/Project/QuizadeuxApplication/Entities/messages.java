package com.Project.QuizadeuxApplication.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "messages")
public class messages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "messagesid")
    private  int messagesid;
    @Basic
    @Column(name = "messagefrom")
    private String from;
    @Basic
    @Column(name = "messageid")
    private String id;
    @Basic
    @Column(name = "messagetimestamp")
    private String timestamp;
    @OneToOne(mappedBy = "messages", cascade = CascadeType.ALL)
    private text text;

    @OneToOne(mappedBy = "messages" , cascade = CascadeType.ALL)
    private Context context;
    @OneToOne(mappedBy = "messages", cascade = CascadeType.ALL)
    private Interactive interactive;
    @Basic
    @Column(name = "type")
    private  String type;
    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "value", referencedColumnName = "valueid")
    private value value;

    public messages() {
    }

    public int getMessagesid() {
        return messagesid;
    }

    public void setMessagesid(int messagesid) {
        this.messagesid = messagesid;
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public com.Project.QuizadeuxApplication.Entities.text getText() {
        return text;
    }

    public void setText(com.Project.QuizadeuxApplication.Entities.text text) {
        this.text = text;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public com.Project.QuizadeuxApplication.Entities.value getValue() {
        return value;
    }

    public void setValue(com.Project.QuizadeuxApplication.Entities.value value) {
        this.value = value;
    }

    public Interactive getInteractive() {
        return interactive;
    }

    public void setInteractive(Interactive interactive) {
        this.interactive = interactive;
    }
}
