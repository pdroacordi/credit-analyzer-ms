package org.acordi.microsservices.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ProposalRequestDTO {
    private String name;
    private String lastname;
    private String phone;
    private String cpf;
    private Double income;
    private Double askedValue;
    private int paymentTerm;

    public ProposalRequestDTO() {
    }

    public ProposalRequestDTO(String name, String lastname, String phone, String cpf, Double income, Double askedValue, int paymentTerm) {
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
        this.cpf = cpf;
        this.income = income;
        this.askedValue = askedValue;
        this.paymentTerm = paymentTerm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
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
}
