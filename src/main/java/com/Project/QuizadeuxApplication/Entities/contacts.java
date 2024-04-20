package com.Project.QuizadeuxApplication.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "contacts")
public class contacts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contactsid")
    private int contactsid;
    @OneToOne(mappedBy = "contacts", cascade = CascadeType.ALL)
    private  profile profile;
    @Basic
    @Column(name = "wa_id")
    private String wa_id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "value", referencedColumnName = "valueid")
    private value value;

    public com.Project.QuizadeuxApplication.Entities.value getValue() {
        return value;
    }

    public void setValue(com.Project.QuizadeuxApplication.Entities.value value) {
        this.value = value;
    }

    public contacts() {
    }

    public int getContactsid() {
        return contactsid;
    }

    public void setContactsid(int contactsid) {
        this.contactsid = contactsid;
    }

    public com.Project.QuizadeuxApplication.Entities.profile getProfile() {
        return profile;
    }

    public void setProfile(com.Project.QuizadeuxApplication.Entities.profile profile) {
        this.profile = profile;
    }

    public String getWa_id() {
        return wa_id;
    }

    public void setWa_id(String wa_id) {
        this.wa_id = wa_id;
    }
}
