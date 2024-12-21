package org.acordi.creditanalysisms.service.strategy.impl;

import org.acordi.creditanalysisms.service.strategy.CreditComputation;
import org.acordi.creditanalysisms.domain.Proposal;
import org.springframework.stereotype.Component;

@Component
public class EnoughIncome implements CreditComputation {
    @Override
    public int compute(Proposal proposal) {
        return isIncomeEnough(proposal.getUser().getIncome(), proposal.getAskedValue(), proposal.getPaymentTerm() ) ? 200 : -150;
    }

    private boolean isIncomeEnough(Double income, Double askedValue, int paymentTerm){
        return (income * paymentTerm) > askedValue;
    }
}
