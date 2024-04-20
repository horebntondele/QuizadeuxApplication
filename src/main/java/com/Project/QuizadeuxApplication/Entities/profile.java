package com.Project.QuizadeuxApplication.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "profile")
public class profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profileid")
    private int profileid;
    @Basic
    @Column(name = "name")
    private  String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contacts", referencedColumnName = "contactsid")
    private contacts contacts;



    public profile() {
    }

    public int getProfileid() {
        return profileid;
    }

    public void setProfileid(int profileid) {
        this.profileid = profileid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public com.Project.QuizadeuxApplication.Entities.contacts getContacts() {
        return contacts;
    }

    public void setContacts(com.Project.QuizadeuxApplication.Entities.contacts contacts) {
        this.contacts = contacts;
    }
}
