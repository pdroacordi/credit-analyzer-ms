package org.acordi.microsservices.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double askedValue;

    private int paymentTerm;

    private Boolean approved;

    private boolean integrated;

    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    @JsonManagedReference
    private User user;

    public Proposal() {
    }

    public Proposal(Long id, Double askedValue, int paymentTerm, Boolean approved, boolean integrated, String description, User user) {
        this.id = id;
        this.askedValue = askedValue;
        this.paymentTerm = paymentTerm;
        this.approved = approved;
        this.integrated = integrated;
        this.description = description;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAskedValue() {
        return askedValue;
    }

    public void setAskedValue(Double askedValue) {
        this.askedValue = askedValue;
    }

    public int getPaymentTerm() {
        return paymentTerm;
    }

    public void setPaymentTerm(int paymentTerm) {
        this.paymentTerm = paymentTerm;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public boolean isIntegrated() {
        return integrated;
    }

    public void setIntegrated(boolean integrated) {
        this.integrated = integrated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
