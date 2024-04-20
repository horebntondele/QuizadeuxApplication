package com.Project.QuizadeuxApplication.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "changes")
public class changes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "changesid")
    private int changesid;

    @OneToOne(mappedBy = "changes", cascade = CascadeType.ALL)
    private value value;
    @Basic
    @Column(name = "field")
    private String field;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entry", referencedColumnName = "entryid")
    private entry entry;
    public changes() {
    }

    public int getChangesid() {
        return changesid;
    }

    public com.Project.QuizadeuxApplication.Entities.entry getEntry() {
        return entry;
    }

    public void setEntry(com.Project.QuizadeuxApplication.Entities.entry entry) {
        this.entry = entry;
    }

    public void setChangesid(int changesid) {
        this.changesid = changesid;
    }

    public com.Project.QuizadeuxApplication.Entities.value getValue() {
        return value;
    }

    public void setValue(com.Project.QuizadeuxApplication.Entities.value value) {
        this.value = value;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
