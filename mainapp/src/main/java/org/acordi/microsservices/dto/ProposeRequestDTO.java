package org.acordi.microsservices.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProposeRequestDTO {
    private String name;
    private String lastname;
    private String phone;
    private String cpf;
    private Double income;
    private Double askedValue;
    private int paymentTerm;

}
