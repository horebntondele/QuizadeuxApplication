package com.Project.QuizadeuxApplication.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "categoryId")
    private String categoryId;

    @Basic
    @Column(name = "categoryTitle")
    private String categoryTitle;

    @Basic
    @Column(name = "categorydescription")
    private String categorydescription;

    @Basic
    @Column(name = "price")
    private String price;

    @Basic
    @Column(name = "sectiontitle")
    private String sectiontitle;

    public Category() {
    }

    public Category(String categoryId, String categoryTitle, String categorydescription, String price, String sectiontitle) {
        this.categoryId = categoryId;
        this.categoryTitle = categoryTitle;
        this.categorydescription = categorydescription;
        this.price = price;
        this.sectiontitle=sectiontitle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public String getCategorydescription() {
        return categorydescription;
    }

    public void setCategorydescription(String categorydescription) {
        this.categorydescription = categorydescription;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSectiontitle() {
        return sectiontitle;
    }

    public void setSectiontitle(String sectiontitle) {
        this.sectiontitle = sectiontitle;
    }
}
