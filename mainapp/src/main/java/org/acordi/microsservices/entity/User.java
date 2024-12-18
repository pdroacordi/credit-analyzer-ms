package org.acordi.microsservices.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastname;

    @Column(name="cpf", unique = true)
    private String cpf;

    private String phone;

    private Double income;

    @OneToMany(mappedBy = "user")
    private List<Propose> proposes;

    public User() {
    }

    public User(Long id, String name, String lastname, String cpf, String phone, Double income, List<Propose> proposes) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.cpf = cpf;
        this.phone = phone;
        this.income = income;
        this.proposes = proposes;
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

    public List<Propose> getProposes() {
        return proposes;
    }

    public void setProposes(List<Propose> proposes) {
        this.proposes = proposes;
    }
}
