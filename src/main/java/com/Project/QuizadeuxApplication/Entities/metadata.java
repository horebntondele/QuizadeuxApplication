package com.Project.QuizadeuxApplication.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "metadata")
public class metadata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "metadataid")
    private  int metadataid;
    @Basic
    @Column(name = "display_phone_number")
    private  String display_phone_number;
    @Basic
    @Column(name = "phone_number_id")
    private String phone_number_id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "value", referencedColumnName = "valueid")
    private com.Project.QuizadeuxApplication.Entities.value value;

    public metadata() {
    }

    public int getMetadataid() {
        return metadataid;
    }

    public void setMetadataid(int metadataid) {
        this.metadataid = metadataid;
    }

    public String getDisplay_phone_number() {
        return display_phone_number;
    }

    public void setDisplay_phone_number(String display_phone_number) {
        this.display_phone_number = display_phone_number;
    }

    public String getPhone_number_id() {
        return phone_number_id;
    }

    public void setPhone_number_id(String phone_number_id) {
        this.phone_number_id = phone_number_id;
    }

    public com.Project.QuizadeuxApplication.Entities.value getValue() {
        return value;
    }

    public void setValue(com.Project.QuizadeuxApplication.Entities.value value) {
        this.value = value;
    }
}
