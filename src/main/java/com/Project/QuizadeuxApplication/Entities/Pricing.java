package com.Project.QuizadeuxApplication.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "pricing")
public class Pricing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pricingid")
    private int pricingid;
    @Basic
    @Column(name = "billable")
    private boolean billable;
    @Basic
    @Column(name = "pricing_model")
    private String pricing_model;
    @Basic
    @Column(name = "category")
    private String category;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "statuses" , referencedColumnName = "id_statuses")
    private Statuses statuses;

    public Pricing() {
    }

    public int getPricingid() {
        return pricingid;
    }

    public void setPricingid(int pricingid) {
        this.pricingid = pricingid;
    }

    public boolean isBillable() {
        return billable;
    }

    public void setBillable(boolean billable) {
        this.billable = billable;
    }

    public String getPricing_model() {
        return pricing_model;
    }

    public void setPricing_model(String pricing_model) {
        this.pricing_model = pricing_model;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Statuses getStatuses() {
        return statuses;
    }

    public void setStatuses(Statuses statuses) {
        this.statuses = statuses;
    }
}
