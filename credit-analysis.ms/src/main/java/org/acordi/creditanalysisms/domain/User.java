package org.acordi.creditanalysisms.domain;


import java.util.List;

public class User {
    private Long id;

    private String name;

    private String lastname;

    private String cpf;

    private String phone;

    private Double income;


    public User(Long id, String name, String lastname, String cpf, String phone, Double income) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.cpf = cpf;
        this.phone = phone;
        this.income = income;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

}
