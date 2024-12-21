package org.acordi.creditanalysisms.service.strategy.impl;

import org.acordi.creditanalysisms.service.strategy.CreditComputation;
import org.acordi.creditanalysisms.domain.Proposal;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class OtherLoansInProgress implements CreditComputation {
    @Override
    public int compute(Proposal proposal) {
        return otherLoansInProgress() ? 0 : 80;
    }

    private boolean otherLoansInProgress(){
        return new Random().nextBoolean();
    }
}
