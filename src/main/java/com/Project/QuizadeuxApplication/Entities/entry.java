package com.Project.QuizadeuxApplication.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "entry")
public class entry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entryid")
    private int entryid;
    @Basic
    @Column(name = "id")
    private String id;
    @OneToMany(mappedBy = "entry")
    private changes[] changes;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "watsAppMessage", referencedColumnName = "id")
    private WatsAppMessage watsAppMessage;
    public entry() {
    }

    public int getEntryid() {
        return entryid;
    }

    public void setEntryid(int entryid) {
        this.entryid = entryid;
    }

    public WatsAppMessage getWatsAppMessage() {
        return watsAppMessage;
    }

    public void setWatsAppMessage(WatsAppMessage watsAppMessage) {
        this.watsAppMessage = watsAppMessage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public com.Project.QuizadeuxApplication.Entities.changes[] getChanges() {
        return changes;
    }

    public void setChanges(com.Project.QuizadeuxApplication.Entities.changes[] changes) {
        this.changes = changes;
    }
}
