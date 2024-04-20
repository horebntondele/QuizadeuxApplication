package com.Project.QuizadeuxApplication.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "WatsAppMessage")
public class WatsAppMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "object")
    private  String object;
    @OneToMany(mappedBy = "watsAppMessage")
    private entry[] entry;


    public WatsAppMessage() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public com.Project.QuizadeuxApplication.Entities.entry[] getEntry() {
        return entry;
    }

    public void setEntry(com.Project.QuizadeuxApplication.Entities.entry[] entry) {
        this.entry = entry;
    }
}
